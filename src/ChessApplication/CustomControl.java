package ChessApplication;

import javafx.event.EventHandler;
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

		gridPane = new GridPane();
		gridPane.setMinHeight(100);
		gridPane.setHgap(50);
		gridPane.addRow(0, new Label("Black : "));
		gridPane.addRow(0, new Label("8 pawns, 2 rooks, 2 knights, 2 bishops, 1 king, 1 queen"));
		gridPane.addRow(0, new Label("Check"));
		gridPane.addRow(1, new Label("White : "));
		gridPane.addRow(1, new Label("8 pawns, 2 rooks, 2 knights, 2 bishops, 1 king, 1 queen"));
		gridPane.addRow(1, new Label("Checkmate"));
		getChildren().add(gridPane);

		// mouse clicked event handler that will try to place a piece on the board
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				chessBoard.selectBox(event.getX(), event.getY());
			}
		});
	
		// key stroke event handler which will call the restGame method when the space bar is pressed
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.SPACE)
					chessBoard.resetGame();
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

	//private fields of the class
	private ChessBoard chessBoard;	// a Chess board
	private GridPane gridPane;
}
