#ifndef BUCKET_H
#define BUCKET_H

#include <QObject>
#include <QGraphicsPixmapItem>
#include <QKeyEvent>

class Bucket : public QObject,  public QGraphicsPixmapItem
{
    Q_OBJECT
public:
    explicit Bucket(QObject *parent = nullptr);

signals:
public slots:
    void keyPressEvent(QKeyEvent *event);

};

#endif // BUCKET_H
