package starFighterPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienBullets extends Bullets{
  @Override
  public void drawEmAll( Graphics window )
  {
    for(Ammo a : getList())
      a.draw(window, Color.RED);
  }

  @Override
  public void moveEmAll()
  {
    for(Ammo a : getList())
      a.move(3);
  }

  @Override
  public void cleanEmUp()
  {
    for(int i=0; i < getList().size(); i++)
      if(getList().get(i).getY() >= StarFighter.HEIGHT){
        getList().remove(i);
        i--;
      }
  }
}