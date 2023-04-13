#include "mainwindow.h"
#include "Env.h"
#include "Expr.hpp"
#include "Parse.h"
#include "Val.h"
#include "pointer.h"

mainWindow::mainWindow(QWidget *parent)
    : QWidget{parent}
{
    expression = new QLabel("Expression");
    result = new QLabel("Result");
    exprBox = new QTextEdit();
    resultBox = new QTextEdit();
    interp = new QRadioButton("Interp");
    prettyPrint = new QRadioButton("Pretty Print");
    submitB = new QPushButton("Submit");
    resetB = new QPushButton("Reset");

    grid = new QGridLayout();

    formatLayout();
    setLayout(grid);
    connect(submitB, &QPushButton::clicked, this, &mainWindow::submitInput);
    connect(resetB, &QPushButton::clicked, this, &mainWindow::resetWindow);
}



void mainWindow::formatLayout(){
    grid->addWidget(expression, 0, 0);
    grid->addWidget(exprBox, 0, 1);

    QGroupBox *groupBox = new QGroupBox();
    QVBoxLayout *checkBoxLayout = new QVBoxLayout();
    checkBoxLayout->addWidget(interp);
    checkBoxLayout->addWidget(prettyPrint);
    groupBox->setLayout(checkBoxLayout);
    grid->addWidget(groupBox, 1, 1);
    grid->addWidget(submitB, 2, 1);
    grid->addWidget(result, 3, 0);
    grid->addWidget(resultBox, 3, 1);
    grid->addWidget(submitB, 4, 1);
    grid->addWidget(resetB, 5, 1);

}

void mainWindow::submitInput(){
    QString input;
    PTR(EmptyEnv) empty = NEW(EmptyEnv)();
    input += exprBox->toPlainText();
    std::string strToBeParsed = input.toUtf8().constData();
    PTR(Expr) expr = parseString(strToBeParsed);
    std::string resultsToPrint;
    try {

    if(interp->isChecked()){
        PTR(Val) val = expr->interp(empty);
        resultsToPrint = val->toString();
    }else if(prettyPrint->isChecked()){
        resultsToPrint = expr->prettyToString();
    }else{
        resultsToPrint = "please select an operation";
    }
    } catch (std::exception e) {
    resultsToPrint = "invalid input";
    }
    QString qResults  = QString::fromStdString(resultsToPrint);
    resultBox->setText(qResults);

}

void mainWindow::resetWindow(){
    exprBox->clear();
    resultBox->clear();
    interp->setAutoExclusive(false);
    interp->setChecked(false);
    interp->setAutoExclusive(true);
    prettyPrint->setAutoExclusive(false);
    prettyPrint->setChecked(false);
    prettyPrint->setAutoExclusive(true);
}
