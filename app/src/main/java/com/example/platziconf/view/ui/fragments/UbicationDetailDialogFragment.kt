package com.example.platziconf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platziconf.R
import com.example.platziconf.model.Ubication
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

//cambiar la herencia
class UbicationDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
     return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarUbication.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
        toolbarUbication.setTitleTextColor(Color.WHITE)
        toolbarUbication.setNavigationOnClickListener {
            dismiss()
        }

        val ubication =  Ubication()
        //mostrar en el toobal en el titulo el lugar

        toolbarUbication.title=  ubication.name

        tvDetailUbicationNombreLugar.text = ubication.name
        tvDetailUbicationDireccion.text = ubication.address
        tvDetailUbicationTelefono.text =  ubication.phone
        tvDetailUbicationWebsite.text = ubication.website
//al momento de hacerclick en telefono y website tenemos q tener un evento click

        //hacemos el setonclick para poder entra al telefono con el numero q tengamos en el objeto ubication
        llTelefonoLugar.setOnClickListener{
            val intent =  Intent (Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
        //aca igual creamos otro intent para poder acceder a la pagina q este en el objeto
        llSitioweb.setOnClickListener {
            val intent = Intent (Intent.ACTION_VIEW)
            intent.data = Uri.parse(ubication.website)
            startActivity(intent)
        }

    }
    //para dar margenes a nuestro dialogo sobrecargamos nuestra funcion onstart

    override fun onStart() {
        super.onStart()
        //accedemos a toda la venta y le damos todos los parametros de ancho y alto
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

}