//Ethan Lanier
//Assignment 2
//2/24/24
package com.hfad.assignment2

import FourInARow
import GameConstants.BLUE
import GameConstants.BLUE_WON
import GameConstants.COMPUTER_PLAYER
import GameConstants.HUMAN_PLAYER
import GameConstants.PLAYING
import GameConstants.RED_WON
import GameConstants.TIE
import IGame
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {


    private val game : IGame = FourInARow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getPlayBoardFragment() : PlayBoardFragment {
        val navHostFragment : Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.primaryNavigationFragment as PlayBoardFragment
    }


//function that runs when a tile is clicked
    fun clicked(v: View) {
        val grid = v.parent as GridLayout
        val index = grid.indexOfChild(v)
        val playBoard = getPlayBoardFragment()

        val playerButton = v as ImageButton

        playerButton.setImageResource(R.drawable.bluecircle) //sets image to blue
        playerButton.isClickable = false //tile cant be clicked any more
        game.setMove(HUMAN_PLAYER, index)
        var currentGameState = game.checkForWinner() //check if there is a winner
        if (currentGameState == BLUE_WON) {
            playBoard.onGameStateChanged(BLUE_WON)

        }
        if (currentGameState == PLAYING) { //if no winner
            playBoard.computerTurn()
           val compIndex = game.computerMove() //gets computer move
            game.setMove(COMPUTER_PLAYER, compIndex)
            val computerButton = grid.getChildAt(compIndex) as ImageButton

            computerButton.setImageResource(R.drawable.red_circle) //sets image to red
            computerButton.isClickable = false //tile cant be clicked anymore
        }
        currentGameState = game.checkForWinner() //check again for winner
        if (currentGameState == RED_WON) {
            //UPDATE TO SHOW WINNER
            playBoard.onGameStateChanged(RED_WON)
        }
        if (currentGameState == TIE) {
            playBoard.onGameStateChanged(TIE)
        }
        if(currentGameState == PLAYING) {
            playBoard.humanTurn()
        }
    }

    fun resetClicked(v : View) {
        val playBoard = getPlayBoardFragment()
        playBoard.resetButtonClicked()
        game.clearBoard()
    }
}