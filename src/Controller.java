public class Controller {
    Board board;
    View view;
    Player[] allPlayers;
    int activePlayer;
    Controller() {
        RuleEngine rules = new RuleEngine(1);
        Position boardSize = rules.getBoardSize();

        allPlayers = new Player[] {
                new Player("Player_one", 'X'),
                new Player("player_two", 'O')
        };
        activePlayer = 0;


        board = new Board(boardSize, rules);
        view = new View(this, boardSize);
        view.setAnnouncer(allPlayers[activePlayer].getName() + "'s turn!");
    }

    public void unitClicked(Position position) {
        try {
            char symbol = board.move(position, allPlayers[activePlayer]);
            view.changeUnitSymbol(position, symbol);

            if (board.boardFinished) {
                view.setAnnouncer(allPlayers[activePlayer].getName() + " wins the game!");
                view.setButtonsEnabled(false);
                return;
            }

            activePlayer++;
            if (allPlayers.length <= activePlayer) {
                activePlayer = 0;
            }
            view.setAnnouncer(allPlayers[activePlayer].getName() + "'s turn");
            view.setJudgeWarning("");
        }
        catch (RuleViolation ruleViolation) {
            view.setJudgeWarning(ruleViolation.getMessage());
        }
    }
}
