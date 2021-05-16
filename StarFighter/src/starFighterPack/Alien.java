package starFighterPack;

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;

public class Alien extends MovingThing
{
  private int speed;
  private int direction;
  private Image image;

  public Alien()
  {
    this(0,0,30,30,0,0);
  }

  public Alien(int x, int y)
  {
    super(x,y);
  }

  public Alien(int x, int y, int s)
  {
    super(x,y);
    speed = s;
  }

  public Alien(int x, int y, int w, int h, int s, int d)
  {
    super(x, y, w,h);
    speed=s;
    direction=d;
    try
    {
      URL url = getClass().getResource("alien.jpg");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      //feel free to do something here
    }
  }

  public void setSpeed(int s)
  {
    speed =s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public void setDir(int i){
    direction = i;
  }

  public int getDir(){
    return direction;
  }
  
  public boolean isAtBottom() {
	  return getY()>=StarFighter.HEIGHT;
  }

  public void move(int i){

  }

  public void move()
  {
    if(direction==0)
      setX(getX()-speed);
    else if(direction==1)
      setX(getX()+speed);
    else if(direction==2)
      setY(getY()-speed);
    else if(direction==3)
      setY(getY()+speed);
  }

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    //window.drawString("");
  }

  public String toString()
  {
    return "";
  }
}
