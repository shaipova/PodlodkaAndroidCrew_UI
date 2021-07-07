package com.example.podlodkauiclassic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SessionAdapter(val list: MutableList<Session>, val viewModel: SessionViewModel) :
    RecyclerView.Adapter<SessionAdapter.ViewHolder>() {

    var listFavSession: MutableList<Session> = viewModel.emptyFavList

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val speaker = itemView.findViewById<TextView>(R.id.speaker_session)
        val timeInterval = itemView.findViewById<TextView>(R.id.time_interval_session)

        val photo = itemView.findViewById<CircleImageView>(R.id.photo_session)
        val description = itemView.findViewById<TextView>(R.id.description_session)
        val favButton = itemView.findViewById<ImageButton>(R.id.fav_button)
        var isFav = false
        val card = itemView


        fun bindViews(session: Session) {
            speaker.text = session.speaker
            timeInterval.text = session.timeInterval
            description.text = session.description

            val picasso = Picasso.get()

            picasso.load(session.imageUrl)
                .into(photo)

            card.setOnClickListener {
                onItemClickListener?.let { it(session) }
            }

            favButton.setOnClickListener { onFavClick() }
        }


        fun onFavClick() {
            val session = list[adapterPosition]

            if (!isFav) {
                if (listFavSession.size < 3) {
                    favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                    isFav = true
                    listFavSession.add(session)
                    viewModel.favListChange(listFavSession)
                } else viewModel.showSnackbar()

            } else {
                favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                isFav = false
                listFavSession.remove(session)
                viewModel.favListChange(listFavSession)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.session_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindViews(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private var onItemClickListener: ((Session) -> Unit)? = null
    fun setOnItemClickListener(listener: (Session) -> Unit) {
        onItemClickListener = listener
    }
}