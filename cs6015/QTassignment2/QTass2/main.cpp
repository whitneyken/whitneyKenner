#include <QGraphicsView>
#include "game1scene.h"
#include <QGraphicsScene>

#include <QApplication>
#include <QPushButton>
int main(int argc, char **argv) {

    QApplication app (argc, argv);
    game1scene *background = new game1scene();
    QGraphicsView *view = new QGraphicsView();
    view->setScene(background);
    view->setFixedSize(910, 512);
    view->setHorizontalScrollBarPolicy((Qt::ScrollBarAlwaysOff));
    view->setVerticalScrollBarPolicy((Qt::ScrollBarAlwaysOff));
    view->show();



    return app.exec();
}
