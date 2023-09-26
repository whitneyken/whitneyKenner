package com.example.lab4

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ChuckApplication : Application() {
    val scope = CoroutineScope(SupervisorJob())
    val db by lazy {ChuckDatabase.getDatabase(applicationContext)}
    val chuckRepository by lazy {ChuckRepository(scope, db.chuckDao())}
}