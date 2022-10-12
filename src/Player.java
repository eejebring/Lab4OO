import java.util.Iterator;

public class Player {
    private String name;
    private char symbol;

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}

