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
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@Composable
fun PercentComponent(currentPriceCents: Int, priceChangePercent: Float){
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "$${(currentPriceCents/100)}", color = Color.White, style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            "${(priceChangePercent.absoluteValue.toString())}%",
            color = if (priceChangePercent > 0) Color.Green else if(priceChangePercent == 0f) Color.White else Color.Red,
            style = MaterialTheme.typography.labelMedium
        )
    }

}