package com.example.database.util

import androidx.room.TypeConverter
import com.example.model.GradingCondition

class VaultLedgerConverters {
    @TypeConverter
    fun stringToGradingCondition(value: String?): GradingCondition {
        return try {
            value?.let { enumValueOf<GradingCondition>(it) } ?: GradingCondition.UNSPECIFIED
        } catch (e: IllegalArgumentException) {
            GradingCondition.UNSPECIFIED
        }
    }

    @TypeConverter
    fun gradingConditionToString(gradingCondition: GradingCondition?): String {
        return gradingCondition?.name ?: GradingCondition.UNSPECIFIED.name
    }
}
