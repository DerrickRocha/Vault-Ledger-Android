package com.example.ledger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.CardItem
import com.example.ui.components.CardImage
import com.example.ui.components.PercentComponent
import com.example.ui.VaultLedgerTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun LedgerScreen(modifier: Modifier, viewModel: LedgerViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LedgerScreenContent(state, modifier)
}

@Composable
fun LedgerScreenContent(state: LedgerScreenState, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LedgerGrid(state.items)
    }
}

@Composable
fun LedgerGrid(
    itemList: ImmutableList<CardItem>
) {
    val spacing = 16.dp
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.FixedSize(170.dp),
        horizontalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(spacing),
        contentPadding = PaddingValues(spacing),
    ) {
        items(itemList, key = { item -> item.id }) { item ->
            LedgerItem(item)
        }
    }
}

@Composable
fun LedgerItem(item: CardItem) {
    Card(
        modifier = Modifier
            .height(400.dp)
            .width(170.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CardImage(
                image = item.imageUrl.ifEmpty { null },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                description = item.name
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 0.dp, bottom = 8.dp, start = 6.dp, end = 6.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                ) {
                    TextHeader(item.name)
                    Spacer(Modifier.height(6.dp))
                    LedgerSecondaryText(item.cardSet)
                    LedgerSecondaryText(item.gradingCondition.gradeName)
                    LedgerSecondaryText(item.valuation.toString())
                    Spacer(Modifier.height(6.dp))
                    PercentComponent(item.currentPriceCents, item.priceChangePercent)
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun TextHeader(text: String) {
    Text(
        text,
        autoSize = TextAutoSize.StepBased(maxFontSize = 16.sp, minFontSize = 12.sp),
        maxLines = 2,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.labelMedium,
    )
}

@Composable
fun LedgerSecondaryText(text: String) {
    Text(
        text,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.labelSmall
    )
}

@Preview()
@Composable
fun LedgerScreenPreview() {
    VaultLedgerTheme(darkTheme = true) {
        LedgerScreenContent(LedgerScreenState(items = cards))
    }
}