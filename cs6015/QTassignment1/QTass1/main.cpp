 #include <QApplication>
#include <QPushButton>
#include "mainwidget.h"
int main(int argc, char **argv) {
    QApplication app (argc, argv);
    mainWidget *widget = new mainWidget();
    widget->show();

    return app.exec();
}
