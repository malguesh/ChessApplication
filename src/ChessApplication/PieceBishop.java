package ChessApplication;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class PieceBishop extends Piece{

	public PieceBishop(int type) {
		super(type);
		// TODO Auto-generated constructor stub
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
