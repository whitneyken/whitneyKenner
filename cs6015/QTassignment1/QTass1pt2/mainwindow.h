#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QWidget>
#include <QLabel>
#include <QTextEdit>
#include <QRadioButton>
#include <QPushButton>
#include <QGridLayout>
#include <QGroupBox>

class mainWindow : public QWidget
{
public:
    QLabel *expression;
    QLabel *result;
    QTextEdit *exprBox;
    QTextEdit *resultBox;
    QRadioButton *interp;
    QRadioButton *prettyPrint;
    QPushButton *submitB;
    QPushButton *resetB;
    QGridLayout *grid;



    Q_OBJECT
public:
    explicit mainWindow(QWidget *parent = nullptr);

signals:
    public slots:
    void formatLayout();
    void submitInput();
    void resetWindow();

};

#endif // MAINWINDOW_H
