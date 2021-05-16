package starFighterPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class AlienHorde
{
  private List<Alien> aliens;
  private int size;
  private int alSize;
  private int alSpeed;
  private int chance;

  public AlienHorde(int s, int l)
  {
    size = 10+5*l;
    alSize = s;
    alSpeed = l;
    chance = 5000/(int)Math.pow(2, l);
    makeList();
  }

  public void makeList(){
    int x = alSize;
    int y = alSize;
    int d = 0;
    aliens = new ArrayList<Alien>();
    for(int i=0; i < size; i++){
      add(new Alien(x,y,alSize,alSize,alSpeed,d));
      x+=6*alSize/5;
      //x+=alSize;
      if(x > StarFighter.WIDTH-alSize){
        x=alSize;
        y+=alSize;
        if(d==0)
          d=1;
        else
          d=0;
      }
    }
  }

  public int getSize(){
    return size;
  }

  public int getAlSize(){
    return alSize;
  }

  public int getAlSpeed(){
    return alSpeed;
  }

  public void setSize(int i){
    size = i;
  }

  public void setAlSize(int i){
    alSize = i;
  }

  public void setAlSpeed(int i){
    alSpeed = i;
  }

  public void add(Alien al)
  {
    aliens.add(al);
  }
  
  public List<Alien> getList(){
	  return aliens;
  }

  public void drawEmAll( Graphics window )
  {
    for(Alien a : aliens)
      a.draw(window);
  }

  public boolean noMoreAliens(){
    return aliens.size() == 0;
  }

  public void tryShoot(AlienBullets alBull){
    int num = (int) (Math.random()*aliens.size());
    Alien al = aliens.get(num);
    int rand = (int) (Math.random()*chance);
    if(rand==0)
      alBull.add(new Ammo(al.getX()+al.getWidth()/2-5,al.getY(),5));
  }
  
  public boolean areAtBottom() {
	  for(Alien a : aliens)
		  if(a.isAtBottom())
			  return true;
	  return false;
  }

  public void moveEmAll()
  {
    for(Alien a : aliens){
      a.move();
      if(a.getX() < alSize || a.getX() > StarFighter.WIDTH-alSize){
        a.setY(a.getY()+alSize);
        if(a.getDir() == 0){
          a.setDir(1);
          a.setX(alSize);
        }else{
          a.setDir(0);
          a.setX(StarFighter.WIDTH-alSize);
        }
      }
    }
  }

  public void removeDeadOnes(List<Ammo> shots, Score score)
  {
    for(int i=0; i < aliens.size(); i++){
      for(int ii=0; ii < shots.size(); ii++){
        if(aliens.get(i).didCollide(shots.get(ii))){
          System.out.println("Alien "+i+" collided with bullet " +ii);
          aliens.remove(i);
          shots.remove(ii);
          score.setScore(score.getScore()+10);
          //score.setAmmo(score.getAmmo()+1);
          i--;
          break;
        }
      }
    }
  }

  public String toString()
  {
    return "";
  }
}
