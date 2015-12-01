package ChessApplication;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceBishop extends Piece{

	public PieceBishop(int type) {
		super(type);
		image = new Image("file:assets/" + (type == BLACK ? "black" : "white") + "_bishop.png");
		view = new ImageView(image);
	}

	public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
		List<Vec2d> moves = new ArrayList<Vec2d>();

		for (int xpos = x, ypos = y; x >= 0 && y >= 0; --x, --y) {
			if (board[xpos][ypos] == null)
				moves.add(new Vec2d(xpos, ypos));
			else if (board[xpos][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, ypos));
				break;
			}
			else
				break;
		}
		for (int xpos = x, ypos = y; x >= 0 && y < board.length; --x, ++y) {
			if (board[xpos][ypos] == null)
				moves.add(new Vec2d(xpos, ypos));
			else if (board[xpos][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, ypos));
				break;
			}
			else
				break;
		}
		for (int xpos = x, ypos = y; x < board.length && y < board.length; ++x, ++y) {
			if (board[xpos][ypos] == null)
				moves.add(new Vec2d(xpos, ypos));
			else if (board[xpos][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, ypos));
				break;
			}
			else
				break;
		}
		for (int xpos = x, ypos = y; x < board.length && y >= 0; ++x, --y) {
			if (board[xpos][ypos] == null)
				moves.add(new Vec2d(xpos, ypos));
			else if (board[xpos][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, ypos));
				break;
			}
			else
				break;
		}
		return (moves);
	}
}
