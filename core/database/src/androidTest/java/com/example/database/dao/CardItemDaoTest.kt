package com.example.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.database.VaultLedgerDatabase
import com.example.database.entities.CardItemEntity
import com.example.model.GradingCondition
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CardItemDaoTest {

    private lateinit var cardItemDao: CardItemDao
    private lateinit var db: VaultLedgerDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, VaultLedgerDatabase::class.java
        ).build()
        cardItemDao = db.cardItemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCard() = runBlocking {
        val card = CardItemEntity(
            id = 1,
            name = "Black Lotus",
            cardSet = "Alpha",
            gradingCondition = GradingCondition.GEM_MINT,
            valuation = 10.0,
            imageUrl = "",
            currentPriceCents = 100000
        )
        cardItemDao.insert(card)
        val allCards = cardItemDao.getAll()
        assertEquals(allCards[0], card)
    }

    @Test
    @Throws(Exception::class)
    fun getAllByFlow() = runBlocking {
        val card1 = CardItemEntity(1, "Card 1", "Set A", GradingCondition.NEAR_MINT, 10.0, "", 0)
        val card2 = CardItemEntity(2, "Card 2", "Set B", GradingCondition.HEAVILY_PLAYED, 5.0, "", 0)
        cardItemDao.insertAll(listOf(card1, card2))

        val allCards = cardItemDao.getAllByFlow().first()
        assertEquals(2, allCards.size)
        assertEquals(card1, allCards[0])
        assertEquals(card2, allCards[1])
    }

    @Test
    @Throws(Exception::class)
    fun getByIdFlow() = runBlocking {
        val card = CardItemEntity(1, "Test Card", "Set", GradingCondition.LIGHTLY_PLAYED, 8.0, "", 0)
        cardItemDao.insert(card)

        val retrievedCard = cardItemDao.getById(1).first()
        assertEquals(card, retrievedCard)

        val nonExistentCard = cardItemDao.getById(2).first()
        assertNull(nonExistentCard)
    }

    @Test
    @Throws(Exception::class)
    fun updateCard() = runBlocking {
        val card = CardItemEntity(1, "Test Card", "Set", GradingCondition.MINT, 9.0, "", 0)
        cardItemDao.insert(card)

        val updatedCard = card.copy(valuation = 9.5)
        cardItemDao.update(updatedCard)

        val retrievedCard = cardItemDao.getAll()[0]
        assertEquals(9.5, retrievedCard.valuation, 0.0)
    }

    @Test
    @Throws(Exception::class)
    fun deleteCard() = runBlocking {
        val card = CardItemEntity(1, "Test Card", "Set", GradingCondition.GEM_MINT, 10.0, "", 0)
        cardItemDao.insert(card)
        cardItemDao.delete(card)

        val allCards = cardItemDao.getAll()
        assertEquals(0, allCards.size)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val card1 = CardItemEntity(1, "Card 1", "Set A", GradingCondition.NEAR_MINT, 10.0, "", 0)
        val card2 = CardItemEntity(2, "Card 2", "Set B", GradingCondition.DAMAGED, 5.0, "", 0)
        cardItemDao.insertAll(listOf(card1, card2))

        cardItemDao.deleteAll()
        val allCards = cardItemDao.getAll()
        assertEquals(0, allCards.size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidValuationThrowsException() {
        CardItemEntity(
            id = 1,
            name = "Invalid Card",
            cardSet = "Set",
            gradingCondition = GradingCondition.MINT,
            valuation = 11.0,
            "",
            0
        )
    }
}
