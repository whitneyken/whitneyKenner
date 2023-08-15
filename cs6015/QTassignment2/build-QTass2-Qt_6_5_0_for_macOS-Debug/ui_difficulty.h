/********************************************************************************
** Form generated from reading UI file 'difficulty.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DIFFICULTY_H
#define UI_DIFFICULTY_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QRadioButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Difficulty
{
public:
    QRadioButton *easyBtn;
    QRadioButton *normalBtn;
    QRadioButton *hardBtn;
    QPushButton *pushButton;

    void setupUi(QWidget *Difficulty)
    {
        if (Difficulty->objectName().isEmpty())
            Difficulty->setObjectName("Difficulty");
        Difficulty->resize(400, 300);
        easyBtn = new QRadioButton(Difficulty);
        easyBtn->setObjectName("easyBtn");
        easyBtn->setGeometry(QRect(160, 80, 99, 20));
        normalBtn = new QRadioButton(Difficulty);
        normalBtn->setObjectName("normalBtn");
        normalBtn->setGeometry(QRect(160, 110, 99, 20));
        hardBtn = new QRadioButton(Difficulty);
        hardBtn->setObjectName("hardBtn");
        hardBtn->setGeometry(QRect(160, 140, 99, 20));
        pushButton = new QPushButton(Difficulty);
        pushButton->setObjectName("pushButton");
        pushButton->setGeometry(QRect(150, 180, 100, 32));

        retranslateUi(Difficulty);

        QMetaObject::connectSlotsByName(Difficulty);
    } // setupUi

    void retranslateUi(QWidget *Difficulty)
    {
        Difficulty->setWindowTitle(QCoreApplication::translate("Difficulty", "Form", nullptr));
        easyBtn->setText(QCoreApplication::translate("Difficulty", "Easy", nullptr));
        normalBtn->setText(QCoreApplication::translate("Difficulty", "Normal", nullptr));
        hardBtn->setText(QCoreApplication::translate("Difficulty", "Hard", nullptr));
        pushButton->setText(QCoreApplication::translate("Difficulty", "GO!", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Difficulty: public Ui_Difficulty {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DIFFICULTY_H
