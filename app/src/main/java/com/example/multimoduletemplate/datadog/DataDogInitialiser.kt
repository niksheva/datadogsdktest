package com.example.multimoduletemplate.datadog

import android.content.Context
import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.DatadogConfig
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.rum.tracking.MixedViewTrackingStrategy

fun init(context: Context) {
    val clientToken = "xx"
    val environmentName = "AndroidTest"
    val applicationId = "x"

    val config = DatadogConfig.Builder(
        clientToken,
        environmentName,
        applicationId
    )
        .trackInteractions()
        .setLogsEnabled(true)
        .setRumEnabled(true)
        .useViewTrackingStrategy(MixedViewTrackingStrategy(true))
        .build()

    Datadog.initialize(context, TrackingConsent.GRANTED, config)
    Datadog.setVerbosity(Log.VERBOSE)

    GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
}
