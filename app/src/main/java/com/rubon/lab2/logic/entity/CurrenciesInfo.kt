package com.rubon.lab2.logic.entity

data class CurrenciesInfo(
    val EUR: Float,
    val RUB: Float,
    val USD: Float
)

enum class Currency{
    EUR,
    RUB,
    USD,
    BYN
}