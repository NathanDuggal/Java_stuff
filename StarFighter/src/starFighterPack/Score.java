package starFighterPack;

import java.util.*;
import java.awt.*;

public class Score{

  private int xPos;
  private int yPos;
  private int score;
  private int lives;
  private int ammo;
  private int level;
  public static final Font FONT = new Font("Arial", 1, 20);

  public Score(){
    this(20,20,0,3,0,1);
  }

  public Score(int x, int y, int s, int li, int a, int le){
    xPos = x;
    yPos = y;
    score = s;
    lives = li;
    ammo = a;
    level = le;
  }

  public void setScore(int i){
    score=i;
  }

  public void setLives(int i){
    lives=i;
  }

  public void setAmmo(int i){
    ammo=i;
  }

  public void setLevel(int i){
    level=i;
  }

  public boolean outOfAmmo(){
    return ammo == 0; 
  }

  public int getScore(){
    return score;
  }

  public int getLives(){
    return lives;
  }

  public int getAmmo(){
    return ammo;
  }

  public int getLevel(){
    return level;
  }

  public void draw(Graphics window, Color c){
    window.setFont(FONT); 
    window.setColor(c);
    window.drawString("Lives: "+lives+" Ammo: "+ammo+" Score: "+score+" Level: "+level, xPos, yPos);
  }
}