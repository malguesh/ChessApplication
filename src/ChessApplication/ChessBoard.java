package ChessApplication;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoard extends Pane {
	
	public ChessBoard() {
		//initalize the board: background, data structures, inital layout of pieces
		background = new Rectangle();
		background.setFill(Color.WHITE);
		getChildren().addAll(background);

		// initialize board array to the correct size		
		board = new int[boardWidth][boardHeight];

		// initialize pieces array to the correct size
		pieces = new Piece[8][8];

		// initialize windows array to the correct size
		boxes = new Box[8][8];
		
		// for loop to populate all arrays to default values and add the windows to the board		 
		for(int i = 0; i < boardWidth; i++) {
			for(int j = 0; j < boardHeight; j++) {
				board[i][j] = EMPTY;
				pieces[i][j] = null;
				if ((i + j) % 2 == 0) {
					boxes[i][j] = new Box(Color.WHITE);
				} else {
					boxes[i][j] = new Box(Color.PURPLE);
				}
				getChildren().add(boxes[i][j]);
			}
		}
		// set the current player to white
		current_player = PlayerWhite;

		// call the winner method to see if there is a winner
		int winner = winner();
		if (winner == 1) {
			resetGame();
		}
	}
	
	//resize method
	@Override
	public void resize(double width, double height) {
		//call the superclass resize method  
		super.resize(width, height);

		// resize the rectangle to take the full size of the widget 
		background.setWidth(width);
		background.setHeight(height);
		
		// calculate the width and height of a cell in which a windows and a piece will sit		 
		cell_width = width / (float)boardWidth;
		cell_height = height / (float)boardHeight;

		// nested for loop to reset the sizes and positions of all pieces that were already placed 
		// and update the position of the windows in the board 
		for (int i = 0; i < boardWidth; ++i)
			for (int j = 0; j < boardHeight; ++j)
			{
				if (boxes[i][j] != null)
				{
//					pieces[i][j].relocate(i * cell_width, j * cell_height);
//					pieces[i][j].resize(cell_width, cell_height);
					boxes[i][j].relocate(i* cell_width, j * cell_height);
					boxes[i][j].resize(cell_width, cell_height);
				}
			}
	}
	
	//reset game method
	public void resetGame() {
		for (int i = 0; i < boardWidth; ++i)
			for (int j = 0; j < boardHeight; ++j)
			{
				board[i][j] = EMPTY;
				getChildren().remove(pieces[i][j]);
				pieces[i][j] = null;
			}
		current_player = PlayerWhite;
	}
	
	//select piece method
	public void selectPiece(double x, double y) {
		
	}
	
	//move piece method
	public void movePiece(double x, double y) {
		
	}
	
	public boolean IsPieceSelected() {
		return (pieceSelected);
	}
	
	public int winner() {
		return winner;
	}
	
	//private fields
	private int boardWidth = 8;			// the width of the Chess board
	private int boardHeight = 8;		// the height of the Chess board
	private int[][] board; 				// 2D array that holds  int values representing the pieces
	private Piece[][] pieces; 			// 2D array that holds the renders of the pieces
	private Rectangle background; 		// background of the board
	private Box[][] boxes; 		// 2D array that holds all the windows (white circles) for the board 
	private double cell_width;			// width of a cell in the board
	private double cell_height; 		// height of a cell in the board
	private int current_player; 		// hold the value of the current player (PlayerWhite or PlayerBlack) 
	private boolean pieceSelected = false;

	// constants to be inserted into the 2D board array to keep track of the location of cells containing 
	// empty, white and black pieces 
	private final int EMPTY = 0;		// 0 is used to indicate that a cell in the board is unoccupied
	private final int PlayerWhite = 1;	// 1 is used to indicate that a cell in the board is occupied by a white piece
	private final int PlayerBlack = 2; // 2 is used to indicate that a cell in the board is occupied by a black piece
	
	int winner = 0;						// variable to determine who the current winner is: 0 - no current winner, 
}
