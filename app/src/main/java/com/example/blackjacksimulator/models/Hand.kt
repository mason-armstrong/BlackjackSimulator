package com.example.blackjacksimulator.models

class Hand {
    val cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }
    fun value(): Int{
        var totalValue = cards.sumOf { it.value()}
        var aces = cards.count {it.rank == Rank.ACE}

        while(aces > 0 && totalValue + 10 <= 21){
            totalValue += 10
            aces -= 1
        }
        return totalValue
    }

    fun size(): Int = cards.size

    fun get(index: Int): Card = cards[index]

    fun clear() {
        cards.clear()
    }


    fun cardString(index: Int): String = cards[index].toString()
    fun cardSuit(index: Int): String = cards[index].suit.toString()
    override fun toString(): String = cards.joinToString { it.toString() }
    fun isBlackjack(): Boolean = size() == 2 && value() == 21
    fun isBust(): Boolean = value() > 21
}