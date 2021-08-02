package matrixpack;

import java.util.*;

public class MatrixDB{

  private ArrayList<Matrix> matrixDB;
  private int currMatrix;

  public MatrixDB(){
    matrixDB = new ArrayList<Matrix>();
    currMatrix = -1;
  }

  public boolean inBounds(int i){
    return i >= 0 && i < matrixDB.size();
  }

  public void add(Matrix m){
    matrixDB.add(m);
    currMatrix = matrixDB.size()-1;
  }

  public Matrix get(){
    if(inBounds(currMatrix))
      return matrixDB.get(currMatrix);
    else
      return null;
  }

  public Matrix get(int i){
    if(!inBounds(i))
      return null;
    return matrixDB.get(i);
  }


  public void remove(){
    if(inBounds(currMatrix))
      matrixDB.remove(currMatrix);
  }

  public void remove(int i){
    matrixDB.remove(i);
  }

  public int getCurrent(){
    return currMatrix;
  }

  public void setCurrent(int i){
    currMatrix = i;
  }

  public void toggleDec(int i){
    boolean dec = false;
    if(i > 0)
      dec = true;
    for(Matrix m : matrixDB){
      m.toggleDec(dec);
    }
  }

  public int size(){
    return matrixDB.size();
  }

  public String toString(){
    String ret = "";
    for(Matrix m : matrixDB){
      ret+=m.toString()+"\n";
    }
    return ret;
  }
}
