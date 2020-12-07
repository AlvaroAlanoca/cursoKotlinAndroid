package com.example.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platziconf.model.Conference
import com.example.platziconf.network.Callback
import com.example.platziconf.network.FirestoreService
import java.lang.Exception

//ersta clase debe heredar de la clase ViewModel
class ScheduleViewModel : ViewModel (){
    //creamos una instancia de firebase y nos da accso a las funciones creadas
    val firestoreService = FirestoreService()
    //creamos una variable q tendra acceso a la lista q sera almacenada
    var listSchedule:MutableLiveData<List<Conference>> = MutableLiveData()
    //creamos una variable boolenaa q nos permite actualizar nuestra UI de carga
    var isLoading = MutableLiveData<Boolean>()

    //funcion
    fun refresh(){
        getScheduleFromFirebase()
        }

    fun getScheduleFromFirebase(){
        //llamamos a la funcion de firestoreservice
        //creamos un object para poder contener la lista en el callback
        firestoreService.getSchedule(object :Callback<List<Conference>>{
            //creamos el metodo si la respuesta fue exitosa o fallida
            override fun onSucces(result: List<Conference>?) {
                listSchedule.postValue(result)
                //creamos una funcion para saber si finalizamos el proceso
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }

        })
    }
    fun processFinished(){
        //nos permite saber cuando nuestro mutablelivedata termina su proceso sea exitoso o fallido
        isLoading.value =  true
    }
}