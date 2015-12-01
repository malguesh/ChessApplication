package ChessApplication;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CustomControl extends Control {
	
	//similar to previous custom controlls but must handle more
	//complex mouse interactions and key interactions
	
	// constructor
	public CustomControl() {
		// set a default skin, generate a board and add the board to the control
		setSkin(new CustomControlSkin(this));
		
		chessBoard = new ChessBoard();
		
		getChildren().add(chessBoard);

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
		super.resize(width, height);
		chessBoard.resize(width, height);
	}

	//private fields of the class
	private ChessBoard chessBoard;	// a Chess board

}
