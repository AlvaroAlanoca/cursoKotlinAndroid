package com.example.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import kotlin.collections.ArrayList

//hacemos herencia de la clase RecyclerView.Adapter y que sea de tipo ScheduleAdapter
class ScheduleAdapter(val scheduleListener: ScheduleListener) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    //creamos una lista  de tipo conference, es donde se almacenaran los elementos
    var listConference = ArrayList<Conference>()

    //generamos con el IDE las funciones necesarias para un recyclerview q es un modelador de datos
    //estructura basica de cualquier recyclerView

    //metodo q permite crear el diseño q se utilizara para la vista
    //la convertimos en una funcion en linea, en esta funcion llamamos al xml de item schedule
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        //con esta linea es decir cual es el archivo para el diseño de un item del recyclerView
        ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule,parent,false))

    //este metodo es para ver cuantos elementos tenemos
    //la convertmimos en una funcion en linea q le enviamos los elementos q vayamos a tener
    override fun getItemCount() = listConference.size
    //recibe un item view, hereda de recylcer.viewholder

       //los datos q vayamos a cargar, q informacion tendremos
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        //creamos un objeto conferencia, tendrmos un elemento en lka psicion q se encuentra
         val conference =  listConference[position] as Conference
        //creamos instancias de la clase Conference del modelo
         holder.tvConferenceName.text = conference.title
         holder.tvConferenceSpeaker.text =conference.speaker
         holder.tvConferenceTag.text = conference.tag

         val simpleDateformat = SimpleDateFormat( "HH:mm")
         val simpleDateformatAMPM = SimpleDateFormat("a")

         val cal = Calendar.getInstance()
         cal.time = conference.datetime
         val hourFormat =  simpleDateformat.format(conference.datetime)

         holder.tvConferenceHour.text = hourFormat
         holder.tvConferenceAMPM.text = simpleDateformatAMPM.format(conference.datetime).toUpperCase()

         holder.itemView.setOnClickListener{
             scheduleListener.onConferenceClicked(conference,position)
         }



    }




    //funcion q reciba datos de conferencia, limpia la lista, adiciona los datos enviados, y notifica los datos modificados
fun updateData (data: List<Conference>){
    listConference.clear()
    listConference.addAll(data)
    notifyDataSetChanged()

}

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        //enlazamos cada uno de los elementos
        //creamos variable para poder tener acceso al xml de item_schedule
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemsSchduelConferenceName)
        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }
}