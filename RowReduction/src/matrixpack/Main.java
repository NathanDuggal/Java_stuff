package matrixpack;

import java.util.*;
import java.io.*;

class Main {

	  public static int select(){
	    boolean error = true;
	    int ret = 0;
	    Scanner s = new Scanner(System.in);
	    while(error){
	      System.out.println("Enter an index:");
	      if(s.hasNextInt()){
	        ret = s.nextInt();
	      }else{
	        s.next();
	        continue;
	      }
	      error = false;
	    }
	    return ret;
	  }

	  public static void mainInterface() throws IOException{
	    Scanner sFile = new Scanner(new File("in.txt"));
	    
	    MatrixDB mdb = new MatrixDB();
	    int dec = -1;

	    while(sFile.hasNextLine()){
	      int r = sFile.nextInt();
	      int c = sFile.nextInt();
	      mdb.add(new Matrix(r,c));
	      for(int i = 0; i < r; i++){
	        for(int ii = 0; ii < c; ii++){
	          mdb.get().set(i,ii,getFraction(sFile.next()));
	        }
	      }
	    }
	    System.out.println(mdb.size());
	    System.out.println(mdb);

	    Scanner sIn = new Scanner(System.in);
	    String in = "";

	    while(!in.equals("q")){
	      System.out.println("Press q to exit:");
	      in = sIn.nextLine();

	      System.out.println();

	      if(in.equals("print"))
	        System.out.println(mdb.get());
	      else if(in.equals("print all"))
	        System.out.println(mdb);
	      else if(in.equals("select")){
	        mdb.setCurrent(select());
	      }else if(in.equals("rref")){
	        if(!mdb.get().equals(null)){
	          mdb.get().rowReduce();
	        }else{
	          mdb.setCurrent(select());
	          mdb.get().rowReduce();
	        }
	        System.out.println("RREF:");
	        System.out.println(mdb.get());
	      }else if(in.equals("decimal")){
	        System.out.println("Toggling decimal");
	        dec*=-1;
	        mdb.toggleDec(dec);
	      }else if(in.equals("unselect")){
	        mdb.setCurrent(-1);
	      }else{
	        System.out.println("Invalid");
	      }
	    }
	  }

	  public static Fraction getFraction(String s){
	    if(s.indexOf("/") > -1){
	      String s1 = s.substring(0,s.indexOf("/"));
	      String s2 = s.substring(s.indexOf("/")+1);
	      return new Fraction(Integer.parseInt(s1), Integer.parseInt(s2));
	    }else{
	      return new Fraction(Integer.parseInt(s));
	    }
	  }

	  public static void fractions(){
	    
	  }

	  public static void main(String[] args) throws IOException{
	    mainInterface();
	    //fractions();
	  }
	}
