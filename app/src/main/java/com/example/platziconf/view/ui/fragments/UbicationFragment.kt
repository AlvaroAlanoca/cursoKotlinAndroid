package com.example.platziconf.view.ui.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


//creamos dependencia a onmapdeadycallback implementamos los metodos al igual q onmarkerclicklistener
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        val ubication = Ubication ()
        //para poder hacer un acercamiento
        val zoom = 16f
        //importar este import com.google.android.gms.maps.model.LatLng para poder darle la latitud y longitud
        //aca le damos la ubicacion
        val centerMap = LatLng(ubication.latitude, ubication.longitude)
        //en googlemap podemos modificar nuestro mapa y usamos la funcion animatecamera con los atributos q le dimos
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))
        //usamos un marcador para poder colocar el elemento que queremos en ese lugar
        val centerMark = LatLng(ubication.latitude, ubication.longitude)
        val markerOptions =  MarkerOptions()
        // MarkerOptions nos permite genrar la posicion y tambien el nombre
        markerOptions.position(centerMark)
        markerOptions.title("Platzi Conf 2019")
        //creamos una variable para poder acceder a nuestra imagen en verz del marcados por defecto de maps
        //la conversion es importante para poder cargar cualquier otra imagen
        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) } as BitmapDrawable
        //creamos la variable para q la imagen agregada no llegue en tamaño real
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150,150, false)
        //para poder agregar el mapa usamos el markeroptions
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        //agregamos el marcador al mapa
        googleMap?.addMarker(markerOptions)
        //al hacer esta accion el marcador q tenemos en el mpaa mandara a la funcion onmarkerclick
        googleMap?.setOnMarkerClickListener (this)

        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context,R.raw.custom_map))
    }
//para poder acceder a esta funcion desde el mapa debemos usar setonmarkerclick
    override fun onMarkerClick(p0: Marker?): Boolean {
    //segun el nav_graph podemos saber a donde debe ir al dar click
            findNavController().navigate(R.id.mapDetailFragmentDialog)
    return true
    }


}