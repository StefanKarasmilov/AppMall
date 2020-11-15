package com.example.uvotematter

import android.app.Application
import timber.log.Timber

class VoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.asTree())
    }

}