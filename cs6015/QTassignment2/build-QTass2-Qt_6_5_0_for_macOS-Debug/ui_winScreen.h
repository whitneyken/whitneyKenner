/********************************************************************************
** Form generated from reading UI file 'winScreen.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_WINSCREEN_H
#define UI_WINSCREEN_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_winScreen
{
public:
    QWidget *centralwidget;
    QPushButton *pushButton;
    QLabel *label;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *winScreen)
    {
        if (winScreen->objectName().isEmpty())
            winScreen->setObjectName("winScreen");
        winScreen->resize(800, 600);
        centralwidget = new QWidget(winScreen);
        centralwidget->setObjectName("centralwidget");
        pushButton = new QPushButton(centralwidget);
        pushButton->setObjectName("pushButton");
        pushButton->setGeometry(QRect(350, 120, 100, 32));
        label = new QLabel(centralwidget);
        label->setObjectName("label");
        label->setGeometry(QRect(310, 60, 171, 61));
        QFont font;
        font.setFamilies({QString::fromUtf8("Academy Engraved LET")});
        font.setPointSize(36);
        label->setFont(font);
        label->setAlignment(Qt::AlignCenter);
        winScreen->setCentralWidget(centralwidget);
        menubar = new QMenuBar(winScreen);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 800, 24));
        winScreen->setMenuBar(menubar);
        statusbar = new QStatusBar(winScreen);
        statusbar->setObjectName("statusbar");
        winScreen->setStatusBar(statusbar);

        retranslateUi(winScreen);

        QMetaObject::connectSlotsByName(winScreen);
    } // setupUi

    void retranslateUi(QMainWindow *winScreen)
    {
        winScreen->setWindowTitle(QCoreApplication::translate("winScreen", "MainWindow", nullptr));
        pushButton->setText(QCoreApplication::translate("winScreen", "Past Scores", nullptr));
        label->setText(QCoreApplication::translate("winScreen", "You Won!", nullptr));
    } // retranslateUi

};

namespace Ui {
    class winScreen: public Ui_winScreen {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_WINSCREEN_H
