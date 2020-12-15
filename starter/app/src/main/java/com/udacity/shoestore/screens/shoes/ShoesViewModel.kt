package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.data.ShoesRepository
import com.udacity.shoestore.models.Shoe

class ShoesViewModel(private val repo: ShoesRepository): ViewModel() {

    fun add(name: String, company: String, size: Double, description: String) {
        val shoe = Shoe(name = name, company = company, size = size, description = description)
        repo.add(shoe)
        _shoes.value = repo.getShoes()
    }

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = repo.getShoes()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repo: ShoesRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoesViewModel::class.java))
                return ShoesViewModel(repo) as T
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}