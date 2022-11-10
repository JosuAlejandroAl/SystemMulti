import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
//Imports
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class Wing extends JPanel{
    // int cont = 1;
    int x=150;
    int y =150;
    // // int Width = 100;
    // // int Height = 100;
    int spin = 0;    




public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(WIDTH/2, HEIGHT/2);
    g2d.rotate(Math.toRadians(spin++));
    g2d.setColor(new Color(250, 0, 0));
    g2d.drawLine(433, 40, 433, 416);
    g2d.drawLine(433, 416, 371, 416);
    g2d.drawLine(371, 416, 371, 434);
    g2d.drawLine(371, 434, 128, 434);
    g2d.drawLine(128, 434, 128, 223);
    g2d.drawLine(128, 223, 116, 223);
    g2d.drawLine(116, 223, 116,505);
    g2d.drawLine(116,505, 135, 505);
    g2d.drawLine(135, 505, 380, 556);
    g2d.drawLine(380, 556, 380, 600);
    g2d.drawLine(380, 556, 380, 600);
    g2d.drawLine(380, 600, 406, 600);
    g2d.drawLine(406, 600, 405, 540);
    g2d.drawLine( 405, 540, 495, 540);
    g2d.drawLine(495, 540, 494, 600);
    g2d.drawLine(494, 600, 520, 600);
    g2d.drawLine(520, 600, 520, 556);
    g2d.drawLine(520, 556, 765, 505);
    g2d.drawLine(765, 505, 784, 505);
    g2d.drawLine(784, 505, 784, 223);
    g2d.drawLine(784, 223, 772, 223);
    g2d.drawLine(772, 223, 772, 434);
    g2d.drawLine(772, 434, 528, 434);
    g2d.drawLine(528, 434, 528, 416);
    g2d.drawLine(528, 416, 467, 416);
    g2d.drawLine(467, 416, 467, 40);
    g2d.drawLine(467, 40, 433, 40);
    // g2d.Mode(CENTER);
    // repaint();
// g2d.rotate(Math.toRadians((spin));

}
 
public static void main(String args[]){
    Wing panel = new Wing();
    JFrame application = new JFrame();
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    application.add(panel);
    application.setSize(1000, 900);
    application.setVisible(true);
    // spinner values
    SpinnerModel value = new SpinnerNumberModel(5,0,360,1);
    JSpinner spinner = new JSpinner(value);
    spinner.setBounds(150, 150, 150, 40);
    application.add(spinner);
    application.setVisible(true);
    // spin=(int)JSpinner.getValue(); 
    // repaint();
}

// public class Graphs extends JPanel{
//     @Override
//     public void paint(Graphics g){
//         super.paint(g);
//         Graphics2D g2d = (Graphics2D)g;
//         g2d.translate(x, y);
//         g2d.rotate(Math.toRadians(45));

//     }
// }
}