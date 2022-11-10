import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Hexagon {

    public static void main(String[] args) {
    JFrame frame = new JFrame();
    JPanel myPanel = new JPanel();
    frame.add(myPanel);

    myPanel.add(new MyLabel(200, Color.YELLOW, Color.CYAN, Color.RED, Color.BLUE));
    frame.pack();
    frame.setSize(1000, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private static class MyLabel extends JLabel{
    int size;
    Color leftTop;
    Color rightTop;
    Color leftBottom;
    Color rightBottom;

    public MyLabel(int size, Color leftTop, Color rightTop, Color leftBottom, Color rightBottom){
        super();
        this.size = size;
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.leftBottom = leftBottom;
        this.rightBottom = rightBottom;
        this.setPreferredSize(new Dimension(512, 512));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint twoColorGradient = new GradientPaint(
            size, 0f, rightTop, 0, 512, leftBottom);

        float radius = 512-(512/4);
        float[] dist = {0f, 1.0f};
        Point2D center = new Point2D.Float(0f, 0f);
        Color noColor = new Color(0f, 0f, 0f, 0f);
        Color[] colors = {leftTop, noColor};
        RadialGradientPaint thirdColor = new RadialGradientPaint(center, radius, dist, colors);


        center = new Point2D.Float(512, 512);
        Color[] colors2 = {rightBottom, noColor};
        RadialGradientPaint fourthColor = new RadialGradientPaint(center, radius, dist, colors2);

        g2d.setPaint(twoColorGradient);
        g2d.fillRect(0, 0, 512, 512);

        g2d.setPaint(thirdColor);
        g2d.fillRect(0, 0, 512, 512);


        g2d.setPaint(fourthColor);
        g2d.fillRect(0, 0, 512, 512);
    }
}
}