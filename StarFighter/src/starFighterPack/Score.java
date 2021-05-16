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
  private int isPaused;
  public static final Font FONT = new Font("Arial", 1, 20);

  public Score(){
    this(20,20,0,3,0,1,1);
  }

  public Score(int x, int y, int s, int li, int a, int le, int p){
    xPos = x;
    yPos = y;
    score = s;
    lives = li;
    ammo = a;
    level = le;
    isPaused = p;
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
  
  public void togglePause() {
	  isPaused*=-1;
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
  
  public int isPaused() {
	  return isPaused;
  }

  public void draw(Graphics window, Color c){
    window.setFont(FONT); 
    window.setColor(c);
    window.drawString("Lives: "+lives+" Level: "+level+" Score: "+score+" Ammo: "+ammo, xPos, yPos);
    if(isPaused==-1) {
    	drawPaused(window, Color.RED);
    	if(level==1) {
        	drawGameOver(window, Color.RED);
        }
    }
  }
  
  public void drawPaused(Graphics window, Color c){
	  window.setColor(c);
	  window.drawString("Press C to continue", StarFighter.WIDTH/2-120, StarFighter.HEIGHT/2);
  }
  
  public void drawGameOver(Graphics window, Color c) {
	  window.drawString("GAME OVER", StarFighter.WIDTH/2-75, StarFighter.HEIGHT/2-100);
	  drawPaused(window, c);
  }
}