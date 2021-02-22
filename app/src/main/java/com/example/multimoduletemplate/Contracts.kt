package com.example.multimoduletemplate

import com.example.multimoduletemplate.model.User

interface Contracts {
    interface View {
        fun updateView(result: String)
    }

    interface Presenter {
        fun bindView(view: Contracts.View)
        fun fetchData()
    }

    interface Repository {
        suspend fun fetchData(): List<User>
    }
}