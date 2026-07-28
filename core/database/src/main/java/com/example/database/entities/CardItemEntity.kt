package com.example.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.CardItem
import com.example.model.GradingCondition

@Entity(tableName = "card_items")
data class CardItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "card_set") val cardSet: String,
    @ColumnInfo(name = "grading_condition") val gradingCondition: GradingCondition,
    val valuation: Double
)

fun CardItemEntity.asExternalModel() = CardItem(
    id = id,
    name = name,
    cardSet = cardSet,
    gradingCondition = gradingCondition,
    valuation = valuation
)

fun CardItem.asEntity() = CardItemEntity(
    id = id,
    name = name,
    cardSet = cardSet,
    gradingCondition = gradingCondition,
    valuation = valuation
)
