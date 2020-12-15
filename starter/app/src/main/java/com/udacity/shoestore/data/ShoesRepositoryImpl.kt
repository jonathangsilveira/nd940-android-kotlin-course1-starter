package com.udacity.shoestore.data

import com.udacity.shoestore.models.Shoe

class ShoesRepositoryImpl: ShoesRepository {

    private val shoes = mutableListOf<Shoe>()

    override fun getShoes(): List<Shoe> = shoes

    override fun add(shoe: Shoe) {
        shoes.add(shoe)
    }

}