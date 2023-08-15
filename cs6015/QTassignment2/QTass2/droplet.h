#ifndef DROPLET_H
#define DROPLET_H

#include <QObject>
#include <QGraphicsPixmapItem>
#include <QTimer>
#include <QGraphicsScene>


class Droplet : public QObject, public QGraphicsPixmapItem
{
    Q_OBJECT
public:
    explicit Droplet(QObject *parent = nullptr);

signals:
public slots:
    void fallingDroplet();

};

#endif // DROPLET_H
