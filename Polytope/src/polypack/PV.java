package polypack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PV extends Canvas implements Runnable
{
  private Vector vector1;
  private Vector vector2;
  private Vector vector3;
  
  private ArrayList<Vector> vectors;

  private ArrayList<Vector> points;

  private BufferedImage back;
  
  public PV(ArrayList<Vector> v)
  {
    vectors = v;
    
    points = new ArrayList<Vector>();
    setBackground(Color.WHITE);

    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window)
  {
    paint(window);
  }

  public void paint( Graphics window )
  {
	
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D)window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if(back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();

    //graphToBack.setColor(Color.BLUE);
    
    graphToBack.setColor(Color.WHITE);
    graphToBack.fillRect(0,0,800,600);
    
    ArrayList<Double> weights = new ArrayList<Double>();
    double tot = 2;
    
    while(tot > 1) {
	    weights = new ArrayList<Double>();
    	tot=0;
    	
    	double total = 1.0;
	    for(int i=0; i < vectors.size()-1; i++) {
	    	double d = total*Math.random();
	    	total -= d;
	    	weights.add(d);
	    	/*double d = Math.random();
	    	d= 1-d
	    	weights.add();
	    	tot+=d;
	    	*/
	    }
	    weights.add(total);
    }

    Vector sum = new Vector(0,0);
    for(int i=0; i < vectors.size(); i++) {
    	Vector v = vectors.get(i);
    	sum.add(v.scale(weights.get(i)));
    }

    points.add(sum);

    graphToBack.setColor(Color.BLACK);
    for(int i=0; i < points.size(); i++){
      Vector v = points.get(i);
      graphToBack.fillRect(v.getX(),v.getY(),2,2);
    }
    
    graphToBack.setColor(Color.RED);
    for(Vector v : vectors) {
    	graphToBack.fillRect(v.getX(),v.getY(),5,5);
    }
    
    vectors.add(0,vectors.remove(vectors.size()-1));

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(1);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }
}

