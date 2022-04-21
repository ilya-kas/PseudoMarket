package com.rubon.lab2.data.database.comments

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rubon.lab2.logic.entity.Product

@Entity(tableName = "comments")
data class LoadableComment (
    @PrimaryKey()
    val product: Int,

    val text: String
)