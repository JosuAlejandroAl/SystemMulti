import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Point3D {
   public float x, y, z;
   public Point3D( float X, float Y, float Z ) {
      x = X;  y = Y;  z = Z;
   }
}

class Edge {
   public int a, b;
   public Edge( int A, int B ) {
      a = A;  b = B;
   }
}

public class WireframeJApplet extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      
   int width, height;
   // int mx, my;  // the most recently recorded mouse coordinates

   int azimuth = 0, elevation = 0, mandarina_slizes, parale;
   float radio;

   Point3D[] vertices;
   Edge[] edges;
   

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;  

   public void init(float radioEsfera,int meridiano,int plano) {

      radio = radioEsfera;
      mandarina_slizes = 2*meridiano;
      parale = plano;
      edges = new Edge[2*mandarina_slizes*parale];
      int faces = 0;

      vertices = new Point3D[(mandarina_slizes+1)*(parale+1)];
      int cont = 0;

      for(int i = 0; i < parale+1; i++)
      {
         float angles = map(i,0, parale, 0, Math.PI);
         for(int j = 0; j < mandarina_slizes+1; j++) 
         { // devide the mandarine in half
            float Flip_angles = map(j,0,mandarina_slizes,0, 2*Math.PI);
            float x = (float) (radio * Math.sin(angles) * Math.cos(Flip_angles));
            float y = (float) (radio * Math.sin(angles) * Math.sin(Flip_angles));
            float z = (float) (radio * Math.cos(angles));
               
            vertices[cont] = new Point3D(x, y, z);
            cont++;
         }
      }
      cont = 0;

      for(int i = 0; i < parale; i++)
      {
         for(int j = 0; j < mandarina_slizes; j++)
         {
            edges[cont] = new Edge(faces, faces+mandarina_slizes+1); cont++;
            edges[cont] = new Edge(faces, faces+1); cont++;
            faces++;
         }
         faces++;
      }

      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
   
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();

   public float map(int Keyset, int verx, int very, double facey, double facex) {
      float slices = (float) (Keyset-verx) / (very-verx);
      return (float) (facey + (facex - facey) * (slices));
   }  
   
   class DisplayPanel extends JPanel {
      public void paintComponent(Graphics g) {
         super.paintComponent(g);  

         if (focussed) 
            g.setColor(Color.cyan);
         else
            g.setColor(Color.lightGray);

         int width = getSize().width;  // Width of the applet.
         int height = getSize().height; // Height of the applet.
         g.drawRect(0,0,width-1,height-1);
         g.drawRect(1,1,width-3,height-3);
         g.drawRect(2,2,width-5,height-5);

         if (!focussed) {
            g.setColor(Color.magenta);
            g.drawString("Click to activate",100,120);
            g.drawString("Use arrow keys to change azimuth and elevation",100,150);

         }
         else { 

            double theta = Math.PI * azimuth / 180.0;
            double phi = Math.PI * elevation / 180.0;
            float cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
            float cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
            float cosTcosP = cosT*cosP, cosTsinP = cosT*sinP,
                  sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

            // project vertices onto the 2D viewport
            Point[] points;
            points = new Point[ vertices.length ];
            int j;
            int scaleFactor = width/8;
            float near = 3;  // distance from eye to near plane
            float nearToObj = 1.5f*radio;  // distance from near plane to center of object
            for ( j = 0; j < vertices.length; ++j ) {
               float x0 = vertices[j].x;
               float y0 = vertices[j].y;
               float z0 = vertices[j].z;

               // compute an orthographic projection
               float x1 = cosT*x0 + sinT*z0;
               float y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;
               float z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;

               // now adjust things to get a perspective projection
               x1 = x1*near/(z1+near+nearToObj);
               y1 = y1*near/(z1+near+nearToObj);

               // the 0.5 is to round off when converting to int
               points[j] = new Point(
                  (int)(width/2 + scaleFactor*x1 + 0.5),
                  (int)(height/2 - scaleFactor*y1 + 0.5)
               );
            }

            // draw the wireframe
            g.setColor( Color.black );
            g.fillRect( 0, 0, width, height );
            g.setColor( Color.white );
            for ( j = 0; j < edges.length; j++ ) {
               g.drawLine(
                  points[ edges[j].a ].x, points[ edges[j].a ].y,
                  points[ edges[j].b ].x, points[ edges[j].b ].y
               );
            }
         }
      }  // end paintComponent()    
    } // end nested class DisplayPanel 

   // ------------------- Event handling methods ----------------------
   
   public void focusGained(FocusEvent evt) {
      focussed = true;
      canvas.repaint();
   }
   
   public void focusLost(FocusEvent evt) {
      focussed = false;
      canvas.repaint(); 
   }
      
   public void keyTyped(KeyEvent evt) {  
   }  // end keyTyped()
      
   public void keyPressed(KeyEvent evt) { 
       
      int key = evt.getKeyCode();  // keyboard code for the key that was pressed
      
      if (key == KeyEvent.VK_LEFT) {
         azimuth += 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_RIGHT) {
         azimuth -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_UP) {
         elevation -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_DOWN) {
         elevation += 5;         
         canvas.repaint();
      }

   }  // end keyPressed()

   public void keyReleased(KeyEvent evt) { 
      // empty method, required by the KeyListener Interface
   }
   
   public void mousePressed(MouseEvent evt) {      
      canvas.requestFocus();
   }      
   
   public void mouseEntered(MouseEvent evt) { }  // Required by the
   public void mouseExited(MouseEvent evt) { }   //    MouseListener
   public void mouseReleased(MouseEvent evt) { } //       interface.
   public void mouseClicked(MouseEvent evt) { }
   public void mouseDragged( MouseEvent e ) { }
} // end class 
