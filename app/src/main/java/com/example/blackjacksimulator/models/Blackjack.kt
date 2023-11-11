package com.example.blackjacksimulator.models

class Blackjack {
    private val deck = Deck()
    private var playerHand = Hand()
    private var dealerHand = Hand()
    private var gameState = GameState.INITIAL
    init {
        startNewGame()
    }
    private fun startNewGame() {
        deck.shuffle()
        playerHand.clear()
        dealerHand.clear()

        playerHand = deck.dealHand()
        dealerHand = deck.dealHand()

        gameState = when {
            playerHand.isBlackjack() && dealerHand.isBlackjack() -> GameState.TIE
            playerHand.isBlackjack() -> GameState.PLAYER_WINS
            dealerHand.isBlackjack() -> GameState.DEALER_WINS
            else -> GameState.PLAYER_TURN
        }
    }
    fun playerHit() {
        if (gameState != GameState.PLAYER_TURN) return

        playerHand.add(deck.draw())
        gameState = when {
            playerHand.isBust() -> GameState.DEALER_WINS
            playerHand.isBlackjack() -> GameState.DEALER_TURN // Allow player to stand after hitting blackjack
           else -> GameState.PLAYER_TURN
        }
    }
    fun playerStand(){
        if(gameState != GameState.PLAYER_TURN){
            return
        }
        gameState = GameState.DEALER_TURN
        dealerAction()
    }
    private fun dealerAction() {
        while (dealerHand.value() < 17) {
            dealerHand.add(deck.draw())
        }
        determineOutcome()
    }
    private fun determineOutcome() {
        gameState = when {
            dealerHand.isBust() -> GameState.PLAYER_WINS
            playerHand.value() > dealerHand.value() -> GameState.PLAYER_WINS
            playerHand.value() < dealerHand.value() -> GameState.DEALER_WINS
            else -> GameState.TIE
        }
    }

    fun gameState(): String = gameState.toString()

    fun playerHand(): Hand = playerHand
    fun dealerHand(): Hand = dealerHand
    enum class GameState {
        INITIAL,
        PLAYER_TURN,
        DEALER_TURN,
        PLAYER_WINS,
        DEALER_WINS,
        TIE
    }
}