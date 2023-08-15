#ifndef GAME1SCENE_H
#define GAME1SCENE_H
#include <QGraphicsScene>
#include<QGraphicsPixmapItem>
#include <QRandomGenerator>
#include <QTouchEvent>

class Bucket;
class game1scene : public QGraphicsScene
{
    Bucket *bucket;

    Q_OBJECT
public:
    game1scene();
signals:
public slots:
    void generateDroplet();
};

#endif // GAME1SCENE_H
