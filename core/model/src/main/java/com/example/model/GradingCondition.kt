package com.example.model

enum class GradingCondition(val gradeName: String) {
    GEM_MINT("Gem Mint"),
    MINT("Mint"),
    NEAR_MINT("Near Mint"),
    LIGHTLY_PLAYED("Lightly Played"),
    MODERATELY_PLAYED("Moderately Played"),
    HEAVILY_PLAYED("Heavily Played"),
    DAMAGED("Damaged"),
    UNSPECIFIED("Unspecified")
}
