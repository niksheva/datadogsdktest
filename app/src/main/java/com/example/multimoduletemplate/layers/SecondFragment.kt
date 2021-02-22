package com.example.multimoduletemplate.layers

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.datadog.android.rum.GlobalRum
import com.example.multimoduletemplate.Contracts
import com.example.multimoduletemplate.R
import com.example.multimoduletemplate.helpers.ServiceLocator

class SecondFragment : Fragment(R.layout.fragment_second),
    Contracts.View {

    companion object {
        fun newInstance() =
            SecondFragment()
    }

    private val presenter: Contracts.Presenter = ServiceLocator().presenter
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)

        textView = view.findViewById<Button>(R.id.textview_second)

        GlobalRum.get().startView("view fragment", "second fragment")

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            presenter.fetchData()
        }
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        GlobalRum.get().startView("destroy fragment", "second fragment")
    }

    override fun updateView(result: String) {
        textView.text = result
        println(result)
    }
}