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
import androidx.core.graphics.toColor
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.platziconf.R
import com.example.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*
import kotlinx.android.synthetic.main.item_speaker.*

class SpeakersDetailDialogFragment :    DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeakers.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)




        toolbarSpeakers.setNavigationOnClickListener {
            dismiss()
        }
        val speakers =  arguments?.getSerializable("speaker")as Speaker

        toolbarSpeakers.title =  speakers.name
        toolbarSpeakers.setTitleTextColor(Color.WHITE)

        tvDetailSpeakerName.text =  speakers.name
        tvDetailSpeakerBiography.text = speakers.biography
        tvDetailSpeakerJobTittle.text =  speakers.jobtitle
        tvDetailSpeakerWorkPlace.text = speakers.workplace

        Glide.with(this).load(speakers.image).apply(RequestOptions.circleCropTransform()).into(ivDetailSpeakerImage)

        ivDetailSpeakerTwitter.setOnClickListener{
            val url = speakers.twitter
            val uri = Uri.parse("https://twitter.com/"+url)
            val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
            startActivity(launchBrowser)
        }
        /*ivDetailSpeakerTwitter.setOnClickListener {
            var intent: Intent
            try {
                context?.packageManager?.getPackageArchiveInfo("com.twitter.android",0)
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=${speakers.twitter}"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            catch (e:Exception)
            {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/${speakers.twitter}"))
            }
            startActivity(intent)
        }*/

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    }
}