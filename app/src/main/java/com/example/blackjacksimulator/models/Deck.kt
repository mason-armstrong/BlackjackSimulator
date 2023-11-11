package com.example.blackjacksimulator.models

class Deck {

    private val cards = mutableListOf<Card>()

    init{
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    fun draw(): Card {
        if (cards.isEmpty()) {
            throw IllegalStateException("No cards left in the deck.")
        }
        return cards.removeAt(0)
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun dealHand(): Hand {
        val hand = Hand()
        repeat(2){
            hand.add(draw())
        }
        return hand
    }
}