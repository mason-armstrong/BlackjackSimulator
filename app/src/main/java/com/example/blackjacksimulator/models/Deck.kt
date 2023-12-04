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
            //Return a new deck if the current deck is empty
            for (suit in Suit.values()) {
                for (rank in Rank.values()) {
                    cards.add(Card(suit, rank))
                }
            }
            cards.shuffle()
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

    fun cardsRemaining(): Int {
        return cards.size
    }

    fun get(i: Int): Card {
        return cards[i]
    }
}