package com.rubon.lab2.app_level.dagger.module

import android.content.Context
import androidx.room.Room
import com.rubon.lab2.data.database.Database
import com.rubon.lab2.data.database.comments.CommentsDAO
import com.rubon.lab2.data.database.products.ProductsDAO
import dagger.Module
import dagger.Provides

@Module
open class DBModule(val context: Context) {
    private val db = Room
        .databaseBuilder(context, Database::class.java, "App")
        .build()

    @Provides
    open fun getProductsDAO(): ProductsDAO {
        return db.productsDAO()
    }

    @Provides
    open fun getCommentsDAO(): CommentsDAO {
        return db.commentsDAO()
    }
}