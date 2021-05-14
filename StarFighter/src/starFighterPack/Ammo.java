package starFighterPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
  private int speed;

  public Ammo()
  {
    this(0,0,2);
  }

  public Ammo(int x, int y)
  {
    this(x,y,10);
  }

  public Ammo(int x, int y, int s)
  {
    super(x,y);
    speed = s;
  }

  public void setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public void draw( Graphics window ){
    
  }

  public void draw( Graphics window , Color c)
  {
    window.setColor(c);
    window.fillRect(getX(), getY(), getWidth(), getHeight());
  }
        
        
  public void move( int i )
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


  public String toString()
  {
    return "";
  }
}
