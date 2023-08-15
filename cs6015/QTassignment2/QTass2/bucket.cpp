#include "bucket.h"


Bucket::Bucket(QObject *parent)
    : QObject{parent}
{
    this->setPixmap((QPixmap(":/images/bucket.png")).scaled(150,150));
}

void Bucket::keyPressEvent(QKeyEvent *event){
    if(event->key() == Qt::Key_Right && this->x() < 785){
        this->setPos(this->x() + 12, this->y());
    }
    if(event->key() == Qt::Key_Left && this->x() > -20){
        this->setPos(this->x() - 12, this->y());
    }
}
