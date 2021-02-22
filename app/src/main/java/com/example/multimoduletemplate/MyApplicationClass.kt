package com.example.multimoduletemplate

import android.app.Application
import com.example.multimoduletemplate.datadog.init

class MyApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        initDataDog()
    }

    private fun initDataDog() {
        init(this)
    }
}
