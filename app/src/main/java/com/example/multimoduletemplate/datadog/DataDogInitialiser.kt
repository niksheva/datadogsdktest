package com.example.multimoduletemplate.datadog

import android.content.Context
import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.DatadogConfig
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.rum.tracking.MixedViewTrackingStrategy

fun init(context: Context) {
    val clientToken = "pub3ccc5b7328b73b6309aa24ec8f4af637"
    val environmentName = "DepopAndroidTest"
    val applicationId = "7238168c-223e-4225-a261-3fc379cd375e"
    val config = DatadogConfig.Builder(
        clientToken,
        environmentName,
        applicationId
    )
        .trackInteractions()
        .setLogsEnabled(true)
        .setRumEnabled(true)
        .useViewTrackingStrategy(MixedViewTrackingStrategy(false))
        .build()

    Datadog.initialize(context, config)
    Datadog.setVerbosity(Log.VERBOSE)

    GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
}
