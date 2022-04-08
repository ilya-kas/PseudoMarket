package com.rubon.lab2.data.database.products

import androidx.room.*
import com.rubon.lab2.data.database.products.LoadableProduct

@Dao
interface ProductsDAO {
    @Query("SELECT * FROM products")
    fun getAll(): List<LoadableProduct>

    @Query("DELETE FROM products")
    fun delete()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: LoadableProduct)
}