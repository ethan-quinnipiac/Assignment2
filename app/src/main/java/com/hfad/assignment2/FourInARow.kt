//Ethan Lanier
//Assignment 2
//2/24/24

import GameConstants.BLUE
import GameConstants.BLUE_WON
import GameConstants.COMPUTER_PLAYER
import GameConstants.EMPTY
import GameConstants.HUMAN_PLAYER
import GameConstants.PLAYING
import GameConstants.RED
import GameConstants.RED_WON
import GameConstants.TIE

/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @date 2/12/2022
 */
class FourInARow
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }

    override fun clearBoard() {

        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = EMPTY // sets every space to 0
            }
        }
    }

    override fun setMove(player: Int, location: Int) {

        var the_col: Int = location
        var the_row: Int = 0

        while (the_col >= 6) {
            the_col -= 6
            the_row++
        }//convert 0-35 to a space on the board
        val color = if ( player == HUMAN_PLAYER) {
            BLUE
        }
        else {
            RED
        }
        board[the_row][the_col] = color //sets the space to the players number
    }

    override fun computerMove(): Int {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == EMPTY) {
                    return (row * 6) + col // returns first open space
                }
            }
        }
        return -1
    }

    override fun checkForWinner(): Int {


        var has_won: Boolean = false;
        for (current_player in HUMAN_PLAYER..COMPUTER_PLAYER) {
            val color = if (current_player == HUMAN_PLAYER) {
                BLUE
            }
            else {
                RED
            }
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row][col + 1] == color &&
                        board[row][col + 2] == color &&
                        board[row][col + 3] == color
                    ) {
                        has_won = true // returns true if there is a 4 in a row horizontally
                    }
                }
            }
            for (row in 0 until GameConstants.ROWS - 3) {
                for (col in 0 until GameConstants.COLS) {
                    if (board[row][col] == color &&
                        board[row + 1][col] == color &&
                        board[row + 2][col] == color &&
                        board[row + 3][col] == color
                    ) {
                        has_won = true // checks vertically
                    }
                }
            }
            for (row in 3 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row - 1][col + 1] == color &&
                        board[row - 2][col + 2] == color &&
                        board[row - 3][col + 3] == color
                    ) {
                        has_won = true // checks diagonally to the left
                    }
                }
            }
            for (row in 0 until GameConstants.ROWS - 3) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row + 1][col + 1] == color &&
                        board[row + 2][col + 2] == color &&
                        board[row + 3][col + 3] == color
                    ) {
                        has_won = true //checks diagonally to the right
                    }
                }
            }
            if (has_won) {
                if (current_player == HUMAN_PLAYER) {
                    return BLUE_WON;
                }
                else {
                    return RED_WON
                }
            }
            if (isTie()) {
                return TIE
            }


        }
        return PLAYING
    }


    private fun isTie(): Boolean {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == EMPTY) {
                    return false //if every tile is empty return false, else true
                }
            }
        }
        return true
    }
}

