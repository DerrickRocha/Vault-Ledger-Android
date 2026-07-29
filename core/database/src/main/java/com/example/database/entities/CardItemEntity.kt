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
    val valuation: Double,
    @ColumnInfo(name = "image_url")val imageUrl: String
) {
    init {
        require(valuation in 0.0..10.0) {
            "Valuation must be between 0.0 and 10.0. Provided: $valuation"
        }
    }
}

fun CardItemEntity.asExternalModel() = CardItem(
    id = id,
    name = name,
    cardSet = cardSet,
    gradingCondition = gradingCondition,
    valuation = valuation,
    imageUrl = imageUrl
)

fun CardItem.asEntity() = CardItemEntity(
    id = id,
    name = name,
    cardSet = cardSet,
    gradingCondition = gradingCondition,
    valuation = valuation,
    imageUrl = imageUrl
)
