package com.example.blackjacksimulator.models

class Hand {
    private val cards = mutableListOf<Card>()
    var useSoftAce = true

    fun toggleAce(){
        useSoftAce = !useSoftAce
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun value(): Int{
        var totalValue = cards.sumOf { it.value()}
        var aces = cards.count {it.rank == Rank.ACE}

        if(useSoftAce){
            while(aces > 0 && totalValue + 10 <= 21){
                totalValue += 10
                aces -= 1
            }
        }

        return totalValue
    }

    //Function to check if hand contains an ace
    fun containsAce(): Boolean{
        for(card in cards){
            if(card.rank == Rank.ACE){
                return true
            }
        }
        return false
    }
    fun containsTen(): Boolean{
        for(card in cards){
            if(card.rank == Rank.TEN){
                return true
            }
        }
        return false
    }

    fun isSoft(): Boolean{
        if(containsAce() && value() <= 11){
            return true
        }
        return false
    }
    fun isHard(): Boolean{
        if(containsAce() && value() > 11){
            return true
        }
        return false
    }


    fun size(): Int = cards.size

    operator fun get(index: Int): Card = cards[index]

    fun clear() {
        cards.clear()
    }


    fun cardString(index: Int): String = cards[index].toString()
    fun cardSuit(index: Int): String = cards[index].suit.toString()
    override fun toString(): String = cards.joinToString { it.toString() }
    fun isBlackjack(): Boolean = size() == 2 && value() == 21
    fun isBust(): Boolean = value() > 21
}