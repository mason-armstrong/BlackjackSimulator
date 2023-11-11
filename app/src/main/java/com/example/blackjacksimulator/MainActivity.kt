package com.example.blackjacksimulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.blackjacksimulator.models.Blackjack


/*
TODO: Add play again button
TODO: Add betting, splitting, and double downing

TODO: Add a way to track the player's win/loss record
TODO: Add a way to track the player's money
TODO: Add a way to track the player's bet

TODO: Implement basic strategy engine
TODO: Implement basic strategy hints
 */

class MainActivity : AppCompatActivity() {
    private lateinit var blackjack: Blackjack

    private lateinit var playerHandView: TextView
    private lateinit var dealerHandView: TextView
    private lateinit var hitButton: Button
    private lateinit var standButton: Button
    private lateinit var gameStateView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your XML layout
        setContentView(R.layout.activity_main)

        // Initialize your game logic
        blackjack = Blackjack()

        // Connect the UI components to the views in the layout
        playerHandView = findViewById(R.id.playerHandView)
        dealerHandView = findViewById(R.id.dealerHandView)
        hitButton = findViewById(R.id.hitButton)
        standButton = findViewById(R.id.standButton)

        gameStateView = findViewById(R.id.gameStateView)


        // Set up button listeners to handle user input
        hitButton.setOnClickListener {
            blackjack.playerHit()
            updateUI()
        }

        standButton.setOnClickListener {
            blackjack.playerStand()
            updateUI()
        }

        // Initial UI update
        updateUI()
    }

    // Create a function to update the UI based on the current game state
    private fun updateUI() {
        //Give the current count of the player's hand
        playerHandView.text = "Player Hand: ${blackjack.playerHand().value().toString()}"
        dealerHandView.text = "Dealer Hand: ${blackjack.dealerHand().value().toString()}"



        gameStateView.text = when (blackjack.gameState()) {
            Blackjack.GameState.PLAYER_TURN.toString() -> "Your Turn"
            Blackjack.GameState.DEALER_TURN.toString() -> "Dealer's Turn"
            Blackjack.GameState.PLAYER_WINS.toString() -> "You Win!"
            Blackjack.GameState.DEALER_WINS.toString() -> "Dealer Wins!"
            Blackjack.GameState.TIE.toString() -> "It's a Tie!"
            else -> ""
        }

        // More UI updates...
    }
}
