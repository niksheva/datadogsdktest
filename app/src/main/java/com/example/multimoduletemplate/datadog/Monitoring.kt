package com.example.multimoduletemplate.datadog

import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumActionType.CUSTOM

private const val SUCCESS = "success"
private const val FAILURE = "failure"
private const val LAYER = "layer"
private const val TIME = "time"
private const val CLASS_NAME = "className"
private const val RESULT = "result"

fun incrementMetric(sliName: String, layer: String, time: Long, className: String, success: Boolean) {
    val attributes = hashMapOf(
        Pair(LAYER, layer),
        Pair(CLASS_NAME, className),
        Pair(TIME, time),
        Pair(RESULT, if (success) SUCCESS else FAILURE)
    )
    println("DataDogMetrics: $sliName - $className - $time - $success")
    // CURRENTLY RESOLVING ISSUE: it seems like not all the events are showing inn the dashboard
    GlobalRum.get().addUserAction(CUSTOM, sliName, attributes)
}
