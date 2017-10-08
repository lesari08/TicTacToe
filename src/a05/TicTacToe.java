/**
 * 
 */
package a05;


//Import classes
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Leslie Aririguzo
 *
 */
public class TicTacToe {
	
	
	//Variables to help create the tic tac toe board
	public static final int BOARD_LENGTH = 21;
	public static final int BOARD_DIVIDE = BOARD_LENGTH / 3; 
	public static final int ROW_SIZE = 3;
	public static final int COL_SIZE = 3;
	public static final int MAX_MOVES = 9;
	
	//used to keep track of whose turn it is
	int playersTurn;
	//cRow & cCol are used to save the last inputed coordinates from the user
	int cRow;
	int cCol;
	int winner;
	//Variable used to flag if an exception occurred
	boolean wentToCatch;
	boolean gameOver;
	int [][] gameSpaces;
	
	//tracks the number of moves. Used to find out when board is full
	int numOfMoves;
	Scanner scanner;
	
	//constructor
	public TicTacToe()
	{
		playersTurn = 1;
		wentToCatch = false;
		gameOver = false;
		gameSpaces = new int[ROW_SIZE][COL_SIZE];
		numOfMoves = 0;
		scanner = new Scanner(System.in);
	}
	
	public void startGame()
	{
		System.out.println("**This is Tic Tac Toe**\n");
		
		printBoard();
		
		System.out.println("\nRules: Choose a move by typing the coordinates"
				+ " of the space you'd like to place your move on.\nFirst enter "
				+ "the row number followed by the column number(Row,Column)");
		
		askForMove();
	}
	public void printBoard()
	{
		//this variable acts as a row count for printing purposes
	    int rowN = 0;
	
		//print labels for column numbers
		System.out.printf("\t%d%3d%3d\n\n", 0, 1, 2);
	
		for(int i = 0; i < ROW_SIZE; i++)
		{
			//if row is even, print the label for its row number
			//else print a space 
			System.out.printf("%d\t", rowN++);
			
			for(int j = 0; j < COL_SIZE; j++)
			{
				//print the game symbols
				switch(gameSpaces[i][j])
				{
					case 0:
						System.out.print(" ");
						break;
					case 1:
						System.out.print("X");
						break;
					case 2:
						System.out.print("O");
						break;		
				}
						
				if(j != 2)
				{
					System.out.print(" |");
				}
				else
				{
					System.out.print(" ");
				}
			}
			if(i != 2)
			{
			System.out.println("\n\t--------");
			}
		}
		
		System.out.println();
	}
	
	/* Method that asks for the next move from the user */
	public void askForMove()
	{
		while (gameOver == false && winner == 0)
		{
			try
			{
				System.out.printf("\nPlayer %d's turn. Enter the coordinates for your "
					+ "next move", playersTurn);
			
				cRow = scanner.nextInt();
				cCol = scanner.nextInt();
		
				//To ensure the integers entered at not out of bounds or representing
				//a taken spot on the game board 
				while (cRow >= ROW_SIZE || cCol >= COL_SIZE || 
						gameSpaces[cRow][cCol] != 0)
				{
					System.out.println("Invalid parameters. Please reenter 2 valid numbers"
							+ "for the coordinate(x,y) space");
			
					cRow = scanner.nextInt();
					cCol = scanner.nextInt();	
				}
				
				//Adds the move to the game board based on who's turn it is
				gameSpaces[cRow][cCol] = (playersTurn == 1)? 1: 2;
				
				//print the updated board with the newly added move
				printBoard();
				
				if(checkForWinner() == true)
				{
					winner = playersTurn;
				}
		
		}
		catch (InputMismatchException e)
		{
			wentToCatch = true;
			scanner.nextLine();
			System.out.println("Invalid parameters");
		}
			
	  } //end of while
		
			gameOver();
	}
	
	
	public boolean checkForWinner()
	{
		int mid = 1;
		
		//If there's no more moves to make, set the game over flag to true
		if( ++numOfMoves == MAX_MOVES)
		{
			gameOver = true;
		}
	
		//If else statements to see if a winning move was made
		//check the move to see if it's the same as the move next to it
		if(gameSpaces[cRow][cCol] == gameSpaces[cRow][(cCol + 1) % 3])
		{
			if(gameSpaces[cRow][(cCol + 1) % 3] == gameSpaces[cRow][(cCol + 2) % 3])
			{
				return true;

			}
		}
		else if(gameSpaces[cRow][cCol] == gameSpaces[(cRow + 1) % 3][cCol])
		{
			if(gameSpaces[(cRow + 1) % 3][cCol] == gameSpaces[(cRow + 2) % 3][cCol])
			{
				return true;
			}
		}
		//check left diagonals
		else if(cRow == cCol)
		{
			int j = (cRow + 1) % 3;
			if(gameSpaces[cRow][cCol] == gameSpaces[j][j])
			{
				j = ++j % 3;
				if(gameSpaces[cRow][cCol] == gameSpaces[j][j])
				{
					return true;
				}
			}
		}
		//check right diagonal
		else if(cCol + cRow == 2)
		{
			if(gameSpaces[cRow][cCol] == gameSpaces[cCol][cRow])
				if(gameSpaces[mid][mid] == gameSpaces[cRow][cCol])
				{
					return true;
				}
		}
		
		//Switch to the next players turn
		playersTurn = (playersTurn == 1)? 2: 1;
		
		return false;
	}
	
	/*Clears the game board and variables for a new game */
	public void clear()
	{
		for(int i = 0; i < ROW_SIZE; i++)
		{
			for(int j = 0; j < COL_SIZE; j++)
			{
				gameSpaces[i][j] = 0;
			}
		}
		playersTurn = 1;
		gameOver = false;
		numOfMoves = 0;
		winner = 0;
	}
	
	
	public void gameOver()
	{
		int playAgain;
		
		System.out.println("Game over!");
		System.out.printf("And the winner is.....");
		
		switch(winner)
		{
		case 0:
			System.out.print("It's a tie!\n");
			break;
		case 1:
			System.out.print("Player 1!\n");
			break;
		case 2:
			System.out.print("Player 2!\n");
			break;
		default:
			System.out.print("Hmm seems the results cannot be found\n");
		}
		
		System.out.println("Would you like to play again? Type 1 for yes or 2 for no");
		
		//To make sure the user enters a valid integer
		if(scanner.hasNextInt())
		{
			playAgain = scanner.nextInt();
			
			if(playAgain == 1)
			{
				clear();
				startGame();
			}
			else if(playAgain == 2)
			{
				System.out.println("Goodbye");
			}
			else
			{
				System.out.println("Could not understand selection. Goodbye");
			}
		}
		else
		{
			System.out.println("Invalid entry. Goodbye");
		}
		
	}
	
	
	
}
