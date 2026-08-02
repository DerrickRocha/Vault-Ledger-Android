package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.LorcanaEmerald
import com.example.ui.LorcanaRuby
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@Composable
fun PercentComponent(currentPriceCents: Int, priceChangePercent: Float){
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "$${(currentPriceCents/100)}",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            "${(priceChangePercent.absoluteValue.toString())}%",
            color = if (priceChangePercent > 0) LorcanaEmerald else if(priceChangePercent == 0f) MaterialTheme.colorScheme.onSurface else LorcanaRuby,
            style = MaterialTheme.typography.labelMedium
        )
    }

}