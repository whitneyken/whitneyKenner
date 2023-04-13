 #include <QApplication>
#include <QPushButton>
#include "mainWindow.h"
int main(int argc, char **argv) {
    QApplication app (argc, argv);
    mainWindow *widget = new mainWindow();
    widget->resize(800, widget->height() * 1.5);
    widget->show();

    return app.exec();
}
