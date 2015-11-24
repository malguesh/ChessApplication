package ChessApplication;

import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;

//class declaration - abstract because we will not want to create a Piece object but we would
//like to specify the private fields that all pieces should have in addition to their behaviours
public abstract class Piece {
	
	//piece can be either white (1) or black (2)
	private int type;
	//image used to display the piece
	private Image image;
	
	public static final int WHITE = 1;
	public static final int BLACK = 2;

	public Piece(int type) {
		this.type = type;
	}
	
	public int GetType() {
		return (type);
	}
	
	//move method
//	abstract public boolean movePiece(double x, double y);

	abstract public List<Vec2d> getMoves(int x, int y, Piece[][] board);
}
