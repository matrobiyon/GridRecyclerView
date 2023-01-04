package com.ru.gridrecyclerview.view.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ru.gridrecyclerview.R
import com.ru.gridrecyclerview.ViewModel.ApiViewModel
import com.ru.gridrecyclerview.databinding.FragmentInternetCheckBinding


class InternetCheck : Fragment() {

    //Binding
    private var _binding : FragmentInternetCheckBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel : ApiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInternetCheckBinding.inflate(inflater,container,false)

        viewModel.init()

        val text = binding.waitingNetwork
        val progressBar = binding.progressBar
        val success = binding.successCheck
        val unsuccess = binding.unsuccessCheck
        val tryAgain = binding.tryAgain

        tryAgain.setOnClickListener {
            findNavController().navigate(R.id.internetCheck)
        }


        val statusObserver = Observer<Boolean> {
            if (viewModel.status.value == true) {

                progressBar.visibility = View.GONE
                success.visibility = View.VISIBLE

                //Navigate to another fragment
                Handler().postDelayed({
                    findNavController().navigate(R.id.action_internetCheck_to_homePage)
                }, 1500)
            } else {
                Log.d("MyError", "${viewModel.status.value} from InternetCheck")
                progressBar.visibility = View.GONE
                unsuccess.visibility = View.VISIBLE
            }
        }
        Handler().postDelayed({viewModel.status.observe(viewLifecycleOwner,statusObserver)},2000)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
