package com.example.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import java.util.ArrayList

class SpeakerAdapter(val speakersListener: SpeakersListener)
    :RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    var listSpeaker = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_speaker, parent, false))

    override fun getItemCount() = listSpeaker.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = listSpeaker[position]

        holder.tvNombreSpeaker.text = speaker.name
        holder.tvSpeakerWork.text = speaker.workplace
        //se ultilizara un nuevo componente GLIDE q se debe adicionar en el gradle del modulo ya q la imagen se descargara de un urlk
        // primero enviamos el contexto
        Glide.with(holder.itemView.context)
                //donde esta la URL de la imagen
            .load(speaker.image)
                //convertimos la imgaen en circular
            .apply(RequestOptions.circleCropTransform())
                //donde la colocaremos
            .into(holder.ivSpeakerImage)
//el evento lick
        holder.itemView.setOnClickListener{
            speakersListener.onSpeakerClicked(speaker,position)
        }

        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
        val tvNombreSpeaker = itemView.findViewById<TextView>(R.id.tvItemSpeakerNombre)
        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerTrabajo)

    }
    fun updateData(data: List<Speaker>) {
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()

    }
}