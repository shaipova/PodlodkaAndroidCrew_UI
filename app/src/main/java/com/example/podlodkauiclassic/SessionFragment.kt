package com.example.podlodkauiclassic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.podlodkauiclassic.databinding.FragmentSessionBinding
import com.google.android.material.snackbar.Snackbar


class SessionFragment : Fragment() {

    lateinit var binding: FragmentSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_session, container, false)
        binding.setLifecycleOwner(this)

        val viewModelProviderFactory = ViewModelProviderFactory()
        val viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SessionViewModel::class.java)

        val sessionAdapter = SessionAdapter(viewModel.list, viewModel)
        binding.sessionRecyclerView.adapter = sessionAdapter

        sessionAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("details", it)
            }
            findNavController().navigate(
                R.id.action_sessionFragment_to_detailFragment, bundle
            )
        }

        viewModel.favList.observe(viewLifecycleOwner, Observer {
            binding.favRecyclerView.adapter = FavAdapter(it, viewModel)
        })

        viewModel.snackbar.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(
                    binding.root,
                    R.string.failed_to_add_session_to_favorites,
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.stopSnackbar()
            }
        })

        return binding.root

    }
}