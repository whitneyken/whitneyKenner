#include "game1scene.h"
#include "bucket.h"
#include "droplet.h"

game1scene::game1scene()
{
    bucket = new Bucket();
    bucket->setFlag(QGraphicsItem::ItemIsFocusable);
    bucket->setFocus();
    bucket->setPos(369, 355);
    QTimer *timerDrop = new QTimer(this);
    connect(timerDrop, &QTimer::timeout, this, &game1scene::generateDroplet);
    timerDrop->start(100);
    this->addItem(bucket);
    this->setBackgroundBrush(QBrush(QImage(":/images/background.png").scaledToHeight(512) .scaledToWidth(910)));
    this->setSceneRect(0,0,908,510);
    //Qt::WA_AcceptTouchEvents(false);


}

void game1scene::generateDroplet(){
    Droplet *droplet = new Droplet();
    quint32 v = QRandomGenerator::global()->bounded(0, 900);
    droplet->setPos(v, 0);
    this->addItem(droplet);
}
