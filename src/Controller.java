import javax.swing.*;

public class Controller extends Thread {

    private boolean isPlaying;
    private Universe universe;
    private JLabel genLabel;
    private JLabel aliveLabel;
    private JField field;
    private JToggleButton playButton;

    public Controller(Universe universe, JLabel genLabel, JLabel aliveLabel, JField field, JToggleButton playButton) {
        this.universe = universe;
        this.genLabel = genLabel;
        this.aliveLabel = aliveLabel;
        this.field = field;
        this.playButton = playButton;
        updateUI();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                if (isPlaying) {
                    universe.genNext();
                    updateUI();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI() {
        field.updateField(universe.getField());
        genLabel.setText("Generation #" + universe.getGeneration());
        aliveLabel.setText("Alive: " + universe.getAlive());
        playButton.setSelected(isPlaying);
    }

    public void playPause() {
        isPlaying = !isPlaying;
        updateUI();
    }

    public void reset() {
        isPlaying = false;
        universe.genFirst();
        updateUI();
    }

}
