package com.example.model

data class CardItem(
    val id: Int,
    val name: String,
    val cardSet: String,
    val gradingCondition: GradingCondition,
    val valuation: Double
)