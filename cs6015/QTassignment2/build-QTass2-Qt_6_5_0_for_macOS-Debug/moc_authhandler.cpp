/****************************************************************************
** Meta object code from reading C++ file 'authhandler.h'
**
** Created by: The Qt Meta Object Compiler version 68 (Qt 6.5.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../../../finalQT/PeakAndConsume/finalQT/QTass2/authhandler.h"
#include <QtNetwork/QSslError>
#include <QtCore/qmetatype.h>

#if __has_include(<QtCore/qtmochelpers.h>)
#include <QtCore/qtmochelpers.h>
#else
QT_BEGIN_MOC_NAMESPACE
#endif


#include <memory>

#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'authhandler.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 68
#error "This file was generated using the moc from 6.5.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

#ifndef Q_CONSTINIT
#define Q_CONSTINIT
#endif

QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
QT_WARNING_DISABLE_GCC("-Wuseless-cast")
namespace {

#ifdef QT_MOC_HAS_STRINGDATA
struct qt_meta_stringdata_CLASSAuthHandlerENDCLASS_t {};
static constexpr auto qt_meta_stringdata_CLASSAuthHandlerENDCLASS = QtMocHelpers::stringData(
    "AuthHandler",
    "pullUserData",
    "",
    "putUserData",
    "postNewUser",
    "highScoreRequest",
    "networkReplyReadyRead",
    "userDataPullRequest",
    "userDataAddRequest",
    "userDataPushRequest"
);
#else  // !QT_MOC_HAS_STRING_DATA
struct qt_meta_stringdata_CLASSAuthHandlerENDCLASS_t {
    uint offsetsAndSizes[20];
    char stringdata0[12];
    char stringdata1[13];
    char stringdata2[1];
    char stringdata3[12];
    char stringdata4[12];
    char stringdata5[17];
    char stringdata6[22];
    char stringdata7[20];
    char stringdata8[19];
    char stringdata9[20];
};
#define QT_MOC_LITERAL(ofs, len) \
    uint(sizeof(qt_meta_stringdata_CLASSAuthHandlerENDCLASS_t::offsetsAndSizes) + ofs), len 
Q_CONSTINIT static const qt_meta_stringdata_CLASSAuthHandlerENDCLASS_t qt_meta_stringdata_CLASSAuthHandlerENDCLASS = {
    {
        QT_MOC_LITERAL(0, 11),  // "AuthHandler"
        QT_MOC_LITERAL(12, 12),  // "pullUserData"
        QT_MOC_LITERAL(25, 0),  // ""
        QT_MOC_LITERAL(26, 11),  // "putUserData"
        QT_MOC_LITERAL(38, 11),  // "postNewUser"
        QT_MOC_LITERAL(50, 16),  // "highScoreRequest"
        QT_MOC_LITERAL(67, 21),  // "networkReplyReadyRead"
        QT_MOC_LITERAL(89, 19),  // "userDataPullRequest"
        QT_MOC_LITERAL(109, 18),  // "userDataAddRequest"
        QT_MOC_LITERAL(128, 19)   // "userDataPushRequest"
    },
    "AuthHandler",
    "pullUserData",
    "",
    "putUserData",
    "postNewUser",
    "highScoreRequest",
    "networkReplyReadyRead",
    "userDataPullRequest",
    "userDataAddRequest",
    "userDataPushRequest"
};
#undef QT_MOC_LITERAL
#endif // !QT_MOC_HAS_STRING_DATA
} // unnamed namespace

Q_CONSTINIT static const uint qt_meta_data_CLASSAuthHandlerENDCLASS[] = {

 // content:
      11,       // revision
       0,       // classname
       0,    0, // classinfo
       8,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       4,       // signalCount

 // signals: name, argc, parameters, tag, flags, initial metatype offsets
       1,    0,   62,    2, 0x06,    1 /* Public */,
       3,    0,   63,    2, 0x06,    2 /* Public */,
       4,    0,   64,    2, 0x06,    3 /* Public */,
       5,    0,   65,    2, 0x06,    4 /* Public */,

 // slots: name, argc, parameters, tag, flags, initial metatype offsets
       6,    0,   66,    2, 0x0a,    5 /* Public */,
       7,    0,   67,    2, 0x0a,    6 /* Public */,
       8,    0,   68,    2, 0x0a,    7 /* Public */,
       9,    0,   69,    2, 0x0a,    8 /* Public */,

 // signals: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

Q_CONSTINIT const QMetaObject AuthHandler::staticMetaObject = { {
    QMetaObject::SuperData::link<QObject::staticMetaObject>(),
    qt_meta_stringdata_CLASSAuthHandlerENDCLASS.offsetsAndSizes,
    qt_meta_data_CLASSAuthHandlerENDCLASS,
    qt_static_metacall,
    nullptr,
    qt_incomplete_metaTypeArray<qt_meta_stringdata_CLASSAuthHandlerENDCLASS_t,
        // Q_OBJECT / Q_GADGET
        QtPrivate::TypeAndForceComplete<AuthHandler, std::true_type>,
        // method 'pullUserData'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'putUserData'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'postNewUser'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'highScoreRequest'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'networkReplyReadyRead'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'userDataPullRequest'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'userDataAddRequest'
        QtPrivate::TypeAndForceComplete<void, std::false_type>,
        // method 'userDataPushRequest'
        QtPrivate::TypeAndForceComplete<void, std::false_type>
    >,
    nullptr
} };

void AuthHandler::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        auto *_t = static_cast<AuthHandler *>(_o);
        (void)_t;
        switch (_id) {
        case 0: _t->pullUserData(); break;
        case 1: _t->putUserData(); break;
        case 2: _t->postNewUser(); break;
        case 3: _t->highScoreRequest(); break;
        case 4: _t->networkReplyReadyRead(); break;
        case 5: _t->userDataPullRequest(); break;
        case 6: _t->userDataAddRequest(); break;
        case 7: _t->userDataPushRequest(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        {
            using _t = void (AuthHandler::*)();
            if (_t _q_method = &AuthHandler::pullUserData; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 0;
                return;
            }
        }
        {
            using _t = void (AuthHandler::*)();
            if (_t _q_method = &AuthHandler::putUserData; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 1;
                return;
            }
        }
        {
            using _t = void (AuthHandler::*)();
            if (_t _q_method = &AuthHandler::postNewUser; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 2;
                return;
            }
        }
        {
            using _t = void (AuthHandler::*)();
            if (_t _q_method = &AuthHandler::highScoreRequest; *reinterpret_cast<_t *>(_a[1]) == _q_method) {
                *result = 3;
                return;
            }
        }
    }
    (void)_a;
}

const QMetaObject *AuthHandler::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *AuthHandler::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_CLASSAuthHandlerENDCLASS.stringdata0))
        return static_cast<void*>(this);
    return QObject::qt_metacast(_clname);
}

int AuthHandler::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QObject::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 8)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 8;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 8)
            *reinterpret_cast<QMetaType *>(_a[0]) = QMetaType();
        _id -= 8;
    }
    return _id;
}

// SIGNAL 0
void AuthHandler::pullUserData()
{
    QMetaObject::activate(this, &staticMetaObject, 0, nullptr);
}

// SIGNAL 1
void AuthHandler::putUserData()
{
    QMetaObject::activate(this, &staticMetaObject, 1, nullptr);
}

// SIGNAL 2
void AuthHandler::postNewUser()
{
    QMetaObject::activate(this, &staticMetaObject, 2, nullptr);
}

// SIGNAL 3
void AuthHandler::highScoreRequest()
{
    QMetaObject::activate(this, &staticMetaObject, 3, nullptr);
}
QT_WARNING_POP
