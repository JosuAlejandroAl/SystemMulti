import java.io.*;
import java.util.*;
import java.util.Scanner;
 
class main {
 
  public static void main(String[] args) 
  {
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    int n = sc.nextInt();
    printHexagon(n);

    for (int j = 0; j < num - 1; j++) {
      System.out.print(" ");
    }
    for (int j = 0; j < num; j++) {
      System.out.print("+");
    }
    System.out.println();
    int l = num - 2;
    int m = num;
    for (int k = 0; k < num - 1; k++) {
      System.out.println(getRow(l, m));
      l -= 1;
      m += 2;
    }
    for (int j = 0; j < num - 2; j++) {
      System.out.println(getRow(l, m - 2));
    }
    for (int j = 0; j < num - 1; j++) {
      m -= 2;
      l += 1;
      System.out.println(getRow(l, m));
    }
    for (int j = 0; j < num - 1; j++) {
      System.out.print(" ");
    }
    for (int j = 0; j < num; j++) {
      System.out.print("+");
    }
    System.out.println();

    Scanner scanner = new Scanner(System.in);

      print("Enter the height of the rectangle : ");
      int height = scanner.nextInt();

      print("Enter the width of the rectangle : ");
      int width = scanner.nextInt();

      String c = "+";

      printRectangle(height, width, c);
 
      System.out.println("Enter the number of rows: ");
   
      int rows = sc.nextInt();    
      for (int i=1; i<= rows ; i++) { for (int j = rows; j > i ; j--) {
              System.out.print(" ");
          }
          System.out.print("+");
          for (int k = 1; k < 2*(i -1) ;k++) { System.out.print(" "); } if( i==1) { System.out.println(""); } else { System.out.println("*"); } } for (int i=rows-1; i>= 1 ; i--)
          {
          for (int j = rows; j > i ; j--) {
              System.out.print(" ");
          }
          System.out.print("+");
          for (int k = 1; k < 2*(i -1) ;k++) {
              System.out.print(" ");
          }
          if( i==1)
              System.out.println("");
          else
              System.out.println("+");
      }
      sc.close();
    
  }

  // Hexagonal function ---------------------------o-------------------------------


  static void printHexagon(int n)
    {
        int l = 2 * n - 1;
     
        /**** Print upper part *****/
        for (int i = 0; i < n; i++)
        {
            int elem = i + n;
     
            // loop for printing the
            // whitespaces and the star
            for (int k = 0; k < elem; k++)
            {
                // prints the star
                if ((k == n + i - 1) ||
                    (k == n - i - 1))
                    System.out.print("+");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
     
        /**** Print middle part *****/
        // Here we print two vertical lines
        for (int m = 0; m < n - 2; m++)
        {
            for (int j = 0; j < l; j++)
            {
                if (j == 0 || j == l - 1)
                    System.out.print("+");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
     
        /**** Print Lower part *****/
        int r = n - 1;
        for (int h = r; h >= 0; h--)
        {
            int elem = h + n;
            for (int k = 0; k < elem; k++)
            {
                if ((k == n + h - 1) ||
                    (k == n - h - 1))
                    System.out.print("+");
                else
                    System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static void println(String str) {
      System.out.println(str);
  }

  private static void print(String str) {
      System.out.print(str);
  }

  // rectangule function ---------------------------o-------------------------------

  private static void printRectangle(int height, int width, String c) {
      for (int i = 0; i < height; i++) {
          if (i == 0 || i == height - 1) {
              //for first line and last line , print the full line
              for (int j = 0; j < width; j++) {
                  print(c);
              }
              println(""); //enter a new line
          } else {
              //else
              for (int j = 0; j < width; j++) {
                  if (j == 0 || j == width - 1) {
                      //print only the first and last element as the character
                      print(c);
                  } else {
                      //else print only blank space for the inner elements
                      print(" ");
                  }
              }
              println(""); //enter a new line
          }
      }
  }

  // daimond ---------------------o-------------------


  
  public static String getRow(int off, int dist) {
    String res = "";
    for (int j = 0; j < off; j++) {
      res += " ";
    }
    res += "+";
    for (int j = 0; j < dist; j++) {
      res += " ";
    }
    res += "+";
    return res;
  }
}