package com.ahmed.habib.utils.list_of_timers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.habib.utils.R

class ListOfTimersActivity : AppCompatActivity() {

    private val adapter by lazy { MoviesAdapter(movies) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.moviesRvId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_timers)

        init()
    }

    private fun init() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView.adapter = null
    }
}