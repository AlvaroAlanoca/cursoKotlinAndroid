package com.example.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.platziconf.R
import com.example.platziconf.model.Conference
import com.example.platziconf.view.adapter.ScheduleAdapter
import com.example.platziconf.view.adapter.ScheduleListener
import com.example.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
//implementamos el listener
class ScheduleFragment : Fragment(), ScheduleListener {
    //creamos dos variables q nos den acceso directo al viewmodel y al adapter(Tambien al firestore)
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*una instancia de viewmodel   le damos el contexto del fragmento en el que estamos y le dacimos q quemos obtener una instancia del shcedule
   //esto lo hacemos para poder obtener los metodos q tiene el ViewModel de Schedule
        //para poder usar viewmodel tenemos q crear la dependencia en el gradle
        //ver si el View model este heredado en la clase q se este haciendo referencia*/
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        //obtenemos los datos de firebase
        viewModel.refresh()
//creamos una instancia de nuestro adaptador del recycler view donde colocaremos la informacion
        scheduleAdapter = ScheduleAdapter(this)
        //le damos atributos a nuestro recyclerview
        rvSchedule.apply {

            //como se mostrarán las vistas de nuestro RecyclerView
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            //LE DECIMOS CUAL SERA EL ADAPTADOR
            adapter = scheduleAdapter
        }
        //observar los datos
        observeViewModel ()
    }


    fun observeViewModel(){
        //hacemos q la lista este observada
        viewModel.listSchedule.observe(this.viewLifecycleOwner, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)

        })
        //creamos la instancia de tipo boolean como el del viewmodel de schedule
        //validamos los datos ingresados (controlar cuando los datos terminen de cargar)
        viewModel.isLoading.observe(this.viewLifecycleOwner,Observer<Boolean>{
            if (it !=null) {
                rlBaseSchedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference,position: Int) {
        //enviamos un objeto
        var bundle = bundleOf("conference" to conference)
        //le enviamos a donde queremos que vaya al momento de hacer click y que nos envie el bundle?
        findNavController().navigate(R.id.scheduleDetailFragmentDialog,bundle)
    }


}