package ChessApplication;

import com.sun.javafx.geom.Vec2d;

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
		

		// initialize box array to the correct size
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

		// initialize the black pieces on the board
		pieces[0][0] = new PieceRook(pieces[0][0].BLACK);
		pieces[1][0] = new PieceKnight(pieces[1][0].BLACK);
		pieces[2][0] = new PieceBishop(pieces[2][0].BLACK);
		pieces[3][0] = new PieceQueen(pieces[3][0].BLACK);
		pieces[4][0] = new PieceKing(pieces[4][0].BLACK);
		pieces[5][0] = new PieceBishop(pieces[5][0].BLACK);
		pieces[6][0] = new PieceKnight(pieces[6][0].BLACK);
		pieces[7][0] = new PieceRook(pieces[7][0].BLACK);
		for (int i = 0; i < boardWidth; i++) {
			int j = 1;
			pieces[i][j] = new PiecePawn(pieces[i][j].BLACK);
		}

		// initialize the white pieces on the board
		for (int i = 0; i < boardWidth; i++) {
			int j = 6;
			pieces[i][j] = new PiecePawn(pieces[i][j].WHITE);
		}
		pieces[0][7] = new PieceRook(pieces[0][7].WHITE);
		pieces[1][7] = new PieceKnight(pieces[1][7].WHITE);
		pieces[2][7] = new PieceBishop(pieces[2][7].WHITE);
		pieces[3][7] = new PieceQueen(pieces[3][7].WHITE);
		pieces[4][7] = new PieceKing(pieces[4][7].WHITE);
		pieces[5][7] = new PieceBishop(pieces[5][7].WHITE);
		pieces[6][7] = new PieceKnight(pieces[6][7].WHITE);
		pieces[7][7] = new PieceRook(pieces[7][7].WHITE);
		
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (pieces[i][j] != null)
					getChildren().add(pieces[i][j].view);
		
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
					boxes[i][j].relocate(i* cell_width, j * cell_height);
					boxes[i][j].resize(cell_width, cell_height);
				}
				if (pieces[i][j] != null)
				{
					pieces[i][j].view.relocate(i * cell_width, j * cell_height);
					pieces[i][j].view.setFitWidth(cell_width);
					pieces[i][j].view.setFitHeight(cell_height);
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
	public void selectBox(double x, double y) {
		int column = (int) (x / cell_width);
		int line = (int) (y / cell_height);
		
		if (pieces[column][line] != null && pieces[column][line].GetType() == current_player)
		{
			for (int i = 0; i < boardWidth; ++i)
				for (int j = 0; j < boardHeight; ++j)
				{
					boxes[i][j].SetHighlighted(false);
					boxes[i][j].SetSelected(false);
				}
			if (pieceSelected != pieces[column][line])
			{
				pieceSelected = null;
				selectedPiecePos = null;
				boxes[column][line].TriggerSelect();
				for (Vec2d pos : pieces[column][line].getMoves(column, line, pieces)) {
					boxes[(int) pos.x][(int) pos.y].TriggerHighlight();
				}
				if (pieces[column][line] == pieceSelected)
				{
					pieceSelected = null;
					selectedPiecePos = null;
				}
				else
				{
					pieceSelected = pieces[column][line];
					selectedPiecePos = new Vec2d(column, line);
				}
			}
			else {
				pieceSelected = null;
				selectedPiecePos = null;
			}
		}
		else if (pieceSelected != null)
		{
			if (boxes[column][line].IsHighlighted())
				movePiece(column, line);
			for (int i = 0; i < boardWidth; ++i)
				for (int j = 0; j < boardHeight; ++j)
				{
					boxes[i][j].SetHighlighted(false);
					boxes[i][j].SetSelected(false);
				}
			pieceSelected = null;
			selectedPiecePos = null;
		}
	}
	
	//move piece method
	public void movePiece(int x, int y) {
		pieceSelected.view.relocate(x * cell_width, y * cell_height);
		if (pieces[x][y] != null)
			getChildren().remove(pieces[x][y].view);
		pieces[x][y] = pieceSelected;
		pieces[(int) selectedPiecePos.x][(int) selectedPiecePos.y] = null;
		current_player = (current_player == PlayerWhite ? PlayerBlack : PlayerWhite);
	}
	
	public boolean IsPieceSelected() {
		return (pieceSelected == null ? false : true);
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
	private Piece pieceSelected = null;
	private Vec2d selectedPiecePos = null;

	// constants to be inserted into the 2D board array to keep track of the location of cells containing 
	// empty, white and black pieces 
	private final int EMPTY = 0;		// 0 is used to indicate that a cell in the board is unoccupied
	private final int PlayerWhite = 1;	// 1 is used to indicate that a cell in the board is occupied by a white piece
	private final int PlayerBlack = 2; // 2 is used to indicate that a cell in the board is occupied by a black piece
	
	int winner = 0;						// variable to determine who the current winner is: 0 - no current winner, 
}
