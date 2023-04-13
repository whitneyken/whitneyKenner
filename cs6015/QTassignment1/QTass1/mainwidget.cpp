#include "mainwidget.h"

mainWidget::mainWidget(QWidget *parent)
    : QWidget{parent}
{
    fName = new QLabel("First Name:");
    lName = new QLabel("Last Name");
    fNameEdit = new QLineEdit();
    lNameEdit = new QLineEdit();
    age = new QLabel("Age:");
    ageBox = new QSpinBox();
    gender = new QLabel("Sex:");
    male = new QRadioButton("Male");
    female = new QRadioButton("Female");
    refreshB = new QPushButton("Refresh");
    summary = new QTextEdit();
    finishB = new QPushButton("Finish");

    vBox = new QVBoxLayout();
    grid = new QGridLayout();

    makeLayout();
    makeVerticalLayout();
    setLayout(vBox);

    connect(finishB, &QPushButton::clicked, this, &mainWidget::fillSummary);
    connect(refreshB, &QPushButton::clicked, this, &mainWidget::refreshFields);
}

void mainWidget::fillSummary(){
    QString result;
    result += ("First Name: " + fNameEdit->text() + "\n" +
                    "Last Name: " + lNameEdit->text() + "\n" +
                    "Age: " + ageBox->text() + "\n" +
                     "Sex: ");
    if(male->isChecked()){
        result += "Male";
    }else if(female->isChecked()){
        result += "Female";
    }
    summary->setText(result);

}

void mainWidget::refreshFields(){
    fNameEdit->clear();
    lNameEdit->clear();
    ageBox->setValue(0);
    male->setAutoExclusive(false);
    male->setChecked(false);
    male->setAutoExclusive(true);
    female->setAutoExclusive(false);
    female->setChecked(false);
    female->setAutoExclusive(true);
    summary->clear();
}
void mainWidget::makeLayout(){
    grid->addWidget(fName, 0,0);
    grid->addWidget(fNameEdit, 0, 1);
    grid->addWidget(age, 0, 2);
    grid->addWidget(ageBox, 0, 4);
    grid->addWidget(lName, 1, 0);
    grid->addWidget(lNameEdit, 1, 1);
    grid->addWidget(gender, 2, 0);
    QGroupBox *box = new QGroupBox();
    QVBoxLayout *sexLayout = new QVBoxLayout();
    sexLayout->addWidget(male);
    sexLayout->addWidget(female);
    box->setLayout(sexLayout);
    grid->addWidget(box, 2, 1);
    grid->addWidget(refreshB, 3, 0);
}

void mainWidget::makeVerticalLayout(){
    vBox->addLayout(grid);
    vBox->addWidget(summary);
    vBox->addWidget(finishB);
}
