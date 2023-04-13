#ifndef MAINWIDGET_H
#define MAINWIDGET_H

#include <QWidget>
#include <QLabel>
#include <QLineEdit>
#include <QDialogButtonBox>
#include <QRadioButton>
#include <QPushButton>
#include <QTextEdit>
#include <QSpinBox>
#include <QVBoxLayout>
#include <QGroupBox>

class mainWidget : public QWidget
{
    Q_OBJECT
public:
    explicit mainWidget(QWidget *parent = nullptr);
    QLabel *fName;
    QLabel *lName;
    QLineEdit *fNameEdit;
    QLineEdit *lNameEdit;
    QLabel *age;
    QSpinBox *ageBox;
    QLabel *gender;
    QRadioButton *male;
    QRadioButton *female;
    QPushButton *refreshB;
    QTextEdit *summary;
    QPushButton *finishB;
    QVBoxLayout *vBox;
    QGridLayout *grid;


signals:
public slots:
    void makeLayout();
    void makeVerticalLayout();
    void fillSummary();
    void refreshFields();
};

#endif // MAINWIDGET_H
