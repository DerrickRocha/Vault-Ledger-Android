package com.example.model

data class CardItem(
    val id: Int,
    val name: String,
    val cardSet: String,
    val gradingCondition: GradingCondition,
    val valuation: Double
) {
    init {
        require(valuation in 0.0..10.0) {
            "Valuation must be between 0.0 and 10.0. Provided: $valuation"
        }
    }
}
