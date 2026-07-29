package com.example.ledger

import androidx.lifecycle.ViewModel
import com.example.model.CardItem
import com.example.model.GradingCondition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LedgerViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(LedgerScreenState(items = cards))
    val state: StateFlow<LedgerScreenState> = _state.asStateFlow()
}

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