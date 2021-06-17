package com.vs.android11permissionshandling

import android.app.Application
import com.chibatching.kotpref.Kotpref

class SampleApp :Application(){

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
    }
}