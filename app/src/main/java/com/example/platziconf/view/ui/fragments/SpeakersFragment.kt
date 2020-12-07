package com.example.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.platziconf.R

import com.example.platziconf.model.Speaker
import com.example.platziconf.view.adapter.SpeakerAdapter
import com.example.platziconf.view.adapter.SpeakersListener
import com.example.platziconf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment() ,SpeakersListener{

    private lateinit var speakerAdapter:SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter =  SpeakerAdapter(this)
        rvSPeakers.apply {
            layoutManager =  GridLayoutManager(context,2)
            adapter = speakerAdapter
        }
        observeViewModel()

    }
    fun observeViewModel(){

        viewModel.listSpeakers.observe(this.viewLifecycleOwner, Observer <List<Speaker>>{ speaker->
            speakerAdapter.updateData(speaker)
        })
        viewModel.isLoading.observe(this.viewLifecycleOwner,Observer<Boolean>{
            if (it !=null) {
                rlBaseSpeaker.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailFragmentDialog,bundle)
    }


}