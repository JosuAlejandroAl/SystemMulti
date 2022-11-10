import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class test extends JPanel{
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int red = 255;
        int green = 255;
        int blue = 0;
        for (int i= 10; i < 512 ; i+=2){
            for (int j = 10; j < 512; j+=2){
                g.setColor(new Color(red,green,blue));
                g.fillRect(10,i,2,2);
                red-=1;
                blue+=1;
            }
            green-=1;
        }
    }
    public static void main(String args[]){
        test panel = new test();
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(panel);
        application.setSize(800, 800);
        application.setVisible(true);
    }
}