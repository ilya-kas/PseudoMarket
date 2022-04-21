package com.rubon.lab2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubon.lab2.data.database.comments.CommentsDAO
import com.rubon.lab2.data.database.comments.LoadableComment
import com.rubon.lab2.data.database.products.LoadableProduct
import com.rubon.lab2.data.database.products.ProductsDAO

@Database(entities = [LoadableProduct::class, LoadableComment::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
    abstract fun commentsDAO(): CommentsDAO
}