package ChessApplication;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceKing extends Piece{

    public PieceKing(int type) {
        super(type);
		image = new Image("file:assets/" + (type == BLACK ? "black" : "white") + "_king.png");
		view = new ImageView(image);
    }

    public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
        List<Vec2d> moves = new ArrayList<Vec2d>();

        if (x < board.length - 1 && (board[x + 1][y] == null || board[x + 1][y].GetType() != GetType()))
            moves.add(new Vec2d(x + 1, y));
        if (x < board.length - 1 && y < board.length - 1 && (board[x + 1][y + 1] == null || board[x + 1][y + 1].GetType() != GetType()))
            moves.add(new Vec2d(x + 1, y + 1));
        if (y < board.length - 1 && (board[x][y + 1] == null || board[x][y + 1].GetType() != GetType()))
            moves.add(new Vec2d(x, y + 1));
        if (x > 0 && y < board.length - 1 && (board[x - 1][y + 1] == null || board[x - 1][y + 1].GetType() != GetType()))
            moves.add(new Vec2d(x - 1, y + 1));
        if (x > 0 && (board[x - 1][y] == null || board[x - 1][y].GetType() != GetType()))
            moves.add(new Vec2d(x - 1, y));
        if (x > 0 && y > 0 && (board[x - 1][y - 1] == null || board[x - 1][y - 1].GetType() != GetType()))
            moves.add(new Vec2d(x - 1, y - 1));
        if (y > 0 && (board[x][y - 1] == null || board[x][y - 1].GetType() != GetType()))
            moves.add(new Vec2d(x, y - 1));
        if (x < board.length - 1 && y > 0 && (board[x + 1][y - 1] == null || board[x + 1][y - 1].GetType() != GetType()))
            moves.add(new Vec2d(x + 1, y - 1));
        return (moves);
    }
}
