package ChessApplication;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceQueen extends Piece {

    public PieceQueen(int type) {
        super(type);
		image = new Image("file:assets/" + (type == BLACK ? "black" : "white") + "_queen.png");
		view = new ImageView(image);
    }
    
    public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
        List<Vec2d> moves = new ArrayList<Vec2d>();

		for (int xpos = x - 1, ypos = y - 1; xpos >= 0 && ypos >= 0; --xpos, --ypos) {
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
		for (int xpos = x - 1, ypos = y + 1; xpos >= 0 && ypos < board.length; --xpos, ++ypos) {
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
		for (int xpos = x + 1, ypos = y + 1; xpos < board.length && ypos < board.length; ++xpos, ++ypos) {
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
		for (int xpos = x + 1, ypos = y - 1; xpos < board.length && ypos >= 0; ++xpos, --ypos) {
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
}
