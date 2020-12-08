package com.example.platziconf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.item_schedule.*
import java.text.SimpleDateFormat

//cambiamos la herencia de fragment a dialogfrgment
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //aca llamamos al estilo q usaremos para el dialog fragment
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //realizamos el enlace del toolbar, aca le agregamos un icocno al toolbar con el navigationicon
        //con el contextcompat cargamos el icono para q sea usado
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
       //asignamos color al titulo
        toolbarConference.setTitleTextColor(Color.WHITE)
        //le decimos q al hacer click se cierre
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }
        //obtenemos el objeto q enviamos, desde la anterior fragment y lo casteamos como objeto Conference
        val conference =  arguments?.getSerializable("conference")as Conference
        //podemos mostrar el objeto asi q sacamos el titulo y en la pantalla se mostrara el mismo
        toolbarConference.title =  conference.title
        tvItemScheduleTituloConferencia.text = conference.title
        //convertimos la fecha en un formato q se pueda mostrar
        val pattern =  "dd/MM/yyyy hh:mm a"
        val simpleDf = SimpleDateFormat(pattern)
        val date =  simpleDf.format(conference.datetime)

       tvDetailConferenceHour.text =  date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceTag.text = conference.tag
        tvDetailConferenceDescription.text = conference.description
    }
//onstart pertenece al ciclo de vida de cualquier cativity o fragmento
    override fun onStart() {
        super.onStart()
        //aqui sobrecargamos la funcion onstart para poder colocar los margenes (la primera parte es el ancho, la segunda es el alto )
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}