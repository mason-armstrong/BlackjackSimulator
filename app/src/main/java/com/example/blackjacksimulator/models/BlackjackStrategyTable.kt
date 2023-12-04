package com.example.blackjacksimulator.models


enum class BlackjackMove {
    HIT,
    STAND,
    DOUBLE,
    SPLIT
}

/**
 * This class represents the strategy table for blackjack. It is a map of a pair of the player's hand value and the
 * dealer's upcard value to the move the player should make.
 */
/*
Probability of winning with a given hand value against a dealer's upcard:
* 21: 100%
* 20: 92%
* 19: 85%
* 18: 77%
* 17: 69%
* 16: 62%
* 15: 58%
* 14: 56%
* 13: 39%
* 12: 31%
* 11: 0%
*
* Probability of player busting based on dealer's upcard:
* 2: 35%
* 3: 37%
* 4: 40%
* 5: 42%
* 6: 42%
* 7: 26%
* 8: 24%
* 9: 23%
* 10: 23%
* A: 11%
*
* Dealers chance of busting when standing on 17:
* 2: 35%
* 3: 37%
* 4: 40%
* 5: 42%
* 6: 42%
* 7: 26%
* 8: 24%
* 9: 23%
* 10: 23%
* A: 11%
*
* Odds of being dealt:
* 21: 4.8%
* Standing Hand (17-20): 30.7%
* Decision Hand (1-16): 38.7%
* Bust Hand (22+): 25.8%
*
*
 */
class BlackjackStrategyTable {
    private val strategyTable: Map<Pair<Int, Int>, BlackjackMove> = mapOf(
        // Pair(Hand Value, Dealer Up Card) -> Move

        Pair(1, 2) to BlackjackMove.HIT,
        Pair(1, 3) to BlackjackMove.HIT,
        Pair(1, 4) to BlackjackMove.HIT,
        Pair(1, 5) to BlackjackMove.HIT,
        Pair(1, 6) to BlackjackMove.HIT,
        Pair(1, 7) to BlackjackMove.HIT,
        Pair(1, 8) to BlackjackMove.HIT,
        Pair(1, 9) to BlackjackMove.HIT,
        Pair(1, 10) to BlackjackMove.HIT,
        Pair(1, 11) to BlackjackMove.HIT,

         Pair(2, 2) to BlackjackMove.HIT,
         Pair(2, 3) to BlackjackMove.HIT,
         Pair(2, 4) to BlackjackMove.HIT,
         Pair(2, 5) to BlackjackMove.HIT,
         Pair(2, 6) to BlackjackMove.HIT,
         Pair(2, 7) to BlackjackMove.HIT,
         Pair(2, 8) to BlackjackMove.HIT,
         Pair(2, 9) to BlackjackMove.HIT,
         Pair(2, 10) to BlackjackMove.HIT,
         Pair(2, 11) to BlackjackMove.HIT,

        Pair(3, 2) to BlackjackMove.HIT,
        Pair(3, 3) to BlackjackMove.HIT,
        Pair(3, 4) to BlackjackMove.HIT,
        Pair(3, 5) to BlackjackMove.HIT,
        Pair(3, 6) to BlackjackMove.HIT,
        Pair(3, 7) to BlackjackMove.HIT,
        Pair(3, 8) to BlackjackMove.HIT,
        Pair(3, 9) to BlackjackMove.HIT,
        Pair(3, 10) to BlackjackMove.HIT,
        Pair(3, 11) to BlackjackMove.HIT,

        Pair(4, 2) to BlackjackMove.HIT,
        Pair(4, 3) to BlackjackMove.HIT,
        Pair(4, 4) to BlackjackMove.HIT,
        Pair(4, 5) to BlackjackMove.HIT,
        Pair(4, 6) to BlackjackMove.HIT,
        Pair(4, 7) to BlackjackMove.HIT,
        Pair(4, 8) to BlackjackMove.HIT,
        Pair(4, 9) to BlackjackMove.HIT,
        Pair(4, 10) to BlackjackMove.HIT,
        Pair(4, 11) to BlackjackMove.HIT,

        Pair(5, 2) to BlackjackMove.HIT,
        Pair(5, 3) to BlackjackMove.HIT,
        Pair(5, 4) to BlackjackMove.HIT,
        Pair(5, 5) to BlackjackMove.HIT,
        Pair(5, 6) to BlackjackMove.HIT,
        Pair(5, 7) to BlackjackMove.HIT,
        Pair(5, 8) to BlackjackMove.HIT,
        Pair(5, 9) to BlackjackMove.HIT,
        Pair(5, 10) to BlackjackMove.HIT,
        Pair(5, 11) to BlackjackMove.HIT,

        Pair(6, 2) to BlackjackMove.HIT,
        Pair(6, 3) to BlackjackMove.HIT,
        Pair(6, 4) to BlackjackMove.HIT,
        Pair(6, 5) to BlackjackMove.HIT,
        Pair(6, 6) to BlackjackMove.HIT,
        Pair(6, 7) to BlackjackMove.HIT,
        Pair(6, 8) to BlackjackMove.HIT,
        Pair(6, 9) to BlackjackMove.HIT,
        Pair(6, 10) to BlackjackMove.HIT,
        Pair(6, 11) to BlackjackMove.HIT,

        Pair(7, 2) to BlackjackMove.HIT,
        Pair(7, 3) to BlackjackMove.HIT,
        Pair(7, 4) to BlackjackMove.HIT,
        Pair(7, 5) to BlackjackMove.HIT,
        Pair(7, 6) to BlackjackMove.HIT,
        Pair(7, 7) to BlackjackMove.HIT,
        Pair(7, 8) to BlackjackMove.HIT,
        Pair(7, 9) to BlackjackMove.HIT,
        Pair(7, 10) to BlackjackMove.HIT,
        Pair(7, 11) to BlackjackMove.HIT,

        Pair(8, 2) to BlackjackMove.HIT,
        Pair(8, 3) to BlackjackMove.HIT,
        Pair(8, 4) to BlackjackMove.HIT,
        Pair(8, 5) to BlackjackMove.HIT,
        Pair(8, 6) to BlackjackMove.HIT,
        Pair(8, 7) to BlackjackMove.HIT,
        Pair(8, 8) to BlackjackMove.HIT,
        Pair(8, 9) to BlackjackMove.HIT,
        Pair(8, 10) to BlackjackMove.HIT,
        Pair(8, 11) to BlackjackMove.HIT,

        Pair(9, 2) to BlackjackMove.HIT,
        Pair(9, 3) to BlackjackMove.DOUBLE,
        Pair(9, 4) to BlackjackMove.DOUBLE,
        Pair(9, 5) to BlackjackMove.DOUBLE,
        Pair(9, 6) to BlackjackMove.DOUBLE,
        Pair(9, 7) to BlackjackMove.HIT,
        Pair(9, 8) to BlackjackMove.HIT,
        Pair(9, 9) to BlackjackMove.HIT,
        Pair(9, 10) to BlackjackMove.STAND,
        Pair(9, 11) to BlackjackMove.STAND,

        Pair(10, 2) to BlackjackMove.DOUBLE,
        Pair(10, 3) to BlackjackMove.DOUBLE,
        Pair(10, 4) to BlackjackMove.DOUBLE,
        Pair(10, 5) to BlackjackMove.DOUBLE,
        Pair(10, 6) to BlackjackMove.DOUBLE,
        Pair(10, 7) to BlackjackMove.DOUBLE,
        Pair(10, 8) to BlackjackMove.DOUBLE,
        Pair(10, 9) to BlackjackMove.DOUBLE,
        Pair(10, 10) to BlackjackMove.STAND,
        Pair(10, 11) to BlackjackMove.STAND,

        Pair(11, 2) to BlackjackMove.DOUBLE,
        Pair(11, 3) to BlackjackMove.DOUBLE,
        Pair(11, 4) to BlackjackMove.DOUBLE,
        Pair(11, 5) to BlackjackMove.DOUBLE,
        Pair(11, 6) to BlackjackMove.DOUBLE,
        Pair(11, 7) to BlackjackMove.DOUBLE,
        Pair(11, 8) to BlackjackMove.DOUBLE,
        Pair(11, 9) to BlackjackMove.DOUBLE,
        Pair(11, 10) to BlackjackMove.DOUBLE,
        Pair(11, 11) to BlackjackMove.DOUBLE,

        Pair(12, 2) to BlackjackMove.HIT,
        Pair(12, 3) to BlackjackMove.HIT,
        Pair(12, 4) to BlackjackMove.STAND,
        Pair(12, 5) to BlackjackMove.STAND,
        Pair(12, 6) to BlackjackMove.STAND,
        Pair(12, 7) to BlackjackMove.HIT,
        Pair(12, 8) to BlackjackMove.HIT,
        Pair(12, 9) to BlackjackMove.HIT,
        Pair(12, 10) to BlackjackMove.HIT,
        Pair(12, 11) to BlackjackMove.HIT,

        Pair(13, 2) to BlackjackMove.STAND,
        Pair(13, 3) to BlackjackMove.STAND,
        Pair(13, 4) to BlackjackMove.STAND,
        Pair(13, 5) to BlackjackMove.STAND,
        Pair(13, 6) to BlackjackMove.STAND,
        Pair(13, 7) to BlackjackMove.HIT,
        Pair(13, 8) to BlackjackMove.HIT,
        Pair(13, 9) to BlackjackMove.HIT,
        Pair(13, 10) to BlackjackMove.HIT,
        Pair(13, 11) to BlackjackMove.HIT,

        Pair(14, 2) to BlackjackMove.STAND,
        Pair(14, 3) to BlackjackMove.STAND,
        Pair(14, 4) to BlackjackMove.STAND,
        Pair(14, 5) to BlackjackMove.STAND,
        Pair(14, 6) to BlackjackMove.STAND,
        Pair(14, 7) to BlackjackMove.HIT,
        Pair(14, 8) to BlackjackMove.HIT,
        Pair(14, 9) to BlackjackMove.HIT,
        Pair(14, 10) to BlackjackMove.HIT,
        Pair(14, 11) to BlackjackMove.HIT,

        Pair(15, 2) to BlackjackMove.STAND,
        Pair(15, 3) to BlackjackMove.STAND,
        Pair(15, 4) to BlackjackMove.STAND,
        Pair(15, 5) to BlackjackMove.STAND,
        Pair(15, 6) to BlackjackMove.STAND,
        Pair(15, 7) to BlackjackMove.HIT,
        Pair(15, 8) to BlackjackMove.HIT,
        Pair(15, 9) to BlackjackMove.HIT,
        Pair(15, 10) to BlackjackMove.HIT,
        Pair(15, 11) to BlackjackMove.HIT,

        Pair(16, 2) to BlackjackMove.STAND,
        Pair(16, 3) to BlackjackMove.STAND,
        Pair(16, 4) to BlackjackMove.STAND,
        Pair(16, 5) to BlackjackMove.STAND,
        Pair(16, 6) to BlackjackMove.STAND,
        Pair(16, 7) to BlackjackMove.HIT,
        Pair(16, 8) to BlackjackMove.HIT,
        Pair(16, 9) to BlackjackMove.HIT,
        Pair(16, 10) to BlackjackMove.HIT,
        Pair(16, 11) to BlackjackMove.HIT,

        Pair(17, 2) to BlackjackMove.STAND,
        Pair(17, 3) to BlackjackMove.STAND,
        Pair(17, 4) to BlackjackMove.STAND,
        Pair(17, 5) to BlackjackMove.STAND,
        Pair(17, 6) to BlackjackMove.STAND,
        Pair(17, 7) to BlackjackMove.STAND,
        Pair(17, 8) to BlackjackMove.STAND,
        Pair(17, 9) to BlackjackMove.STAND,
        Pair(17, 10) to BlackjackMove.STAND,
        Pair(17, 11) to BlackjackMove.STAND
    )

    private val splitStrategyTable: Map<Pair<Rank, Int>, BlackjackMove> = mapOf(
        // Pair(Hand Value, Dealer Up Card) -> Move
        //Aces
        Pair(Rank.ACE, 2) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 3) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 4) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 5) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 6) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 7) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 8) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 9) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 10) to BlackjackMove.SPLIT,
        Pair(Rank.ACE, 11) to BlackjackMove.SPLIT,

        Pair(Rank.TWO, 2) to BlackjackMove.SPLIT,
        Pair(Rank.TWO, 3) to BlackjackMove.SPLIT,
        Pair(Rank.TWO, 4) to BlackjackMove.SPLIT,
        Pair(Rank.TWO, 5) to BlackjackMove.SPLIT,
        Pair(Rank.TWO, 6) to BlackjackMove.SPLIT,
        Pair(Rank.TWO, 7) to BlackjackMove.SPLIT,


        Pair(Rank.THREE, 2) to BlackjackMove.SPLIT,
        Pair(Rank.THREE, 3) to BlackjackMove.SPLIT,
        Pair(Rank.THREE, 4) to BlackjackMove.SPLIT,
        Pair(Rank.THREE, 5) to BlackjackMove.SPLIT,
        Pair(Rank.THREE, 6) to BlackjackMove.SPLIT,
        Pair(Rank.THREE, 7) to BlackjackMove.SPLIT,


        Pair(Rank.FOUR, 4) to BlackjackMove.SPLIT,
        Pair(Rank.FOUR, 5) to BlackjackMove.SPLIT,
        Pair(Rank.FOUR, 6) to BlackjackMove.SPLIT,


        Pair(Rank.FIVE, 2) to BlackjackMove.DOUBLE,
        Pair(Rank.FIVE, 3) to BlackjackMove.DOUBLE,
        Pair(Rank.FIVE, 4) to BlackjackMove.DOUBLE,
        Pair(Rank.FIVE, 5) to BlackjackMove.DOUBLE
    )


    fun getSplitMove(playerCard: Card, dealerUpCard: Card): BlackjackMove {
        // Key is a Pair of the player's card rank and the dealer's upcard value
        val key = Pair(playerCard.rank, dealerUpCard.value())
        return splitStrategyTable[key] ?: BlackjackMove.STAND
    }

    fun getMove(playerHandValue: Int, dealerUpCard: Card): BlackjackMove {
        // Key is a Pair of the player's hand total value and the dealer's upcard value
        val key = Pair(playerHandValue, dealerUpCard.value())
        return strategyTable[key] ?: BlackjackMove.STAND
    }










}