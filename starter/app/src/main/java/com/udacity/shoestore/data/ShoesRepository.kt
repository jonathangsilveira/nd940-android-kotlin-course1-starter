package com.udacity.shoestore.data

import com.udacity.shoestore.models.Shoe

interface ShoesRepository {
    fun getShoes(): List<Shoe>
    fun add(shoe: Shoe)
}