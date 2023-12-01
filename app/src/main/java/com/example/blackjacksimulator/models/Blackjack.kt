package com.example.blackjacksimulator.models
class Blackjack {
    private val deck = Deck()
    private var playerHand = Hand()
    private var dealerHand = Hand()
    private var gameState = GameState.INITIAL
    private var strategyTable = BlackjackStrategyTable()
    init {
        startNewGame()
    }
    fun startNewGame() {
        deck.shuffle()
        playerHand.clear()
        dealerHand.clear()


        //Only give dealer one card to start
        dealerHand.add(deck.draw())
        playerHand = deck.dealHand()

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
    fun playerHit() {
        if (gameState != GameState.PLAYER_TURN) return

        playerHand.add(deck.draw())
        gameState = when {
            playerHand.isBust() -> GameState.DEALER_WINS
            playerHand.isBlackjack() -> GameState.DEALER_TURN // Allow player to stand after hitting blackjack
           else -> GameState.PLAYER_TURN
        }
    }

    private fun dealerBlackjackCheck(): Boolean{
        //Check next card in deck to see if dealer has blackjack
        if (dealerHand.containsTen() && dealerHand.containsAce()){
            return true
        }
       return false
    }

    fun doubleDown() {
        if (gameState != GameState.PLAYER_TURN) return
        playerHand.add(deck.draw())
        gameState = when {
            playerHand.isBust() -> GameState.DEALER_WINS
            playerHand.isBlackjack() -> GameState.DEALER_TURN // Allow player to stand after hitting blackjack
            else -> GameState.DEALER_TURN
        }
    }
//TODO: DISPLAY USER HAND WHEN THEY GET A BLACKJACK
    fun split() {
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

    /*
    TODO: Implement the double down and split actions
    TODO: Fix bug where if player has a 20, probability of standing is 1.0
     */

}