package com.example.podlodkauiclassic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.podlodkauiclassic.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.setLifecycleOwner(this)

        val session = args.details

        binding.apply {
            detailSpeaker.text = session.speaker
            detailDate.text = session.date
            detailTime.text = session.timeInterval
            detailDescription.text = session.description

            Picasso.get()
                .load(session.imageUrl)
                .into(detailPhoto)
        }

        return binding.root
    }

}