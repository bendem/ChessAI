package be.bendem.chess;

import be.bendem.chess.ai.BoardState;
import be.bendem.chess.pieces.Type;
import be.bendem.chess.utils.array2d.Array2D;
import org.apache.commons.lang3.Validate;

import java.util.stream.Stream;

/**
 * TODO The board should only store Type[][] so that the pieces do not contain any game state
 */
public class GameBoard implements Board {

    public static boolean isWhite(Position position) {
        return (position.getX() + position.getY()) % 2 == 0;
    }

    public static boolean isBlack(Position position) {
        return !isWhite(position);
    }

    private final Piece[][] pieces;

    public GameBoard() {
        pieces = Array2D.create(Piece.class, WIDTH, HEIGHT);

        initRow(0, Color.Black);
        initRow(1, Color.Black);
        initRow(6, Color.White);
        initRow(7, Color.White);
    }

    private void initRow(int row, Color color) {
        if(row == 1 || row == 6) {
            Array2D.fillRow(pieces, row, new Piece(Type.Pawn, color));
        }

        for(int i = 0; i < WIDTH; i++) {
            Type type;
            switch(i) {
                case 0:
                case 7:
                    type = Type.Rook;
                    break;
                case 1:
                case 6:
                    type = Type.Knight;
                    break;
                case 2:
                case 5:
                    type = Type.Bishop;
                    break;
                case 3:
                    type = Type.Queen;
                    break;
                case 4:
                    type = Type.King;
                    break;
                default:
                    throw new AssertionError("Invalid column " + i);
            }
            pieces[i][row] = new Piece(type, color);
        }
    }

    @Override
    public Piece get(Position position) {
        return Array2D.get(pieces, position);
    }

    /**
     * Check if the castle moves are valid on the board
     *
     * @param castle The move to check
     * @return Returns true if the moves can be executed, false otherwise
     *
     * TODO Move that to King
     */
    public boolean canCastle(Castle castle) {
        if(!castle.isValid(this)) {
            return false;
        }

        Move kingMove = castle.getKingMove();
        Move rookMove = castle.getRookMove();
        if(get(kingMove.getFrom()).hasMoved() || get(rookMove.getFrom()).hasMoved()) {
            return false;
        }

        if(!isEmpty(kingMove.getTo()) || !isEmpty(rookMove.getTo())) {
            return false;
        }

        if(kingMove.getDirection() == Direction.KingCastleLeft && !isEmpty(new Position(kingMove.getFrom(), Direction.Left))) {
            return false;
        }

        // TODO Check if king.getTo or rook.getTo is menaced
        return true;
    }

    @Override
    public GameBoard move(Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();
        Piece piece = Array2D.get(pieces, from);

        Array2D.set(pieces, to, piece);
        Array2D.set(pieces, from, null);

        return this;
    }

    @Override
    public Position getPosition(Piece piece) {
        return Array2D.searchPosition(pieces, p -> p.color == piece.color && p.type == piece.type).get();
    }

    @Override
    public boolean isEmpty(Position position) {
        return get(position) == null;
    }

    @Override
    public BoardState snapshot() {
        return new BoardState(Array2D.copy(pieces));
    }

    @Override
    public Stream<Piece> stream() {
        return Array2D.stream(this.pieces);
    }

    public Move createMove(Position position, Direction direction) {
        return createMove(position, direction, 1);
    }

    public Move createMove(Position position, Direction direction, int count) {
        return new Move(position, direction, count);
    }

    public Castle createCastle(Piece king, Direction direction) {
        Validate.isTrue(Direction.getKingCastles().contains(direction));

        Position rookCoord = new Position(direction.isLeft() ? 0 : 7, getPosition(king).getY());

        Move kingMove = createMove(getPosition(king), direction, 1);
        Move rookMove = createMove(rookCoord, direction == Direction.KingCastleLeft ? Direction.RookCastleRight : Direction.RookCastleLeft, 1);

        return new Castle(kingMove, rookMove);
    }

}
