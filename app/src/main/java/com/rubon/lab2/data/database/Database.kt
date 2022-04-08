package com.rubon.lab2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubon.lab2.data.database.products.LoadableProduct
import com.rubon.lab2.data.database.products.ProductsDAO

@Database(entities = [LoadableProduct::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
}