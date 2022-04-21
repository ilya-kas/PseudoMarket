package com.rubon.lab2.data.database.comments

import com.rubon.lab2.logic.entity.Product
import javax.inject.Inject

interface CommentsRepository {
    suspend fun loadAll(): MutableMap<Int, String>
    suspend fun update(product: Product, text: String) //if length = 0 then delete
}

class CommentsRepositoryImpl @Inject constructor(private val dao: CommentsDAO): CommentsRepository{
    private var cachedValue: MutableMap<Int, String>? = null

    override suspend fun loadAll(): MutableMap<Int, String> {
        if (cachedValue != null) return cachedValue!!

        val data = dao.getAll()

        val result = HashMap<Int, String>()
        for (record in data)
            result[record.product] = record.text

        cachedValue = result
        return result
    }

    override suspend fun update(product: Product, text: String) {
        if (text == ""){
            dao.delete(product.hashCode())
            cachedValue!!.remove(product.hashCode())
            return
        }
        dao.insert(LoadableComment(product.hashCode(), text))
        cachedValue!![product.hashCode()] = text
    }
}