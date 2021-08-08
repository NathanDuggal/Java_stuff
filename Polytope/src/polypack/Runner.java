package polypack;

import javax.swing.JFrame;
import java.awt.Component;
import java.util.*;

public class Runner extends JFrame
{
  public static final int WIDTH = 500;
  public static final int HEIGHT = 500;

  public Runner()
  {
	  
    super("Polytope Visualizer");
    setSize(WIDTH,HEIGHT);
	Scanner s = new Scanner(System.in);
	int num = 0;
	ArrayList<Vector> vectors = new ArrayList<Vector>();
	System.out.println("Enter number of vectors:");
	num = s.nextInt();
	
	for(int i=0; i < num; i++) {
		System.out.println("Enter x for vector "+(i+1));
		int x = s.nextInt();
		System.out.println("Enter y for vector "+(i+1));
		int y = s.nextInt();
		vectors.add(new Vector(x,y));
	}
	
    PV pv = new PV(vectors);
    ((Component)pv).setFocusable(true);

    getContentPane().add(pv);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main( String args[] )
  {
    Runner run = new Runner();
  }
}