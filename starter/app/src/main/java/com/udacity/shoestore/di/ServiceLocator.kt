package com.udacity.shoestore.di

import com.udacity.shoestore.data.ShoesRepository
import com.udacity.shoestore.data.ShoesRepositoryImpl

object ServiceLocator {

    fun createShoesRepository(): ShoesRepository {
        return ShoesRepositoryImpl()
    }

}