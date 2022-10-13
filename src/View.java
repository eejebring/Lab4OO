import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private Controller controller;
    private JButton[][] buttons;
    private JLabel announcer;
    private JLabel judgeWarning;
    View (Controller controller, Position boardSize) {
        System.out.println("Hello world!");
        this.controller = controller;
        this.buttons = new JButton[boardSize.getX() + 1][boardSize.getY() + 1];

        JFrame frame = new JFrame("FlexibleTickTackToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel myButtonPanel = new JPanel();

        myButtonPanel.setLayout(boardSize.toGridLayout());

        PositionIterator gridTile = boardSize.iterator();
        while (gridTile.hasNext()) {
            final Position buttonPosition = gridTile.next();
            JButton button = buttons[buttonPosition.getX()][buttonPosition.getY()] = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.unitClicked(buttonPosition);
                }
            });
            myButtonPanel.add(button);
        }

        JPanel myTextPanel = new JPanel();
        myTextPanel.setLayout(new GridLayout(2,1));
        myTextPanel.setPreferredSize(new Dimension(150,50));
        announcer = new JLabel("", SwingConstants.CENTER);
        judgeWarning = new JLabel("", SwingConstants.CENTER);
        myTextPanel.add(announcer);
        myTextPanel.add(judgeWarning);

        JPanel myMainPanel = new JPanel();
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));
        myMainPanel.add(myButtonPanel);
        myMainPanel.add(myTextPanel);

        frame.getContentPane().add(myMainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void changeUnitSymbol(Position unit, char symbol) {
        buttons[unit.getX()][unit.getY()].setText("" + symbol);
    }
    public void setAnnouncer(String message) {announcer.setText(message);}
    public void setJudgeWarning(String warning) {judgeWarning.setText(warning);}
    public void setButtonsEnabled(boolean state) {
        for (JButton[] buttonRows : buttons) {
            for (JButton button : buttonRows) {
                button.setEnabled(state);
            }
        }
    }
}
