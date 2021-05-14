package starFighterPack;

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

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
  private Ship ship;
  private AlienHorde aliens;
  private Bullets bullets;
  private AlienBullets alienBullets;
  private Score score;
  private int difficulty;

  /* uncomment once you are ready for this part
   *
   private AlienHorde horde;
   private Bullets shots;
  */

  private boolean[] keys;
  private boolean canFire;
  private BufferedImage back;

  public OuterSpace()
  {
    score = new Score();
    reset();
    setBackground(Color.black);

    keys = new boolean[5];

    //instantiate other instance variables
    //Ship, Alien

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void reset(){
	score.setAmmo(30+5*score.getLevel());
    ship = new Ship(400,300,75,75,5);
    aliens = new AlienHorde(50, score.getLevel());
    bullets = new Bullets();
    alienBullets = new AlienBullets();
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

    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("StarFighter ", 20, 50 );
    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0,0,800,600);
    
    if(aliens.noMoreAliens()) {
    	score.setLevel(score.getLevel()+1);
    	reset();
    }
    if(score.getLives() == 0) {
    	score.setLives(3);
    	score.setLevel(1);
    	score.setScore(0);
    	reset();
    }

    if(keys[0])
      ship.move(0);
    if(keys[1])
      ship.move(1);
    if(keys[2])
      ship.move(2);
    if(keys[3])
      ship.move(3);
    if(keys[4] && !score.outOfAmmo()){
      bullets.add(new Ammo(ship.getX()+ship.getWidth()/2-5,ship.getY(),20));
      score.setAmmo(score.getAmmo()-1);
      keys[4]=false;
    }

    aliens.tryShoot(alienBullets);

    bullets.moveEmAll();
    alienBullets.moveEmAll();
    aliens.moveEmAll();
    
    ship.draw(graphToBack);
    bullets.drawEmAll(graphToBack);
    alienBullets.drawEmAll(graphToBack);
    aliens.drawEmAll(graphToBack);


    //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
    if(ship.isHit(alienBullets,aliens))
		score.setLives(score.getLives()-1);
    aliens.removeDeadOnes(bullets.getList(), score);
    score.draw(graphToBack,Color.YELLOW);




    bullets.cleanEmUp();
    alienBullets.cleanEmUp();

    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = false;
    }

    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = true;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e)
  {
    /*if (e.getKeyChar() == ' ')
    {
      bullets.add(new Ammo(ship.getX()+ship.getWidth()/2-5,ship.getY()));
    }
    repaint();*/
  }

  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(5);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }
}

