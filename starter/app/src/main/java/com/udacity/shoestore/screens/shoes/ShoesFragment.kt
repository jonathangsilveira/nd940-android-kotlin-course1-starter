package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.di.ServiceLocator

class ShoesFragment: Fragment() {

    private val viewModel: ShoesViewModel by activityViewModels {
        ShoesViewModel.Factory(ServiceLocator.createShoesRepository())
    }

    private lateinit var binding: FragmentShoesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoes,
            container,
            false
        )
        binding.addButton.setOnClickListener { view ->
            view.findNavController().navigate(
                ShoesFragmentDirections.actionShoesFragmentToShoeFragment()
            )
        }
        viewModel.shoes.observe(
            viewLifecycleOwner,
            { shoes ->
                shoes.forEach { shoe ->
                    val shoesBinding = DataBindingUtil.inflate<ItemShoeBinding>(
                        inflater,
                        R.layout.item_shoe,
                        binding.shoesList,
                        false
                    )
                    shoesBinding.nameText.text = shoe.name
                    shoesBinding.companyText.text = shoe.company
                    shoesBinding.sizeText.text = shoe.size.toInt().toString()
                    binding.shoesList.addView(shoesBinding.root)
                }
            }
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}