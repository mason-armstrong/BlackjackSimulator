package com.example.blackjacksimulator.models



enum class Suit {
    HEARTS,
    DIAMONDS,
    CLUBS,
    SPADES
}
enum class Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}

class Card(val suit: Suit, val rank: Rank) {

    override fun toString(): String {
        return "$rank of $suit"
    }
    fun value(): Int {
        return when (rank) {
            Rank.ACE -> 1  // Assume the lowest value for Ace here, the Hand class will account for 11.
            Rank.TWO -> 2
            Rank.THREE -> 3
            Rank.FOUR -> 4
            Rank.FIVE -> 5
            Rank.SIX -> 6
            Rank.SEVEN -> 7
            Rank.EIGHT -> 8
            Rank.NINE -> 9
            Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING -> 10
        }
    }
}