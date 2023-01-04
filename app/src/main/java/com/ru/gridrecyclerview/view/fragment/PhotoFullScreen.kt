package com.ru.gridrecyclerview.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ru.gridrecyclerview.R
import com.ru.gridrecyclerview.ViewModel.ApiViewModel
import com.ru.gridrecyclerview.view.adapter.GridAdapter

class PhotoFullScreen : Fragment() {

    //ViewModels
    private val viewModel : ApiViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_photo_full_screen, container, false)

        var photo = view.findViewById<ImageView>(R.id.fullScreen)

        Log.d("MyError","${viewModel.photoUrl}")

        Glide.with(requireContext())
            .load(viewModel.photoUrl)
            .into(photo)


        return view
    }

}