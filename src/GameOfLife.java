import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private Controller controller;

    public GameOfLife() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel genLabel = new JLabel();
        genLabel.setPreferredSize(new Dimension(100, 20));
        genLabel.setForeground(Color.WHITE);
        genLabel.setName("GenerationLabel");
        infoPanel.add(genLabel);

        JLabel aliveLabel = new JLabel();
        aliveLabel.setPreferredSize(new Dimension(100, 20));
        aliveLabel.setForeground(Color.WHITE);
        aliveLabel.setName("AliveLabel");
        infoPanel.add(aliveLabel);

        controlPanel.add(infoPanel);

        JToggleButton playButton = new JToggleButton("Play/Pause");
        playButton.addActionListener(e -> controller.playPause());
        playButton.setName("PlayToggleButton");
        controlPanel.add(playButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> controller.reset());
        resetButton.setName("ResetButton");
        controlPanel.add(resetButton);

        add(controlPanel);

        JField field = new JField();
        field.setPreferredSize(new Dimension(301, 301));
        add(field);

        Universe universe = new Universe(20);
        controller = new Controller(universe, genLabel, aliveLabel, field, playButton);
        controller.start();

        pack();
        setResizable(false);
        setVisible(true);

    }

}