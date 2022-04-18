package com.rubon.lab2.logic.entity

class FilterState{
    var mask = ""
    val categories = HashSet<String>()
    var minPrice = 0.0
    var maxPrice = 0.0
}

enum class FilterType{
    MASK,
    CATEGORY,
    PRICE_MIN,
    PRICE_MAX
}