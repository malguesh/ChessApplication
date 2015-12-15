package ChessApplication;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceRook extends Piece {
	
	public PieceRook(int type) {
		super(type);
		image = new Image("file:assets/" + (type == BLACK ? "black" : "white") + "_rook.png");
		view = new ImageView(image);
		pieceType = EPieceType.ROOK;
	}

	public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
		List<Vec2d> moves = new ArrayList<Vec2d>();

		for (int xpos = x - 1; xpos >= 0; --xpos) {
			if (board[xpos][y] == null)
				moves.add(new Vec2d(xpos, y));
			else if (board[xpos][y].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, y));
				break;
			}
			else
				break;
		}
		for (int xpos = x + 1; xpos < board.length; ++xpos) {
			if (board[xpos][y] == null)
				moves.add(new Vec2d(xpos, y));
			else if (board[xpos][y].GetType() != GetType())
			{
				moves.add(new Vec2d(xpos, y));
				break;
			}
			else
				break;
		}
		for (int ypos = y + 1; ypos < board.length; ++ypos) {
			if (board[x][ypos] == null)
				moves.add(new Vec2d(x, ypos));
			else if (board[x][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(x, ypos));
				break;
			}
			else
				break;
		}
		for (int ypos = y - 1; ypos >= 0; --ypos) {
			if (board[x][ypos] == null)
				moves.add(new Vec2d(x, ypos));
			else if (board[x][ypos].GetType() != GetType())
			{
				moves.add(new Vec2d(x, ypos));
				break;
			}
			else
				break;
		}
		return (moves);
	}

	public List<Vec2d> getMovesNoCheck(int x, int y, Piece[][] board) {
		List<Vec2d> moves = new ArrayList<Vec2d>();

		for (int xpos = x - 1; xpos >= 0; --xpos) {
			if (board[xpos][y] == null)
				moves.add(new Vec2d(xpos, y));
			else
			{
				moves.add(new Vec2d(xpos, y));
				break;
			}
		}
		for (int xpos = x + 1; xpos < board.length; ++xpos) {
			if (board[xpos][y] == null)
				moves.add(new Vec2d(xpos, y));
			else
			{
				moves.add(new Vec2d(xpos, y));
				break;
			}
		}
		for (int ypos = y + 1; ypos < board.length; ++ypos) {
			if (board[x][ypos] == null)
				moves.add(new Vec2d(x, ypos));
			else
			{
				moves.add(new Vec2d(x, ypos));
				break;
			}
		}
		for (int ypos = y - 1; ypos >= 0; --ypos) {
			if (board[x][ypos] == null)
				moves.add(new Vec2d(x, ypos));
			else
			{
				moves.add(new Vec2d(x, ypos));
				break;
			}
		}
		return (moves);
	}
}
