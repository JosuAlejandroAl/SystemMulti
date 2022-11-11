import java.awt.*;
import javax.swing.*;

class Wing extends JPanel {
    public void paintComponent(Graphics g) {
        int [] x = {443, 443, 371, 371, 128, 128, 116, 116, 380, 380, 406, 405, 495, 494, 520, 520, 756, 784, 784, 772, 772, 528, 528, 467, 467, 443};
        int [] y = {40, 416, 416, 434, 434, 223, 223, 505, 556, 600,600, 540, 540, 600, 600, 556, 505, 505, 223, 223, 434, 434, 416, 416, 40, 40};
        g.drawPolygon(x, y, 26);
        g.setColor(Color.BLUE);
        g.fillPolygon(x, y, 26);
        //Polygon p = new Polygon();
        //g.fillPolygon(p);
    }
    public static void main( String args[] ) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Fill Polygon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(1000, 800);

        Wing panel = new Wing();

        frame.add(panel);
        frame.setVisible(true);      
    }
}