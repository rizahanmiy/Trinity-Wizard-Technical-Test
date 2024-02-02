package com.rizahanmiy.trinitywizard.presentation.ui.firstpage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.rizahanmiy.trinitywizard.R
import com.rizahanmiy.trinitywizard.data.entities.Contacts
import com.rizahanmiy.trinitywizard.databinding.FragmentFirstPageBinding
import com.rizahanmiy.trinitywizard.presentation.ui.adapter.MainAdapter
import com.rizahanmiy.trinitywizard.presentation.viewmodel.FirstPageViewModel
import com.rizahanmiy.trinitywizard.util.constant.AppConstants
import com.rizahanmiy.trinitywizard.util.constant.AppConstants.APP_PREF
import com.rizahanmiy.trinitywizard.util.constant.AppConstants.POSITION_ID

class FirstPage : Fragment() {

    private val argument: FirstPageArgs by navArgs()

    private val firstPageViewModel by viewModels<FirstPageViewModel>()
    private lateinit var binding: FragmentFirstPageBinding
    private var fragmentView: View? = null
    private lateinit var navController: NavController

    private lateinit var contactListingAdapter: MainAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var contactList = mutableListOf<Contacts>()

    private var isRefresh = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (fragmentView != null) {
            return fragmentView
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first_page, container, false)
        fragmentView = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        val position = sharedPref?.getInt(POSITION_ID, 0)


            navController = Navigation.findNavController(view)

            layoutManager = GridLayoutManager(context, 2)
            setupRecyclerView()
            firstPageViewModel.handleGetData(context)

        binding.srlList.setOnRefreshListener {
            isRefresh =true
            resetState(position!!)

            binding.srlList.isRefreshing = true
            firstPageViewModel.handleGetData(context)

        }

        firstPageViewModel.contactList.observe(viewLifecycleOwner) { res ->
            binding.srlList.isRefreshing = false


            contactList.addAll(res)

            if (argument.firstName.isNotEmpty() && !isRefresh)
                contactList[position!!].firstName = argument.firstName
            if (argument.lastName.isNotEmpty() && !isRefresh)
                contactList[position!!].lastName = argument.lastName
            setupAdapter()

        }
    }

    private fun setupAdapter(){
        contactListingAdapter = MainAdapter(contactList){ item ->
            navController.navigate(
                FirstPageDirections.actionFirstPageToSecondPge(
                    item.firstName.toString(),
                    item.lastName.toString(),
                    item.email.toString(),
                    item.dob.toString()
                )
            )
        }
        binding.rvContact.adapter = contactListingAdapter
    }

    private fun setupRecyclerView() {

        binding.rvContact.also {
            it.layoutManager = layoutManager

        }
    }

    private fun resetState(position: Int) {
        contactList.clear()
    }



}