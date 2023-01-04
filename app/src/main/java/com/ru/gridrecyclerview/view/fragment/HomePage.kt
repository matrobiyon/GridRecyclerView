package com.ru.gridrecyclerview.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ru.gridrecyclerview.R
import com.ru.gridrecyclerview.model.interfaces.TransactionInterface
import com.ru.gridrecyclerview.ViewModel.ApiViewModel
import com.ru.gridrecyclerview.view.adapter.GridAdapter

class HomePage : Fragment(), TransactionInterface {
    //Second fragment for showing the full screen photo
    private var photoFullScreen = PhotoFullScreen()

    //ViewModel
    private val viewModel : ApiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val recyclerView = view.findViewById<RecyclerView>(R.id.grid_rv)

        recyclerView.layoutManager = GridLayoutManager(context, 2)



        var list = viewModel.dataResult.value!!
        recyclerView.adapter = GridAdapter(this,list)
    }

    override fun transaction(url: String) {
        viewModel.photoUrl = url
        findNavController().navigate(R.id.action_homePage_to_photoFullScreen)
    }


}