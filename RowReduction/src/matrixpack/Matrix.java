package matrixpack;

import java.util.*;

public class Matrix{

  private Fraction[][] matrix;

  public Matrix(){
    matrix = new Fraction[3][3];
  }

  public Matrix(int rows, int cols){
    matrix = new Fraction[rows][cols];
  }

  public void set(int r, int c, Fraction f){
    matrix[r][c] = f;
  }

  public Fraction get(int r, int c){
    return matrix[r][c];
  }

  public String getSize(){
    return matrix.length+" "+matrix[0].length;
  }

  public void interC(int r1, int r2){
    Fraction[] temp = matrix[r1];
    matrix[r1] = matrix[r2];
    matrix[r2] = temp;
  }

  public void scale(int r, Fraction f){
    for(int i=0; i < matrix[r].length; i++)
      matrix[r][i] = matrix[r][i].multiply(f);
  }

  public void add(int r1, int r2, Fraction f){
    for(int i=0; i < matrix[r1].length; i++)
      matrix[r2][i] = matrix[r2][i].add(f.multiply(matrix[r1][i]));
  }

  public void toggleDec(boolean dec){
    for(Fraction[] farr : matrix)
      for(Fraction f : farr)
        f.setDec(dec);
  }

  public void rowReduce(){
    int step = 1;
    int i = 0;
    int ii = 0;
    int nextCol = 0;
    int nextRow = 0;
    while(nextRow < matrix.length){
      System.out.println("Reduction "+step);
      ii=nextCol;
      i=nextRow;
      while(matrix[i][ii].getInt() == 0){
        i++;
        if(i == matrix.length){
          ii++;
          i=nextRow;
          if(ii == matrix[0].length)
            return;
        }
      }
      
      interC(nextRow,i);
      i=nextRow;
      
      scale(i,matrix[i][ii].inverse());
      System.out.println(this);

      for(int n=0; n < matrix.length; n++){
        if(n != i)
          add(i,n,matrix[n][ii].multiply(new Fraction(-1,1)));
      }
      nextRow++;
      nextCol++;
      step++;
    }
    
    
  }

  public String toString(){
    String ret = "";
    for(Fraction[] fArr : matrix){
      ret+="|";
      for(Fraction f: fArr){
        ret+=f.format(4,4);
      }
      ret+="|\n";
    }
    return ret;
  }

 

}

