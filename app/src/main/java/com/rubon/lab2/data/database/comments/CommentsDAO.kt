package com.rubon.lab2.data.database.comments

import androidx.room.*
import com.rubon.lab2.data.database.products.LoadableProduct

@Dao
interface CommentsDAO {
    @Query("SELECT * FROM comments")
    fun getAll(): List<LoadableComment>

    @Query("DELETE FROM comments WHERE product = :id")
    fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: LoadableComment)
}