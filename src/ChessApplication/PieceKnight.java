package ChessApplication;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceKnight extends Piece{
	
	public PieceKnight(int type) {
		super(type);
		image = new Image("file:assets/" + (type == BLACK ? "black" : "white") + "_knight.png");
		view = new ImageView(image);
	}

	public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
		List<Vec2d> moves = new ArrayList<Vec2d>();

		if (x - 2 >= 0 && y - 1 >= 0 && (board[x - 2][y - 1] == null || board[x - 2][y - 1].GetType() != GetType()))
			moves.add(new Vec2d(x - 2, y - 1));
		if (x - 2 >= 0 && y + 1 < board.length && (board[x - 2][y + 1] == null || board[x - 2][y + 1].GetType() != GetType()))
			moves.add(new Vec2d(x - 2, y + 1));
		if (x + 2 < board.length && y + 1 < board.length && (board[x + 2][y + 1] == null || board[x + 2][y + 1].GetType() != GetType()))
			moves.add(new Vec2d(x + 2, y + 1));
		if (x + 2 < board.length && y - 1 >= 0 && (board[x + 2][y - 1] == null || board[x + 2][y - 1].GetType() != GetType()))
			moves.add(new Vec2d(x + 2, y - 1));
		if (y - 2 >= 0 && x - 1 >= 0 && (board[x - 1][y - 2] == null || board[x - 1][y - 2].GetType() != GetType()))
			moves.add(new Vec2d(x - 1, y - 2));
		if (y - 2 >= 0 && x + 1 < board.length && (board[x + 1][y - 2] == null || board[x + 1][y - 2].GetType() != GetType()))
			moves.add(new Vec2d(x + 1, y - 2));
		if (y + 2 < board.length && x - 1 >= 0 && (board[x - 1][y + 2] == null || board[x - 1][y + 2].GetType() != GetType()))
			moves.add(new Vec2d(x - 1, y + 2));
		if (y + 2 < board.length && x + 1 < board.length && (board[x + 1][y + 2] == null || board[x + 1][y + 2].GetType() != GetType()))
			moves.add(new Vec2d(x + 1, y + 2));
		return (moves);
	}

}
