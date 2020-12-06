package com.example.platziconf.view.adapter

import com.example.platziconf.model.Conference


//interfaz para tener el evento onclick
interface ScheduleListener {
//funcion q cuando hagamos click tenga su accion, recibimos un objeto de tipo conference y su posicion
    fun onConferenceClicked(conference: Conference, position: Int)
//importante importar la conference de nuestro modelo
}