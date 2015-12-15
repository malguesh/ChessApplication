package ChessApplication;

import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//class declaration - abstract because we will not want to create a Piece object but we would
//like to specify the private fields that all pieces should have in addition to their behaviours
public abstract class Piece {
	
	//piece can be either white (1) or black (2)
	private int type;
	//image used to display the piece
	protected Image image;
	public ImageView view;
	public enum EPieceType {
		PAWN,
		ROOK,
		KNIGHT,
		BISHOP,
		QUEEN,
		KING,
		NONE
	}

	public EPieceType pieceType = EPieceType.NONE;

	public static final int WHITE = 1;
	public static final int BLACK = 2;

	public Piece(int type) {
		this.type = type;
	}

	public int GetType() {
		return (type);
	}
	
	//move method

	abstract public List<Vec2d> getMoves(int x, int y, Piece[][] board);
	abstract public List<Vec2d> getMovesNoCheck(int x, int y, Piece[][] board);
}
