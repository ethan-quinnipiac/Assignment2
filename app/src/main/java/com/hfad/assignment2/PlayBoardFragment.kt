//Ethan Lanier
//Assignment 2
//2/24/24
package com.hfad.assignment2

import GameConstants.BLUE_WON
import GameConstants.RED_WON
import GameConstants.TIE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import org.w3c.dom.Text


class PlayBoardFragment : Fragment() {

    private lateinit var resetButton : Button
    private lateinit var gridLayout : GridLayout
    private val args: PlayBoardFragmentArgs by navArgs()

    private lateinit var playerText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_board, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetButton = view.findViewById(R.id.reset_button)
        resetButton.isEnabled = false
        gridLayout = view.findViewById(R.id.grid_layout)
        val name = args.playerName
        val welcomeMessage = view.context.getString(R.string.welcome_message, name)
        val welcomeText : TextView = view.findViewById(R.id.welcome_message)
        welcomeText.text = welcomeMessage
        playerText = view.findViewById(R.id.turn_view)
    }


    fun onGameStateChanged(gameState : Int) {
        if(gameState == TIE || gameState == RED_WON || gameState == BLUE_WON) { //if game is won or tied enable reset button
            resetButton.isEnabled = true
        }
        else resetButton.isEnabled = false

        if (gameState == BLUE_WON) {
            playerText.text = context?.getString(R.string.human_won) //display human wins text
        }
        else if (gameState == RED_WON) {
            playerText.text = context?.getString(R.string.computer_won)//display computer wins text
        }
        else if (gameState == TIE) {
            playerText.text = context?.getString(R.string.tie_game)//display tie text
        }
    }


    fun resetButtonClicked() {
        for (i in 0..35) {
            val button = gridLayout.getChildAt(i) as ImageButton
            button.setImageResource(R.drawable.gray_circle)
            button.isClickable = true

        }
        resetButton.isEnabled = false
        playerText.text = context?.getString(R.string.humans_turn)
    }

    fun humanTurn() {
        playerText.text = context?.getString(R.string.humans_turn)
    }

    fun computerTurn() {
        playerText.text = context?.getString(R.string.comp_turn)
    }
}