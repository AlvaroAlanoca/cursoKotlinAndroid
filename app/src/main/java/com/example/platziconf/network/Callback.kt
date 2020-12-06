package com.example.platziconf.network

import java.lang.Exception

//se lo hace una interface <T>la palabra T se sua para poder ser un tiopo de distinto cada vez
interface Callback <T>{
    //funciones para ver si falla o no los procesos
    fun onSucces (result : T?)

    fun onFailed(exception: Exception)
}