package com.example.multimoduletemplate

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(R.layout.fragment_second), Contracts.View {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private val presenter: Contracts.Presenter = ServiceLocator().presenter
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)

        textView = view.findViewById<Button>(R.id.textview_second)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            presenter.fetchData()
        }
    }

    override fun updateView(result: String) {
        textView.text = result
        println(result)
    }
}