package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeBinding
import com.udacity.shoestore.screens.shoes.ShoesViewModel

class ShoeFragment: Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe,
            container,
            false
        )
        binding.cancelButton.setOnClickListener { view ->
            navigateBack(view)
        }
        binding.saveButton.setOnClickListener { view ->
            val name = binding.nameEdit.text.toString()
            val company = binding.companyEdit.text.toString()
            val size = binding.sizeEdit.text.toString()
            val description = binding.descriptionEdit.text.toString()
            viewModel.add(name, company, size.toDouble(), description)
            navigateBack(view)
        }
        return binding.root
    }

    private fun navigateBack(view: View) {
        view.findNavController().navigate(
            ShoeFragmentDirections.actionShoeFragmentToShoesFragment()
        )
    }

}