package ChessApplication;

import ChessApplication.Piece.EPieceType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomControl extends Control {
	
	//similar to previous custom controlls but must handle more
	//complex mouse interactions and key interactions
	
	// constructor
	public CustomControl() {
		// set a default skin, generate a board and add the board to the control
		setSkin(new CustomControlSkin(this));
		
		chessBoard = new ChessBoard();
		getChildren().add(chessBoard);

		pieces = chessBoard.getPieces();
		
		gridPane = new GridPane();
		blackStatusLabel = new Label();
		blackStatusLabel2 = new Label();
		whiteStatusLabel = new Label();
		whiteStatusLabel2 = new Label();
		replayButton = new Button("Replay");
		
		blackStatusLabel.setText("8 pawns, 2 rooks, 2 knights, 2 bishops, 1 king, 1 queen");
		
		whiteStatusLabel.setText("8 pawns, 2 rooks, 2 knights, 2 bishops, 1 king, 1 queen");
		
		gridPane.setMinHeight(100);
		gridPane.setHgap(50);
		gridPane.addRow(0, new Label("Black : "));
		gridPane.addRow(0, blackStatusLabel);
		gridPane.addRow(0, blackStatusLabel2);
		gridPane.addRow(1, new Label("White : "));
		gridPane.addRow(1, whiteStatusLabel);
		gridPane.addRow(1, whiteStatusLabel2);
		gridPane.addRow(2, new Label(""));
		gridPane.addRow(3, replayButton);
		getChildren().add(gridPane);

		// mouse clicked event handler that will try to place a piece on the board
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				chessBoard.selectBox(event.getX(), event.getY() + 50);
				updateStatus();
			}
		});
	
		// key stroke event handler which will call the restGame method when the space bar is pressed
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE) {
					chessBoard.resetGame();
					updateStatus();
				}
			}
		});
		
		replayButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				chessBoard.resetGame();
				updateStatus();
			}
		});
	}

	//override the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the size of the board
		if (width < height - gridPane.getMinHeight()) {
			super.resize(width, width);
			chessBoard.resize(width, width);
			gridPane.resize(width, gridPane.getMinHeight());
		} else {
			super.resize(height - gridPane.getMinHeight(), height - gridPane.getMinHeight());
			chessBoard.resize(height - gridPane.getMinHeight(), height - gridPane.getMinHeight());
			gridPane.resize(height - gridPane.getMinHeight(), gridPane.getMinHeight());
		}
		chessBoard.setTranslateY(-gridPane.getMinHeight() / 2);
		gridPane.setTranslateY(chessBoard.getHeight() - gridPane.getMinHeight() / 2);
	}
	
	public void updateStatus() {
		black_pawns = 0;
		black_rooks = 0;
		black_knights = 0;
		black_bishops = 0;
		black_king = 0;
		black_queen = 0;

		white_pawns = 0;
		white_rooks = 0;
		white_knights = 0;
		white_bishops = 0;
		white_king = 0;
		white_queen = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].GetType() == Piece.BLACK){
						if (pieces[i][j].pieceType == EPieceType.PAWN) {
							black_pawns = black_pawns + 1;
						} else if (pieces[i][j].pieceType == EPieceType.ROOK) {
							black_rooks = black_rooks + 1;
						} else if (pieces[i][j].pieceType == EPieceType.KNIGHT) {
							black_knights = black_knights + 1;
						} else if (pieces[i][j].pieceType == EPieceType.BISHOP) {
							black_bishops = black_bishops + 1;
						} else if (pieces[i][j].pieceType == EPieceType.KING) {
							black_king = black_king + 1;
						} else if (pieces[i][j].pieceType == EPieceType.QUEEN) {
							black_queen = black_queen + 1;
						}
					}else if (pieces[i][j].GetType() == Piece.WHITE){
						if (pieces[i][j].pieceType == EPieceType.PAWN) {
							white_pawns = white_pawns + 1;
						} else if (pieces[i][j].pieceType == EPieceType.ROOK) {
							white_rooks = white_rooks + 1;
						} else if (pieces[i][j].pieceType == EPieceType.KNIGHT) {
							white_knights = white_knights + 1;
						} else if (pieces[i][j].pieceType == EPieceType.BISHOP) {
							white_bishops = white_bishops + 1;
						} else if (pieces[i][j].pieceType == EPieceType.KING) {
							white_king = white_king + 1;
						} else if (pieces[i][j].pieceType == EPieceType.QUEEN) {
							white_queen = white_queen + 1;
						}
					}
				}
			}
		}
		
		blackStatusLabel.setText(black_pawns + " pawns, " 
		+ black_rooks + " rooks, " 
		+ black_knights + " knights, " 
		+ black_bishops + " bishops, " 
		+ black_king + " king, " 
		+ black_queen + " queen");
		whiteStatusLabel.setText(white_pawns + " pawns, " 
		+ white_rooks + " rooks, " 
		+ white_knights + " knights, " 
		+ white_bishops + " bishops, " 
		+ white_king + " king, " 
		+ white_queen + " queen");
		if (chessBoard.checkmate == true && chessBoard.current_player == chessBoard.PlayerBlack) {
			blackStatusLabel2.setText("Checkmate");
		} else if (chessBoard.check == true && chessBoard.current_player == chessBoard.PlayerBlack) {
			blackStatusLabel2.setText("Check");
		} else if (chessBoard.checkmate == true && chessBoard.current_player == chessBoard.PlayerWhite) {
			whiteStatusLabel2.setText("Checkmate");
		} else if (chessBoard.check == true && chessBoard.current_player == chessBoard.PlayerWhite) {
			whiteStatusLabel2.setText("Check");
		} else if (chessBoard.stalemate == true) {
			blackStatusLabel2.setText("Stalemate");
			whiteStatusLabel2.setText("Stalemate");
		} else {
			blackStatusLabel2.setText("");
			whiteStatusLabel2.setText("");
		}
		if (LastPlayer != chessBoard.current_player && chessBoard.checkmate) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("CheckMate !");
			alert.setContentText("Player " + (chessBoard.current_player == chessBoard.PlayerBlack ? "White" : "Black") + " won the game !");
			alert.showAndWait();
		} else if (LastPlayer != chessBoard.current_player && chessBoard.check) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Check !");
			alert.setContentText("Player " + (chessBoard.current_player == chessBoard.PlayerBlack ? "White" : "Black") + " put your king in check !");
			alert.showAndWait();
		} else if (LastPlayer != chessBoard.current_player && chessBoard.stalemate) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Stalemate !");
			alert.setContentText("Stalemate occurred !");
			alert.showAndWait();
		}
		LastPlayer = chessBoard.current_player;
	}

	//private fields of the class
	private ChessBoard chessBoard;	// a Chess board
	private GridPane gridPane;		// a GridPane to display the game status
	private Button replayButton;
	private Label blackStatusLabel; // label to display the numbers of every black pieces
	private Label blackStatusLabel2; // label to display the status of the black player
	private Label whiteStatusLabel; // label to display the numbers of every white pieces
	private Label whiteStatusLabel2; // label to display the status of the white player
	private Piece[][] pieces;		// pieces in the board
	private int LastPlayer = 0;

	// numbers of every pieces present on the board
	private int black_pawns;
	private int black_rooks;
	private int black_knights;
	private int black_bishops;
	private int black_king;
	private int black_queen;

	private int white_pawns;
	private int white_rooks;
	private int white_knights;
	private int white_bishops;
	private int white_king;
	private int white_queen;
	
	

}
