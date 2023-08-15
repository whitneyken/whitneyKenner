#include "droplet.h"

Droplet::Droplet(QObject *parent)
    : QObject{parent}
{
    this->setPixmap((QPixmap(":/images/water.gif")).scaled(30,60));
    QTimer *timerDrop = new QTimer(this);
    connect(timerDrop, &QTimer::timeout, this, &Droplet::fallingDroplet);
    timerDrop->start(100);
}

void Droplet::fallingDroplet(){
    this->setPos(this->x(), this->y() + 10);
    if(this->y() > 510){
        scene()->removeItem(this);
        delete this;
    }else if(!collidingItems().isEmpty()){
        scene()->removeItem(this);
        delete this;
    }
}
