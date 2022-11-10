import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Wing extends JPanel{
    int x=150;
    int y =150;
    int Width = 100;
    int Height = 100;
    int spin = 0;



public void paintComponent(Graphics g){
super.paintComponent(g);
//int ancho = getWidth();
//int alto  = getHeight();
g.setColor(new Color(250, 0, 0));
g.drawLine(433, 40, 433, 416);
g.drawLine(433, 416, 371, 416);
g.drawLine(371, 416, 371, 434);
g.drawLine(371, 434, 128, 434);
g.drawLine(128, 434, 128, 223);
g.drawLine(128, 223, 116, 223);
g.drawLine(116, 223, 116,505);
g.drawLine(116,505, 135, 505);
g.drawLine(135, 505, 380, 556);
g.drawLine(380, 556, 380, 600);
g.drawLine(380, 556, 380, 600);
g.drawLine(380, 600, 406, 600);
g.drawLine(406, 600, 405, 540);
g.drawLine( 405, 540, 495, 540);
g.drawLine(495, 540, 494, 600);
g.drawLine(494, 600, 520, 600);
g.drawLine(520, 600, 520, 556);
g.drawLine(520, 556, 765, 505);
g.drawLine(765, 505, 784, 505);
g.drawLine(784, 505, 784, 223);
g.drawLine(784, 223, 772, 223);
g.drawLine(772, 223, 772, 434);
g.drawLine(772, 434, 528, 434);
g.drawLine(528, 434, 528, 416);
g.drawLine(528, 416, 467, 416);
g.drawLine(467, 416, 467, 40);
g.drawLine(467, 40, 433, 40);
// g.rotate(Math.toRadians((spin));
 
}
public static void main(String args[]){
Wing panel = new Wing();
JFrame application = new JFrame();
application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
application.add(panel);
application.setSize(1000, 800);
application.setVisible(true);
}

// public class Graphs extends JPanel{
//     @Override
//     public void paint(Graphics g){
//         super.paint(g);
//         Graphics2D g2d = (Graphics2D)g;
//         g.translate(x,y  )
//     }
// }


}