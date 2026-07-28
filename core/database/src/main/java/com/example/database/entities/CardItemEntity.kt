package com.example.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "card_item")
data class CardItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "card_set") val cardSet: String,
    @ColumnInfo(name = "grading_condition") val gradingCondition: String,
    val valuation: Double
)