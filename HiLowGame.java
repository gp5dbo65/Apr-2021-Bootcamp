/***************************************************************************/
/* April 2021 Java Bootcamp                                                */
/*                                                                         */
/* Assignment #2 - Higher/Lower Guessing Game                              */
/* Author: Dave Otis                                                       */
/* Date: May 6, 2021                                                       */
/*                                                                         */
/* This project could have been done all within the "main" method, but     */
/* Trevor wanted us to add a static method to eliminate duplicate code.    */
/*                                                                         */
/* I went ahead and added exception handling logic if the user tries to    */
/* enter non-integer input as a guess.                                     */
/*                                                                         */
/***************************************************************************/

package com.coderscampus.week3;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HiLowGame {
	//class variables
	static final int MAX_TRYS = 5;
	static final int UPPER_BOUND = 100;
	static final String INPUT_PROMPT = "Pick a number between 1 and 100: ";
	static final String PICK_LOWER = "Please pick a lower number";
	static final String PICK_HIGHER = "Please pick a higher number";
	static final String WINNER_MSG = "You win!";
	static final String LOSER_MSG = "You lose!";
	static final String OUT_OF_BOUNDS_MSG = "Your guess is not between 1 and 100, please try again";
	static final String ANSWER_MSG = "The number to guess was: ";
	static final String INVALID_INPUT_MSG = "Error! Invalid integer. Try again.\n";

	public static void main (String[] args) {
		//local variables
		Random randomNumber = new Random();
		int randomInteger = randomNumber.nextInt(UPPER_BOUND) + 1; //Had to add 1 to the randomized integer
		Scanner scanner = new Scanner(System.in);
		int guess = 0;
		int numTrys = 0;
		boolean correctGuess = false;
		
		while( numTrys < MAX_TRYS) {

			try {
				System.out.print(INPUT_PROMPT);
				guess = scanner.nextInt();
			} catch (InputMismatchException e){
				System.out.println(INVALID_INPUT_MSG);
				scanner.nextLine(); // ignore all data entered by the user
				continue;			// go back to the top of the loop
			}
			
			if (validateInput (guess)) {
				numTrys++;			// increment number of tries counter
				if (guess == randomInteger) {
					System.out.println(WINNER_MSG);
					correctGuess = true;
					break;
				} else if (guess < randomInteger) {
					System.out.println(PICK_HIGHER);
				} else {
					System.out.println(PICK_LOWER);
				}
			} //end of if(validateInput) block
		} //end of while loop
		
		if (!correctGuess) {		// Was a correct guess made? No - display loser message
			System.out.println(LOSER_MSG);
			System.out.println(ANSWER_MSG + randomInteger);			
		}

		scanner.close();
	} //end of main method

	// This method is used to validate the integer input to see if the guess is within the bounds of the game
	static boolean validateInput (int guess) {
		if (guess < 1 || guess > UPPER_BOUND) {
			System.out.println(OUT_OF_BOUNDS_MSG);
			return false;			// User guess was out of bounds
		}
		return true;				// User guess was within bounds
	} //end of validateInput method

} //end of class HiLowGame