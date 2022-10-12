public class Board {
    Position boardSize;
    Player[][] boardStates;
    RuleEngine judge;
    boolean boardFinished;

    Board(Position boardSize, RuleEngine judge) {
        this.boardSize = boardSize;
        boardStates = new Player [boardSize.getX() + 1] [boardSize.getY() + 1];
        this.judge = judge;
        boardFinished = false;
    }

    public Player getUnit (Position position) {
        try {
            return boardStates[position.getX()][position.getY()];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private void setUnit (Position position, Player newOwner) {
        boardStates[position.getX()][position.getY()] = newOwner;
    }
    public char move (Position movePosition, Player mover) throws RuleViolation {
        boardFinished = judge.checkRules(this,movePosition,mover);
        setUnit(movePosition, mover);
        return mover.getSymbol();
    }
}
