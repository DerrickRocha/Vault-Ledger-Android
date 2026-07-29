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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.CardItem
import com.example.ui.components.CardImage
import kotlinx.collections.immutable.ImmutableList

@Composable
fun LedgerScreen(modifier: Modifier, viewModel: LedgerViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LedgerScreenContent(state, modifier)
}

@Composable
fun LedgerScreenContent(state: LedgerScreenState, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.White)) {
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
            .fillMaxSize()
            .background(Color.Black),
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
            .width(170.dp)
            .height(400.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray // Or MaterialTheme.colorScheme.primary
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            CardImage(
                image = item.imageUrl.ifEmpty { null },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                description = item.name
            )

            Column(modifier = Modifier.fillMaxSize()) {
                TextHeader(item.name)
                Spacer(Modifier.height(6.dp))
                WhiteText(item.cardSet)
                WhiteText(item.gradingCondition.gradeName)
                WhiteText(item.valuation.toString())
            }
        }
    }
}

@Composable
fun TextHeader(text: String) {
    Text(
        text,
        color = Color.White,
        autoSize = TextAutoSize.StepBased(maxFontSize = 16.sp, minFontSize = 12.sp),
        maxLines = 2
    )
}

@Composable
fun WhiteText(text: String) {
    Text(text, color = Color.White)
}

@Preview()
@Composable
fun LedgerScreenPreview() {
    LedgerScreenContent(LedgerScreenState(items = cards))
}