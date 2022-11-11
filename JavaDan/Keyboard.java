import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

public class Keyboard extends JApplet
      implements KeyListener, FocusListener, MouseListener {
  
  //status values
   Color shipColor; 
   double Rotate;
   float Scaling;
  //viariables coordinate declaration
   float start_x, start_y, start_z;
   float[] wing_x, wing_y, wing_z; 
   float[] beggin_x, beggin_y, beggin_z; 

   boolean focussed = false; // True when this applet has input focus.
   DisplayPanel canvas; 



   public void init() {
     
      Rotate = 0;
      Scaling = 1;
      start_x = 35.25f;
      start_y = 55.25f;
      start_z = 1f;

      wing_x = new float[] {44, 44, 37, 37, 12, 12, 11, 11, 38, 38, 40, 40, 49, 49, 52, 52, 75, 78, 78, 77, 77, 52, 52, 46, 46, 44}; 
      wing_y = new float[] { 4, 41, 41, 43, 43, 22, 22, 50, 55, 60,60, 54, 54, 60, 60, 55, 50, 50, 22, 22, 43, 43, 41, 41, 4, 4 };
      wing_z = new float[] { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};//26

      beggin_x = new float[26];
      beggin_y = new float[26];
      beggin_z = new float[26];

      for (int i = 0; i < 26; i++) {
         beggin_x[i] = wing_x[i];
         beggin_y[i] = wing_y[i];
         beggin_z[i] = wing_z[i];
      }

      setSize(1000, 600);

      shipColor = Color.GRAY;

      canvas = new DisplayPanel(); // Create drawing surface and
      setContentPane(canvas); // install it as the applet's content pane.

      canvas.setBackground(Color.white); // Set the background color of the canvas.
      canvas.addFocusListener(this); // Set up the applet to listen for events
      canvas.addKeyListener(this); // from the canvas.
      canvas.addMouseListener(this);

   } 

   public void focusGained(FocusEvent e) {
    // The applet now has the input focus.
    focussed = true;
 }

 public void focusLost(FocusEvent e) {
    // The applet has now lost the input focus.
    focussed = false;
 }

   class DisplayPanel extends JPanel {
      

      public void paintComponent(Graphics g) {

         super.paintComponent(g); // Fills the panel with its

         g.setColor(shipColor);

         int[] x = new int[26];
         int[] y = new int[26];

         for (int i = 0; i < 26; i++) {
            x[i] = (int) wing_x[i];
            y[i] = (int) wing_y[i];
         }

         g.drawPolyline(x, y, 26);

         if (!focussed) {
            g.setColor(Color.magenta);
         }

      } // end paintComponent()

   } // end nested class DisplayPanel

   public void calculate() {
      
      for (int i = 0; i < 26; i++) {
         
         beggin_x[i] = beggin_x[i] - start_x;
         beggin_y[i] = beggin_y[i] - start_y;
         beggin_z[i] = 50;

         wing_x[i] = beggin_x[i] * Scaling;
         wing_y[i] = beggin_y[i] * Scaling;
         wing_z[i] = 10;
         
         float temp_x = wing_x[i];
         wing_x[i] = (float) ((Math.cos(Math.toRadians(Rotate)) * wing_x[i]) - (Math.sin(Math.toRadians(Rotate)) * wing_y[i]));
         wing_y[i] = (float) ((Math.sin(Math.toRadians(Rotate)) * temp_x) + (Math.cos(Math.toRadians(Rotate)) * wing_y[i]));
         wing_z[i] = 1;
         
         wing_x[i] = wing_x[i] + start_x;
         wing_y[i] = wing_y[i] + start_y;
         wing_z[i] = 1;
 
         beggin_x[i] = beggin_x[i] + start_x;
         beggin_y[i] = beggin_y[i] + start_y;
         beggin_z[i] = 1;
      }

   }

   public void keyTyped(KeyEvent e) {

    char key = e.getKeyChar(); // The character typed.

    if (key == 'b') { shipColor = Color.GRAY;
       canvas.repaint();
    } else if (key == 'g') {shipColor = Color.GRAY;
       canvas.repaint();
    } else if (key == 'r') {shipColor = Color.GRAY;
       canvas.repaint();
    } else if (key == 'k') {shipColor = Color.GRAY;
       canvas.repaint();
    }

 } 

   public void keyPressed(KeyEvent e) {
      
      int key = e.getKeyCode(); // keyboard code for the key that was pressed

      if (key == KeyEvent.VK_LEFT) {
         calculate();

         double radian = Math.toRadians(180 - (90 + Rotate));

         for (int i = 0; i < 26; i++) {
            wing_x[i] -= (float) 5 * (-Math.cos(radian));
            wing_y[i] -= (float) 5 * Math.sin(radian);
            wing_z[i] = 1;
            beggin_x[i] -= (float) 5 * (-Math.cos(radian));
            beggin_y[i] -= (float) 5 * Math.sin(radian);
            beggin_z[i] = 1;
         }

         start_x -= (float) 5 * (-Math.cos(radian));
         start_y -= (float) 5 * Math.sin(radian);
         start_z = 1;


         canvas.repaint();
      } else if (key == KeyEvent.VK_RIGHT) {

         calculate();

         double radian = Math.toRadians(180 - (90 + Rotate));

         for (int i = 0; i < 26; i++) {
            wing_x[i] += (float) 5 * (-Math.cos(radian));
            wing_y[i] += (float) 5 * Math.sin(radian);
            wing_z[i] = 1;
            beggin_x[i] += (float) 5 * (-Math.cos(radian));
            beggin_y[i] += (float) 5 * Math.sin(radian);
            beggin_z[i] = 1;
         }

         start_x += (float) 5 * (-Math.cos(radian));
         start_y += (float) 5 * Math.sin(radian);
         start_z = 1;

         canvas.repaint();
      } else if (key == KeyEvent.VK_UP) {

         calculate();

         double radians = Math.toRadians(Rotate);

         for (int i = 0; i < 26; i++) {
            wing_x[i] += (float) 5 * Math.cos(radians);
            wing_y[i] += (float) 5 * Math.sin(radians);
            wing_z[i] = 1;
            beggin_x[i] += (float) 5 * Math.cos(radians);
            beggin_y[i] += (float) 5 * Math.sin(radians);
            beggin_z[i] = 1;
         }

         start_x += (float) 5 * Math.cos(radians);
         start_y += (float) 5 * Math.sin(radians);
         start_z = 1;


         canvas.repaint();
      } else if (key == KeyEvent.VK_DOWN) {

         calculate();

         double radians = Math.toRadians(Rotate);

         for (int i = 0; i < 26; i++) {
            wing_x[i] -= (float) 5 * Math.cos(radians);
            wing_y[i] -= (float) 5 * Math.sin(radians);
            wing_z[i] = 1;
            beggin_x[i] -= (float) 5 * Math.cos(radians);
            beggin_y[i] -= (float) 5 * Math.sin(radians);
            beggin_z[i] = 1;
         }

         start_x -= (float) 5 * Math.cos(radians);
         start_y -= (float) 5 * Math.sin(radians);
         start_z = 1;


         canvas.repaint();
      } else if (key == KeyEvent.VK_R || key == KeyEvent.VK_F) {

         if (key == KeyEvent.VK_R) {
            if (Scaling > 4)
               return;
            Scaling += 1;
         } else {
            if (Scaling < 0.4)
               return;
            Scaling -= 1;
         }

         calculate();

         canvas.repaint();
      } else if (key == KeyEvent.VK_E || key == KeyEvent.VK_D) {
         if (key == KeyEvent.VK_D) {
            if (Rotate == 360)
               Rotate = 0;
            else
               Rotate += 20;
         } else {
            if (Rotate == 0)
               Rotate = 360;
            else
               Rotate -= 20;
         }

         calculate();

         canvas.repaint();
      }
   } 

   public void keyReleased(KeyEvent e) {
   }
   public void mouseEntered(MouseEvent e) {
  } 
  public void mouseExited(MouseEvent e) {
  } 

  public void mouseReleased(MouseEvent e) {
  } 
  public void mouseClicked(MouseEvent e) {
  }
   public void mousePressed(MouseEvent e) {
      canvas.requestFocus();
   }

} 