package polypack;

public class Vector{

	  private int x;
	  private int y;

	  public Vector(int x, int y){
	    this.x = x;
	    this.y = y;
	  }

	  public int getX(){
	    return x;
	  }

	  public int getY(){
	    return y;
	  }

	  public Vector scale(double c){
	    return new Vector((int)(x*c), (int)(y*c));
	  }

	  public void add(Vector v){
	    x+=v.x;
	    y+=v.y;
	  }
}