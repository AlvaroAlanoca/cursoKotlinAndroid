package com.example.platziconf.network

import com.example.platziconf.model.Conference
import com.example.platziconf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val CONFERENCES_COLLECTION_NAME ="conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    //creamos la instacia incial de firestore, esta es la conexion directa con la base de datos
    val firebaseFirestore = FirebaseFirestore.getInstance()
    //la configuracion para poder tener los datos en modo offline o persistirlos dentro de la aplcacion
    val settings =  FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    //inicializamos en kotlin ya tenemos q los datos descargados una vez sigan persistentes aunque estemos sin conexion
    init {
        //asignar los datos en modo offline los datos que descargamos la primera vez
        firebaseFirestore.firestoreSettings = settings
    }
    //funcion de conexion con parametro callback para recibir datos en una lista de objetos tipo speaker
    //creamos la interface callback para poder usar las funcion de onsucces y on onfailed
    fun getSpeakers (callback: Callback <List <Speaker>>){
        //realizamos la conexion a la coleccion
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
                //una vez llamada a la coleccion ordenamos los datos para modificar como seran mostrados
            .orderBy("category")
            .get()
                //comprobamos q si estemos recibiendo datos
            .addOnSuccessListener { result->
                for (doc in result){
                    //obtenemos los objetos necesarios de speaker y asi tener todos los spaekrs de la coleccion
                    val list =  result.toObjects(Speaker::class.java)
                    //enviarlo al callback
                    callback.onSucces(list)
                    break

                }
            }

    }
        //recibimos una lista de conferencias
    fun getSchedule (callback: Callback<List<Conference>>){

            //realizamos la conexion a la coleccion
            firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
             //no lo ordenamos
                .get()
                .addOnSuccessListener { result->
                    for (doc in result){
                        val list =  result.toObjects(Conference::class.java)
                        //enviarlo al callback
                        callback.onSucces(list)
                        break

                    }
                }

    }

}