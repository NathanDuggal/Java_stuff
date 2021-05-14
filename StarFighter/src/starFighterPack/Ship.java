package starFighterPack;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
  private int speed;
  private Image image;

  public Ship()
  {
    this(10,10,10,10,10);
  }

  public Ship(int x, int y)
  {
    super(x,y);
  }

  public Ship(int x, int y, int s)
  {
    super(x,y);
    speed = s;
  }

  public Ship(int x, int y, int w, int h, int s)
  {
    super(x, y, w, h);
    speed=s;
    try
    {
      URL url = getClass().getResource("ship.jpg");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      //feel free to do something here
    }
  }


  public void setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public void move(int i)
  {
    
    if(i==0)
      setX(getX()-speed);
    else if(i==1)
      setX(getX()+speed);
    else if(i==2)
      setY(getY()-speed);
    else if(i==3)
      setY(getY()+speed);
  }
  
  public boolean isHit(Bullets b, AlienHorde a) {
	  for(int i=0; i < b.getList().size(); i++)
		  if(didCollide(b.getList().get(i))) {
			  b.getList().remove(i);
			  return true;
		  }
	  for(int i=0; i < a.getList().size(); i++)
		  if(didCollide(a.getList().get(i))) {
			  return true;
		  }
	  return false;
  }

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }

  public String toString()
  {
    return super.toString() + getSpeed();
  }
}
