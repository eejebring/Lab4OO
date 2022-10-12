import java.util.Objects;

public class RuleEngine {
    private int rowLengthToWin;
    private boolean canOverrideOtherPlayers;
    private Position boardSize;
    RuleEngine (int gameType) {
        // rule nr 1: tick tack toe rule nr 2: Renju
        // to do implement the Renju forbidden moves
        rowLengthToWin = new int[] {3,5}[gameType];
        canOverrideOtherPlayers = new boolean[] {false, false}[gameType];
        boardSize = new Position[] {new Position(2,2), new Position(16,16)}[gameType];
    }

    public boolean checkRules(Board board, Position position, Player mover) throws RuleViolation {
        Player unit = board.getUnit(position);
        System.out.println(unit);
        if (!canOverrideOtherPlayers && Objects.nonNull(unit)) {
            throw new RuleViolation("Cannot override previous moves");
        }

        boolean win = rowLengthToWin <= ( 1 +
                countUnitsInDirection(board, mover, position, new Position(1,1)) +
                countUnitsInDirection(board, mover, position, new Position(-1,-1))
                );
        win |= rowLengthToWin <= ( 1 +
                countUnitsInDirection(board, mover, position, new Position(1,0)) +
                countUnitsInDirection(board, mover, position, new Position(-1,0))
        );
        win |= rowLengthToWin <= ( 1 +
                countUnitsInDirection(board, mover, position, new Position(0,1)) +
                countUnitsInDirection(board, mover, position, new Position(0,-1))
        );
        win |= rowLengthToWin <= ( 1 +
                countUnitsInDirection(board, mover, position, new Position(1,-1)) +
                countUnitsInDirection(board, mover, position, new Position(-1,1))
        );
        System.out.println(countUnitsInDirection(board, mover, position, new Position(0,1)));
        return win;
    }

    public Position getBoardSize() {
        return boardSize;
    }

    private int countUnitsInDirection(Board board, Player mover, Position previousPosition, Position direction) {
        Position currentPosition = previousPosition.add(direction);
        if (board.getUnit(currentPosition) == mover) {
            return 1 + countUnitsInDirection(board,mover,currentPosition,direction);
        }
        else {
            return 0;
        }
    }
}
