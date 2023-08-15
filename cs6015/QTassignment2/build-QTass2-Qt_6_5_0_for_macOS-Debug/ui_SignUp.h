/********************************************************************************
** Form generated from reading UI file 'SignUp.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SIGNUP_H
#define UI_SIGNUP_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDateEdit>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_SignUp
{
public:
    QWidget *centralwidget;
    QLabel *firstNameLabel;
    QLabel *lastNameLabel;
    QLabel *dobLabel;
    QLabel *pictureLabel;
    QLabel *usernameLabel;
    QLabel *passwordLabel;
    QLabel *secondPassLabel;
    QLineEdit *firstNameLineEdit;
    QLineEdit *lastNameLineEdit;
    QDateEdit *dateEdit;
    QLineEdit *usernameLineEdit;
    QLineEdit *passwordLineEdit;
    QLineEdit *secondPassLineEdit;
    QPushButton *submitPushButton;
    QGraphicsView *profilePictureView;
    QPushButton *browsePushButton;
    QLabel *errorLabel;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *SignUp)
    {
        if (SignUp->objectName().isEmpty())
            SignUp->setObjectName("SignUp");
        SignUp->resize(800, 600);
        centralwidget = new QWidget(SignUp);
        centralwidget->setObjectName("centralwidget");
        firstNameLabel = new QLabel(centralwidget);
        firstNameLabel->setObjectName("firstNameLabel");
        firstNameLabel->setGeometry(QRect(120, 20, 71, 16));
        lastNameLabel = new QLabel(centralwidget);
        lastNameLabel->setObjectName("lastNameLabel");
        lastNameLabel->setGeometry(QRect(120, 60, 71, 16));
        dobLabel = new QLabel(centralwidget);
        dobLabel->setObjectName("dobLabel");
        dobLabel->setGeometry(QRect(120, 100, 81, 16));
        pictureLabel = new QLabel(centralwidget);
        pictureLabel->setObjectName("pictureLabel");
        pictureLabel->setGeometry(QRect(390, 20, 91, 16));
        usernameLabel = new QLabel(centralwidget);
        usernameLabel->setObjectName("usernameLabel");
        usernameLabel->setGeometry(QRect(130, 150, 71, 16));
        passwordLabel = new QLabel(centralwidget);
        passwordLabel->setObjectName("passwordLabel");
        passwordLabel->setGeometry(QRect(130, 190, 71, 16));
        secondPassLabel = new QLabel(centralwidget);
        secondPassLabel->setObjectName("secondPassLabel");
        secondPassLabel->setGeometry(QRect(80, 220, 121, 16));
        firstNameLineEdit = new QLineEdit(centralwidget);
        firstNameLineEdit->setObjectName("firstNameLineEdit");
        firstNameLineEdit->setGeometry(QRect(200, 20, 141, 21));
        lastNameLineEdit = new QLineEdit(centralwidget);
        lastNameLineEdit->setObjectName("lastNameLineEdit");
        lastNameLineEdit->setGeometry(QRect(200, 60, 141, 21));
        dateEdit = new QDateEdit(centralwidget);
        dateEdit->setObjectName("dateEdit");
        dateEdit->setGeometry(QRect(210, 100, 110, 22));
        usernameLineEdit = new QLineEdit(centralwidget);
        usernameLineEdit->setObjectName("usernameLineEdit");
        usernameLineEdit->setGeometry(QRect(200, 150, 141, 21));
        passwordLineEdit = new QLineEdit(centralwidget);
        passwordLineEdit->setObjectName("passwordLineEdit");
        passwordLineEdit->setGeometry(QRect(200, 190, 141, 21));
        passwordLineEdit->setEchoMode(QLineEdit::Password);
        secondPassLineEdit = new QLineEdit(centralwidget);
        secondPassLineEdit->setObjectName("secondPassLineEdit");
        secondPassLineEdit->setGeometry(QRect(200, 220, 141, 21));
        secondPassLineEdit->setEchoMode(QLineEdit::Password);
        submitPushButton = new QPushButton(centralwidget);
        submitPushButton->setObjectName("submitPushButton");
        submitPushButton->setGeometry(QRect(320, 270, 100, 32));
        profilePictureView = new QGraphicsView(centralwidget);
        profilePictureView->setObjectName("profilePictureView");
        profilePictureView->setGeometry(QRect(400, 50, 171, 171));
        profilePictureView->setAutoFillBackground(false);
        browsePushButton = new QPushButton(centralwidget);
        browsePushButton->setObjectName("browsePushButton");
        browsePushButton->setGeometry(QRect(490, 10, 100, 32));
        errorLabel = new QLabel(centralwidget);
        errorLabel->setObjectName("errorLabel");
        errorLabel->setGeometry(QRect(60, 250, 621, 20));
        errorLabel->setLayoutDirection(Qt::LeftToRight);
        errorLabel->setAlignment(Qt::AlignCenter);
        SignUp->setCentralWidget(centralwidget);
        menubar = new QMenuBar(SignUp);
        menubar->setObjectName("menubar");
        menubar->setGeometry(QRect(0, 0, 800, 24));
        SignUp->setMenuBar(menubar);
        statusbar = new QStatusBar(SignUp);
        statusbar->setObjectName("statusbar");
        SignUp->setStatusBar(statusbar);

        retranslateUi(SignUp);

        QMetaObject::connectSlotsByName(SignUp);
    } // setupUi

    void retranslateUi(QMainWindow *SignUp)
    {
        SignUp->setWindowTitle(QCoreApplication::translate("SignUp", "MainWindow", nullptr));
        firstNameLabel->setText(QCoreApplication::translate("SignUp", "First Name:", nullptr));
        lastNameLabel->setText(QCoreApplication::translate("SignUp", "Last Name:", nullptr));
        dobLabel->setText(QCoreApplication::translate("SignUp", "Date of Birth:", nullptr));
        pictureLabel->setText(QCoreApplication::translate("SignUp", "Profile Picture:", nullptr));
        usernameLabel->setText(QCoreApplication::translate("SignUp", "Username:", nullptr));
        passwordLabel->setText(QCoreApplication::translate("SignUp", "Password:", nullptr));
        secondPassLabel->setText(QCoreApplication::translate("SignUp", "Re-enter Password:", nullptr));
        submitPushButton->setText(QCoreApplication::translate("SignUp", "Submit", nullptr));
        browsePushButton->setText(QCoreApplication::translate("SignUp", "Browse...", nullptr));
        errorLabel->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class SignUp: public Ui_SignUp {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SIGNUP_H
