package com.example.blackjacksimulator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.blackjacksimulator.models.Blackjack
import com.example.blackjacksimulator.models.BlackjackMove
import com.example.blackjacksimulator.models.Card
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var blackjack: Blackjack

    private lateinit var playerHandView: TextView
    private lateinit var dealerHandView: TextView
    private lateinit var hitButton: Button
    private lateinit var standButton: Button
    private lateinit var gameStateView: TextView
    private lateinit var doubleDownButton: Button
    private lateinit var splitButton: Button

    private lateinit var hitProbabilityView: TextView
    private lateinit var standProbabilityView: TextView
    private lateinit var doubleProbabilityView: TextView
    private lateinit var splitProbabilityView: TextView

    //Image Views
    private lateinit var dealerCard1View: ImageView
    private lateinit var dealerCard2View: ImageView
    private lateinit var dealerCard3View: ImageView
    private lateinit var dealerCard4View: ImageView
    private lateinit var dealerCard5View: ImageView

    private lateinit var playerCard1View: ImageView
    private lateinit var playerCard2View: ImageView
    private lateinit var playerCard3View: ImageView
    private lateinit var playerCard4View: ImageView
    private lateinit var playerCard5View: ImageView

    private lateinit var dealerCardViews: List<ImageView>
    private lateinit var playerCardViews: List<ImageView>

    private enum class GameState {
        INITIAL,
        PLAYER_TURN,
        DEALER_TURN,
        PLAYER_WINS,
        DEALER_WINS,
        TIE,
        PLAYER_BLACKJACK
    }

    private var gameState = GameState.INITIAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your XML layout
        setContentView(R.layout.activity_main)

        // Initialize game logic
        blackjack = Blackjack()
        showNewGameDialog()

        // Connect the UI components to the views in the layout
        playerHandView = findViewById(R.id.playerHandView)
        dealerHandView = findViewById(R.id.dealerHandView)
        gameStateView = findViewById(R.id.gameStateView)

        //Buttons
        hitButton = findViewById(R.id.hitButton)
        standButton = findViewById(R.id.standButton)
        doubleDownButton = findViewById(R.id.doubleButton)
        splitButton = findViewById(R.id.splitButton)

        //Probability Views
        hitProbabilityView = findViewById(R.id.hitProbabilityView)
        standProbabilityView = findViewById(R.id.standProbabilityView)
        doubleProbabilityView = findViewById(R.id.doubleProbabilityView)
        splitProbabilityView = findViewById(R.id.splitProbabilityView)

        //Image views
        dealerCard1View = findViewById(R.id.dealerCard1View)
        dealerCard2View = findViewById(R.id.dealerCard2View)
        dealerCard3View = findViewById(R.id.dealerCard3View)
        dealerCard4View = findViewById(R.id.dealerCard4View)
        dealerCard5View = findViewById(R.id.dealerCard5View)

        //create list of dealer card views
        dealerCardViews = listOf(
            dealerCard1View,
            dealerCard2View,
            dealerCard3View,
            dealerCard4View,
            dealerCard5View
        )


        playerCard1View = findViewById(R.id.playerCard1View)
        playerCard2View = findViewById(R.id.playerCard2View)
        playerCard3View = findViewById(R.id.playerCard3View)
        playerCard4View = findViewById(R.id.playerCard4View)
        playerCard5View = findViewById(R.id.playerCard5View)


        //create list of player card views
        playerCardViews = listOf(
            playerCard1View,
            playerCard2View,
            playerCard3View,
            playerCard4View,
            playerCard5View
        )

        //Initialize the database


        // Set up button listeners to handle user input
        hitButton.setOnClickListener {
            blackjack.playerHit()
            Log.d("Hit", "Hit button pressed")
            updateUI()
        }

        standButton.setOnClickListener {
            blackjack.playerStand()
            Log.d("Stand", "Stand button pressed")
            updateUI()
        }

        doubleDownButton.setOnClickListener {
            blackjack.doubleDown()
            Log.d("Double", "Double button pressed")
            updateUI()
        }
        splitButton.setOnClickListener {
            Log.d("Split", "Split button pressed")
            updateUI()
        }

    }
    private fun getCardDrawableId(card: Card): Int {
        val suit = card.suit.toString()
        val rank = card.rank.toString()
        Log.d("Card", "$rank OF $suit")
        return when ("$rank OF $suit") {
            "ACE OF HEARTS" -> R.drawable.ace_of_hearts
            "TWO OF HEARTS" -> R.drawable.two_of_hearts
            "THREE OF HEARTS" -> R.drawable.three_of_hearts
            "FOUR OF HEARTS" -> R.drawable.four_of_hearts
            "FIVE OF HEARTS" -> R.drawable.five_of_hearts
            "SIX OF HEARTS" -> R.drawable.six_of_hearts
            "SEVEN OF HEARTS" -> R.drawable.seven_of_hearts
            "EIGHT OF HEARTS" -> R.drawable.eight_of_hearts
            "NINE OF HEARTS" -> R.drawable.nine_of_hearts
            "TEN OF HEARTS" -> R.drawable.ten_of_hearts
            "JACK OF HEARTS" -> R.drawable.jack_of_hearts
            "QUEEN OF HEARTS" -> R.drawable.queen_of_hearts
            "KING OF HEARTS" -> R.drawable.king_of_hearts
            "ACE OF DIAMONDS" -> R.drawable.ace_of_diamonds
            "TWO OF DIAMONDS" -> R.drawable.two_of_diamonds
            "THREE OF DIAMONDS" -> R.drawable.three_of_diamonds
            "FOUR OF DIAMONDS" -> R.drawable.four_of_diamonds
            "FIVE OF DIAMONDS" -> R.drawable.five_of_diamonds
            "SIX OF DIAMONDS" -> R.drawable.six_of_diamonds
            "SEVEN OF DIAMONDS" -> R.drawable.seven_of_diamonds
            "EIGHT OF DIAMONDS" -> R.drawable.eight_of_diamonds
            "NINE OF DIAMONDS" -> R.drawable.nine_of_diamonds
            "TEN OF DIAMONDS" -> R.drawable.ten_of_diamonds
            "JACK OF DIAMONDS" -> R.drawable.jack_of_diamonds
            "QUEEN OF DIAMONDS" -> R.drawable.queen_of_diamonds
            "KING OF DIAMONDS" -> R.drawable.king_of_diamonds
            "ACE OF CLUBS" -> R.drawable.ace_of_clubs
            "TWO OF CLUBS" -> R.drawable.two_of_clubs
            "THREE OF CLUBS" -> R.drawable.three_of_clubs
            "FOUR OF CLUBS" -> R.drawable.four_of_clubs
            "FIVE OF CLUBS" -> R.drawable.five_of_clubs
            "SIX OF CLUBS" -> R.drawable.six_of_clubs
            "SEVEN OF CLUBS" -> R.drawable.seven_of_clubs
            "EIGHT OF CLUBS" -> R.drawable.eight_of_clubs
            "NINE OF CLUBS" -> R.drawable.nine_of_clubs
            "TEN OF CLUBS" -> R.drawable.ten_of_clubs
            "JACK OF CLUBS" -> R.drawable.jack_of_clubs
            "QUEEN OF CLUBS" -> R.drawable.queen_of_clubs
            "KING OF CLUBS" -> R.drawable.king_of_clubs
            "ACE OF SPADES" -> R.drawable.ace_of_spades
            "TWO OF SPADES" -> R.drawable.two_of_spades
            "THREE OF SPADES" -> R.drawable.three_of_spades
            "FOUR OF SPADES" -> R.drawable.four_of_spades
            "FIVE OF SPADES" -> R.drawable.five_of_spades
            "SIX OF SPADES" -> R.drawable.six_of_spades
            "SEVEN OF SPADES" -> R.drawable.seven_of_spades
            "EIGHT OF SPADES" -> R.drawable.eight_of_spades
            "NINE OF SPADES" -> R.drawable.nine_of_spades
            "TEN OF SPADES" -> R.drawable.ten_of_spades
            "JACK OF SPADES" -> R.drawable.jack_of_spades
            "QUEEN OF SPADES" -> R.drawable.queen_of_spades
            "KING OF SPADES" -> R.drawable.king_of_spades
            else -> R.drawable.cardbackpng
        }
    }
    private fun updateCardDisplays() {
        // Update the dealer's cards
        for ((index, card) in blackjack.dealerHand().handIterator().withIndex()) {
            dealerCardViews[index].setImageResource(getCardDrawableId(card))
            dealerCardViews[index].visibility = ImageView.VISIBLE
        }
        // Update the player's cards
        for ((index, card) in blackjack.playerHand().handIterator().withIndex()) {
            playerCardViews[index].setImageResource(getCardDrawableId(card))
            playerCardViews[index].visibility = ImageView.VISIBLE
        }
    }

    private fun resetCardDisplays() {
        val cardBackDrawableId = R.drawable.cardbackpng

        // Reset the dealer's cards
        for (cardView in dealerCardViews) {

            cardView.setImageResource(cardBackDrawableId)
            cardView.visibility = ImageView.INVISIBLE
        }
        // Reset player cards
        for (cardView in playerCardViews) {
            cardView.setImageResource(cardBackDrawableId)
            cardView.visibility = ImageView.INVISIBLE
        }

    }

    private fun startNewGame() {
        // Reset the game state
        gameState = GameState.INITIAL
        blackjack.startNewGame()
        resetButtonColors()

        // Reset the card displays
        resetCardDisplays()

        // Reset the UI
        updateUI()
    }
    private fun populateProbabilityViews() {
    resetButtonColors()

//TODO: poker color 013220
        when (blackjack.determineMove()){
            BlackjackMove.HIT -> {
                //Update button colors when hit is the best move
                //Green color code is #00FF00
                hitProbabilityView.setBackgroundColor(Color.parseColor("#00FF00"))

            }
            BlackjackMove.STAND -> {
                //Red color code is #FF0000
                standProbabilityView.setBackgroundColor(Color.parseColor("#FF0000"))
            }
            BlackjackMove.DOUBLE -> {
                //Yellow color code is #FFFF00
                doubleProbabilityView.setBackgroundColor(Color.parseColor("#FFFF00"))
            }
            BlackjackMove.SPLIT -> {
                //Blue color code is #0000FF
                splitProbabilityView.setBackgroundColor(Color.parseColor("#0000FF"))
            }
        }
    when (blackjack.determineSplitMove()){
        BlackjackMove.HIT -> {}
        BlackjackMove.STAND -> {}
        BlackjackMove.DOUBLE -> {}
        BlackjackMove.SPLIT -> {
            //Blue color code is #0000FF
            splitProbabilityView.setBackgroundColor(Color.parseColor("#0000FF"))
        }
    }
        when (blackjack.determineDoubleMove()){
            BlackjackMove.HIT -> {}
            BlackjackMove.STAND -> {}
            BlackjackMove.DOUBLE -> {
                //Yellow color code is #FFFF00
                doubleProbabilityView.setBackgroundColor(Color.parseColor("#FFFF00"))
            }
            BlackjackMove.SPLIT -> {}
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showNewGameDialog() {
        // Inflate the custom layout
        val dialogView = layoutInflater.inflate(R.layout.new_game_fragment, null)

        // Create the AlertDialog and set the custom view
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Find the views in the custom layout
        val playerScoreView = dialogView.findViewById<TextView>(R.id.playerScoreView)
        val dealerScoreView = dialogView.findViewById<TextView>(R.id.dealerScoreView)

        // Set the scores in the dialog
        if(blackjack.gameState() != Blackjack.GameState.INITIAL.toString()){

            playerScoreView.text = "Player Score: ${blackjack.playerHand().value().toString()}"
            dealerScoreView.text = "Dealer Score: ${blackjack.dealerHand().value().toString()}"

            //Update winner text
            val winnerView = dialogView.findViewById<TextView>(R.id.winnerView)
            winnerView.text = when (blackjack.gameState()) {
                Blackjack.GameState.PLAYER_WINS.toString() -> "You Win!"
                Blackjack.GameState.DEALER_WINS.toString() -> "Dealer Wins!"
                Blackjack.GameState.PLAYER_BLACKJACK.toString() -> "Blackjack!"
                Blackjack.GameState.TIE.toString() -> "It's a Tie!"
                else -> ""
            }
        }
        dialog.show()
        // Find the button in the custom layout
        val startNewGameButton = dialogView.findViewById<Button>(R.id.startNewGameButton)
        startNewGameButton.setOnClickListener {
            // Logic to start a new game
            startNewGame()
            updateUI()
            dialog.dismiss() // Close the dialog
        }
    }
    private fun resetButtonColors() {
        val defaultColor = Color.parseColor("#D3D3D3")
        hitProbabilityView.setBackgroundColor(defaultColor)
        standProbabilityView.setBackgroundColor(defaultColor)
        doubleProbabilityView.setBackgroundColor(defaultColor)
        splitProbabilityView.setBackgroundColor(defaultColor)
    }
    // Create a function to update the UI based on the current game state
    @SuppressLint("SetTextI18n")
    private fun updateUI() {
        updateCardDisplays()
        //Give the current count of the player's hand
        playerHandView.text = "Player Hand: ${blackjack.playerHand().value().toString()}"
        dealerHandView.text = "Dealer Hand: ${blackjack.dealerHand().value().toString()}"

        //Give the probability of each action
        populateProbabilityViews()


        gameStateView.text = when (blackjack.gameState()) {
            Blackjack.GameState.PLAYER_TURN.toString() -> "Your Turn"
            Blackjack.GameState.DEALER_TURN.toString() -> "Dealer's Turn"
            Blackjack.GameState.PLAYER_WINS.toString() -> "You Win!"
            Blackjack.GameState.DEALER_WINS.toString() -> "Dealer Wins!"
            Blackjack.GameState.PLAYER_BLACKJACK.toString() -> "Blackjack!"
            Blackjack.GameState.TIE.toString() -> "It's a Tie!"
            else -> ""
        }

        //Show new game dialog when game is over
        if(blackjack.gameState() == Blackjack.GameState.PLAYER_WINS.toString() ||
            blackjack.gameState() == Blackjack.GameState.DEALER_WINS.toString() ||
            blackjack.gameState() == Blackjack.GameState.TIE.toString() ||
            blackjack.gameState() == Blackjack.GameState.PLAYER_BLACKJACK.toString()){
            showNewGameDialog()
        }
    }
}
