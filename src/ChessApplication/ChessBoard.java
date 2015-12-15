package ChessApplication;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoard extends Pane {
	
	public ChessBoard() {
		//initalize the board: background, data structures, initial layout of pieces
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

		resetGame();
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
				if (pieces[i][j] != null)
					getChildren().remove(pieces[i][j].view);
				pieces[i][j] = null;
			}
		// initialize the black pieces on the board
		pieces[0][0] = new PieceRook(Piece.BLACK);
		pieces[1][0] = new PieceKnight(Piece.BLACK);
		pieces[2][0] = new PieceBishop(Piece.BLACK);
		pieces[3][0] = new PieceQueen(Piece.BLACK);
		pieces[4][0] = new PieceKing(Piece.BLACK);
		pieces[5][0] = new PieceBishop(Piece.BLACK);
		pieces[6][0] = new PieceKnight(Piece.BLACK);
		pieces[7][0] = new PieceRook(Piece.BLACK);
		for (int i = 0; i < boardWidth; i++) {
			int j = 1;
			pieces[i][j] = new PiecePawn(Piece.BLACK);
		}

		// initialize the white pieces on the board
		for (int i = 0; i < boardWidth; i++) {
			int j = 6;
			pieces[i][j] = new PiecePawn(Piece.WHITE);
		}
		pieces[0][7] = new PieceRook(Piece.WHITE);
		pieces[1][7] = new PieceKnight(Piece.WHITE);
		pieces[2][7] = new PieceBishop(Piece.WHITE);
		pieces[3][7] = new PieceQueen(Piece.WHITE);
		pieces[4][7] = new PieceKing(Piece.WHITE);
		pieces[5][7] = new PieceBishop(Piece.WHITE);
		pieces[6][7] = new PieceKnight(Piece.WHITE);
		pieces[7][7] = new PieceRook(Piece.WHITE);

		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (pieces[i][j] != null)
					getChildren().add(pieces[i][j].view);

		// set the current player to white
		current_player = PlayerWhite;
		pieceSelected = null;
		selectedPiecePos = null;
		canPlay = true;
	}
	
	//select piece method
	public void selectBox(double x, double y) {
		int column = (int) (x / cell_width);
		int line = (int) (y / cell_height);

		if (canPlay)
		{
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
						if (GameLogic.CanMovePiece(pieces, new Vec2d(column, line), pos))
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
	}
	
	//move piece method
	public void movePiece(int x, int y) {
		pieceSelected.view.relocate(x * cell_width, y * cell_height);
		if (pieces[x][y] != null)
			getChildren().remove(pieces[x][y].view);
		pieces[x][y] = pieceSelected;
		pieces[(int) selectedPiecePos.x][(int) selectedPiecePos.y] = null;
		if (GameLogic.CheckMate(pieces, current_player))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("CheckMate !");
			alert.setContentText("Player " + (current_player == PlayerWhite ? "White" : "Black") + " won the game !");
			alert.showAndWait();
			canPlay = false;
		}
		else if (GameLogic.Check(pieces, current_player == PlayerWhite ? PlayerBlack : PlayerWhite))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Check !");
			alert.setContentText("Player " + (current_player == PlayerWhite ? "White" : "Black") + " put your king in check !");
			alert.showAndWait();
		}
		else if (GameLogic.StaleMate(pieces, current_player))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Stalemate !");
			alert.setContentText("Stalemate occurred !");
			alert.showAndWait();
			canPlay = false;
		}
		current_player = (current_player == PlayerWhite ? PlayerBlack : PlayerWhite);
	}
	
	public boolean IsPieceSelected() {
		return (pieceSelected != null);
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
	public static int current_player; 		// hold the value of the current player (PlayerWhite or PlayerBlack)
	private Piece pieceSelected = null;
	private Vec2d selectedPiecePos = null;
	private Boolean canPlay = true;

	// constants to be inserted into the 2D board array to keep track of the location of cells containing 
	// empty, white and black pieces 
	private final int EMPTY = 0;		// 0 is used to indicate that a cell in the board is unoccupied
	private final int PlayerWhite = 1;	// 1 is used to indicate that a cell in the board is occupied by a white piece
	private final int PlayerBlack = 2; // 2 is used to indicate that a cell in the board is occupied by a black piece
	
	int winner = 0;						// variable to determine who the current winner is: 0 - no current winner, 
}
