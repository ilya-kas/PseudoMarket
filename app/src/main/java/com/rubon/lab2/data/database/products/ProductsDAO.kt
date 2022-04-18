package com.rubon.lab2.data.database.products

import androidx.room.*
import com.rubon.lab2.data.database.products.LoadableProduct

@Dao
interface ProductsDAO {
    @Query("SELECT * FROM products")
    fun getAll(): List<LoadableProduct>

    @Query("SELECT * FROM products WHERE favorite = 1")
    fun getFavorites(): List<LoadableProduct>

    @Query("DELETE FROM products WHERE hash = :id")
    fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: LoadableProduct)
}