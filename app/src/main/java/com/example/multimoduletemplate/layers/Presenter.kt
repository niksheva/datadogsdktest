package com.example.multimoduletemplate.layers

import com.example.multimoduletemplate.Contracts
import com.example.multimoduletemplate.datadog.incrementMetric
import com.example.multimoduletemplate.helpers.CoroutineDispatcherFactory
import com.example.multimoduletemplate.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class Presenter(
    private val dispatcher: CoroutineDispatcherFactory,
    private val apiHelper: ApiHelper
) : Contracts.Presenter, CoroutineScope {

    private lateinit var view: Contracts.View
    private var counter = 0

    private var coroutineJob: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcher.IODispatcher + coroutineJob

    override fun bindView(view: Contracts.View) {
        this.view = view
    }

    override fun fetchData(): Unit =
        withMetrics("metricsName1, ", "presenter", this::class.java.simpleName) {
            launch(dispatcher.IODispatcher) {
                val result = apiHelper.getUsers()

                withContext(dispatcher.UIDispatcher) {
                    if (result is Result.Success) {
                        view.updateView("${result.users.first().email} ${++counter}")
                    }
                }
            }
        }

    private fun <V> withMetrics(
        sliName: String,
        layer: String,
        className: String,
        block: () -> V
    ): V {
        val startTime = System.currentTimeMillis()
        val result = block()
        val endTime = System.currentTimeMillis()

        val isSuccess = result is Result.Success

        incrementMetric(sliName, layer, endTime - startTime, className, isSuccess)

        return result
    }

}

