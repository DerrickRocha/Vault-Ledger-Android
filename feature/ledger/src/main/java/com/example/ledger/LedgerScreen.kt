package com.example.ledger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CardItem
import com.example.model.GradingCondition
import com.example.ui.components.CardImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class LedgerScreenState(
    val isLoading: Boolean = false,
    val items: ImmutableList<CardItem> = persistentListOf(),
    val error: String? = null
)

val cards = persistentListOf(
    CardItem(
        1,
        "Rise of the Dark Realms",
        "Foundations",
        GradingCondition.GEM_MINT,
        valuation = 10.0,
        ""
    ),
    CardItem(
        2,
        "Liliana Dreadhorde General fjkljasd fkljsdf lkjdsflk jsdflkj dsflk",
        "Foundations",
        GradingCondition.GEM_MINT,
        valuation = 9.8,
        ""
    ),
    CardItem(
        3,
        "Sadisi, Blood Tyrant",
        "Foundations",
        GradingCondition.GEM_MINT,
        valuation = 9.8,
        ""
    )
)

@Composable
fun LedgerScreen(modifier: Modifier) {
    val state by remember { mutableStateOf(LedgerScreenState(items = cards)) }
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
            LedgerItem(item.name, item.cardSet, item.gradingCondition, item.valuation)
        }
    }
}

@Composable
fun LedgerItem(name: String, set: String, condition: GradingCondition, valuation: Double) {
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
            CardImage(null, modifier = Modifier.width(168.dp).height(240.dp), "")

            Column(modifier = Modifier.fillMaxSize()) {
                TextHeader(name)
                Spacer(Modifier.height(6.dp))
                WhiteText(set)
                WhiteText(condition.gradeName)
                WhiteText(valuation.toString())
            }

        }
    }

}

@Composable
fun TextHeader(text: String){
    Text(text, color = Color.White, autoSize = TextAutoSize.StepBased(maxFontSize = 16.sp, minFontSize = 12.sp), maxLines = 2)
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