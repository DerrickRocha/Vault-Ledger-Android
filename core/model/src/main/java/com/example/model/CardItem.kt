package com.example.model

data class CardItem(
    val id: Int,
    val name: String,
    val cardSet: String,
    val gradingCondition: GradingCondition,
    val valuation: Double,
    val imageUrl: String,
    val currentPriceCents: Int = 0,
    val priceChangePercent: Float = 0.00f
)