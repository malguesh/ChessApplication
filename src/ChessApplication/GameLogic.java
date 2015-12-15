package ChessApplication;

import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLogic {
	
	//method to detect stalemate
	static public Boolean StaleMate(Piece[][] board, int currentPlayer) {

        for (int x = 0; x < board.length; ++x)
            for (int y = 0; y < board.length; ++y)
                if (board[x][y] != null && board[x][y].GetType() != currentPlayer)
                    for (Vec2d i: board[x][y].getMoves(x, y, board))
                        if (CanMovePiece(board, new Vec2d(x, y), i))
                            return (false);
        return (true);
    }

	//method to check check/checkmate
    static public Boolean Check(Piece[][] board, int currentPlayer) {
        List<Vec2d> moves = new ArrayList<>();

        for (int x = 0; x < board.length; ++x)
            for (int y = 0; y < board.length; ++y)
                if (board[x][y] != null && board[x][y].GetType() != currentPlayer)
                    moves.addAll(board[x][y].getMoves(x, y, board));

        for (int x = 0; x < board.length; ++x)
            for (int y = 0; y < board.length; ++y)
                if (board[x][y] != null && board[x][y].GetType() == currentPlayer &&
                        board[x][y].pieceType == Piece.EPieceType.KING && moves.contains(new Vec2d(x, y)))
                    return (true);
        return (false);
    }

    static public Boolean CheckMate(Piece[][] board, int currentPlayer) {
        List<Vec2d> moves = new ArrayList<>();

        for (int x = 0; x < board.length; ++x)
            for (int y = 0; y < board.length; ++y)
                if (board[x][y] != null && board[x][y].GetType() != currentPlayer)
                    for (Vec2d i: board[x][y].getMoves(x, y, board))
                        if (CanMovePiece(board, new Vec2d(x, y), i))
                            return (false);
        return (Check(board, currentPlayer == Piece.WHITE ? Piece.BLACK : Piece.WHITE));
    }

    static public Boolean CanMovePiece(Piece[][] board, Vec2d origin, Vec2d target) {
        Piece[][] copy = new Piece[8][8];

        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                copy[i][j] = board[i][j];
        copy[(int)target.x][(int)target.y] = copy[(int)origin.x][(int)origin.y];
        copy[(int)origin.x][(int)origin.y] = null;
        return (!Check(copy, board[(int)origin.x][(int)origin.y].GetType()));
    }
}
