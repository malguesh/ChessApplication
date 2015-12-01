package ChessApplication;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.List;

public class PieceKing extends Piece{

    public PieceKing(int type) {
        super(type);
        // TODO Auto-generated constructor stub
    }

    public List<Vec2d> getMoves(int x, int y, Piece[][] board) {
        List<Vec2d> moves = new ArrayList<Vec2d>();

        if (board[x + 1][y] == null || board[x + 1][y].GetType() != GetType())
            moves.add(new Vec2d(x + 1, y));
        if (board[x + 1][y + 1] == null || board[x + 1][y + 1].GetType() != GetType())
            moves.add(new Vec2d(x + 1, y + 1));
        if (board[x][y + 1] == null || board[x][y + 1].GetType() != GetType())
            moves.add(new Vec2d(x, y + 1));
        if (board[x - 1][y + 1] == null || board[x - 1][y + 1].GetType() != GetType())
            moves.add(new Vec2d(x - 1, y + 1));
        if (board[x - 1][y] == null || board[x - 1][y].GetType() != GetType())
            moves.add(new Vec2d(x - 1, y));
        if (board[x - 1][y - 1] == null || board[x - 1][y - 1].GetType() != GetType())
            moves.add(new Vec2d(x - 1, y - 1));
        if (board[x][y - 1] == null || board[x][y - 1].GetType() != GetType())
            moves.add(new Vec2d(x, y - 1));
        if (board[x + 1][y - 1] == null || board[x + 1][y - 1].GetType() != GetType())
            moves.add(new Vec2d(x + 1, y - 1));
        return (moves);
    }
}
