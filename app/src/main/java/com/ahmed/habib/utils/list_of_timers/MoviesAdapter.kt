package com.ahmed.habib.utils.list_of_timers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import  com.ahmed.habib.utils.R
import com.ahmed.habib.utils.list_of_timers.MoviesAdapter.MovieViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MoviesAdapter(private val movies: List<MovieModel>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val desc: TextView = itemView.findViewById(R.id.description)
        val date: TextView = itemView.findViewById(R.id.dateValue)
        val time: TextView = itemView.findViewById(R.id.timeValue)
        val waitingTime: TextView = itemView.findViewById(R.id.waitingTime)

        private var timerJob: Job? = null

        fun bind(movie: MovieModel) {
            name.text = movie.name
            desc.text = movie.description
            date.text = movie.date
            time.text = movie.time

            startTimer(movie.date, movie.time)
        }

        private fun startTimer(date: String, time: String) {
            timerJob?.cancel()

            val currentTime = System.currentTimeMillis()
            val movieDateTime = getDateTimeInMillis(date, time)

            val timeDiff = movieDateTime - currentTime

            if (timeDiff > 0) {
                timerJob = CoroutineScope(Dispatchers.Main).launch {
                    while (true) {
                        val remainingTime = movieDateTime - System.currentTimeMillis()

                        if (remainingTime <= 0) {
                            waitingTime.text = context?.getString(R.string.now_playing)
                            break
                        } else {
                            waitingTime.text = formatTime(remainingTime)
                        }

                        delay(1000)
                    }
                }
            } else {
                waitingTime.text = context?.getString(R.string.now_playing)
            }
        }

        private fun getDateTimeInMillis(date: String, time: String): Long {
            val dateTimeString = "$date $time"
            val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
            return formatter.parse(dateTimeString)?.time ?: 0L
        }

        private fun formatTime(timeInMillis: Long): String {
            val seconds = (timeInMillis / 1000) % 60
            val minutes = (timeInMillis / (1000 * 60)) % 60
            val hours = (timeInMillis / (1000 * 60 * 60)) % 24
            val days = (timeInMillis / (1000 * 60 * 60 * 24))

            return when {
                days > 0 -> "$days days, $hours hours"
                hours > 0 -> "$hours hours, $minutes min"
                minutes > 0 -> "$minutes min, $seconds sec"
                else -> "$seconds sec"
            }
        }
    }
}