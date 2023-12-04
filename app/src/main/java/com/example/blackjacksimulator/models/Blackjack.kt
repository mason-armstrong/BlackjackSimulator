package com.example.blackjacksimulator.models
class Blackjack {
    private val deck = Deck()
    private var playerHand = Hand()
    private var dealerHand = Hand()
    private var gameState = GameState.INITIAL
    private var strategyTable = BlackjackStrategyTable()
    private var playerHands = mutableListOf<Hand>()

    init {
        startNewGame()
    }
    fun startNewGame() {
        deck.shuffle()
        playerHand.clear()
        playerHands.clear()
        dealerHand.clear()

        //Only give dealer one card to start
        dealerHand.add(deck.draw())
        playerHand = deck.dealHand()
        playerHands.add(playerHand)

        if(dealerBlackjackCheck()){
            gameState = GameState.DEALER_WINS
        }


        gameState = when {
            playerHand.isBlackjack() && dealerHand.isBlackjack() -> GameState.TIE
            playerHand.isBlackjack() -> GameState.PLAYER_BLACKJACK
            dealerHand.isBlackjack() -> GameState.DEALER_WINS
            else -> GameState.PLAYER_TURN
        }
    }

    private fun canDoubleDown(): Boolean{
        if(playerHand.size() == 2){
            return true
        }
        return false
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

    private fun endPlayerTurn(){
        gameState = GameState.DEALER_TURN
        dealerAction()
    }

    private fun dealerBlackjackCheck(): Boolean{
        //Check next card in deck to see if dealer has blackjack
        if (dealerHand.containsTen() && dealerHand.containsAce()){
            return true
        }
       return false
    }

    fun doubleDown() {
        if (gameState != GameState.PLAYER_TURN && canDoubleDown()) return
        playerHand.add(deck.draw())
        gameState = when {
            playerHand.isBust() -> GameState.DEALER_WINS
            else -> {
                GameState.DEALER_TURN
            }

        }

        endPlayerTurn()
        dealerAction()

    }

    fun playerSplit() {
        if(playerHands.size == 1){
            val (hand1, hand2) = playerHand.split()
            playerHands.clear()
            playerHands.add(hand1)
            playerHands.add(hand2)

            hand1.add(deck.draw())
            hand2.add(deck.draw())
            gameState = GameState.PLAYER_TURN

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

        if(dealerHand.size() ==1){
            dealerHand.add(deck.draw())
        }
        while (dealerHand.value() < 17) {
            dealerHand.add(deck.draw())
        }
           determineOutcome()
    }
    private fun determineOutcome() {
        gameState = when {
            dealerHand.isBust() -> GameState.PLAYER_WINS
            playerHand.isBust() -> GameState.DEALER_WINS
            playerHand.value() > dealerHand.value() -> GameState.PLAYER_WINS
            playerHand.value() < dealerHand.value() -> GameState.DEALER_WINS
            else -> GameState.TIE
        }
    }

    fun gameOver(): Boolean = gameState == GameState.PLAYER_WINS || gameState == GameState.DEALER_WINS || gameState == GameState.TIE




    fun gameState(): String = gameState.toString()

    fun playerHand(): Hand = playerHand
    fun dealerHand(): Hand = dealerHand
    enum class GameState {
        INITIAL,
        PLAYER_TURN,
        DEALER_TURN,
        PLAYER_WINS,
        DEALER_WINS,
        PLAYER_BLACKJACK,
        TIE
    }

    fun determineDoubleMove(): BlackjackMove{
        return strategyTable.getMove(playerHand.value(), dealerHand.get(0))
    }
    fun determineSplitMove(): BlackjackMove{
        return strategyTable.getSplitMove(playerHand.get(0), dealerHand.get(0))
    }
    fun determineMove(): BlackjackMove {
        return strategyTable.getMove(playerHand.value(), dealerHand.get(0))
    }

}