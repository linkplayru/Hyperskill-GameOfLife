import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class JField extends JComponent {

    final private int SIZE = 15;
    private boolean[][] field;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.LIGHT_GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, SIZE * field.length, SIZE * field.length));

        g2.setPaint(Color.BLACK);
        for (int i = 0; i <= field.length; i++) {
            g2.draw(new Line2D.Double(0, i * SIZE, SIZE * field.length, i*SIZE));
            g2.draw(new Line2D.Double(i * SIZE, 0, i*SIZE, SIZE * field.length));
        }

        g2.setPaint(Color.BLUE);
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x]) {
                    g2.fill(new Rectangle2D.Double(x * SIZE, y * SIZE, SIZE + 1, SIZE + 1));
                }
            }
        }

    }

    public void updateField(boolean[][] field) {
        this.field = field;
        repaint();
    }
}
