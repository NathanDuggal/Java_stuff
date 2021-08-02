package matrixpack;

public class Fraction{
	  private int num;
	  private int den;
	  private boolean dec;

	  public Fraction(){
	    num = 1;
	    den = 1;
	    dec = false;
	  }

	  public Fraction(int n){
	    num = n;
	    den = 1;
	    dec = false;
	  }

	  public Fraction(int n, int d){
	    num = n;
	    den = d;
	    dec = false;
	    reduce();
	  }

	  public Fraction(double d){
	    int ten = 1;
	    while(d != (int) d){
	      d*=10;
	      ten*=10;
	    }
	    num = (int) d;
	    den = ten;
	    reduce();
	  }

	  public static int gcf(int a, int b){
	    if( a % b == 0)
	      return b;
	    else
	      return gcf( b,  a % b);
	  }

	  public void reduce(){
	    int f = gcf(num,den);
	    num/=f;
	    den/=f;
	    if(den < 0){
	      num*=-1;
	      den*=-1;
	    }
	  }

	  public Fraction multiply(int n){
	    int Nnum = num*n;
	    Fraction g = new Fraction(Nnum,den);
	    g.reduce();
	    return g;
	  }

	  public Fraction divide(int n){
	    int Nden = den*n;
	    Fraction g = new Fraction(num,Nden);
	    g.reduce();
	    return g;
	  }

	  public Fraction add(Fraction f){
	    int Nnum = num*f.den;
	    Nnum+=f.num*den;
	    int Nden=den*f.den;

	    Fraction g = new Fraction(Nnum,Nden);
	    g.reduce();
	 
	    //System.out.println(format(false,2,2));
	    return g;
	  }

	  public Fraction multiply(Fraction f){
	    int Nnum=num*f.num;
	    int Nden=den*f.den;
	    Fraction g = new Fraction(Nnum,Nden);
	    g.reduce();
	    return g;
	  }

	  public int getInt(){
	    return num/den;
	  }

	  public static Fraction clone(Fraction f){
	    return new Fraction(f.num, f.den);
	  }

	  public Fraction inverse(){
	    return new Fraction(den, num);
	  }

	  public void setDec(boolean d){
	    dec = d;
	  }

	  public String format(int digits, int spaces){
	    
	    String ret = "";
	    double d = 0;
	    int dInt = 0;

	    boolean isInt = false;
	    if(den==1)
	      isInt = true;
	    
	    if(isInt){
	      ret+=num;
	    }else if(dec){
	      d = (double) num/den;
	      dInt = (int) (d*Math.pow(10,digits));
	      d = (double) dInt/Math.pow(10,digits);
	      ret+=d;
	    }else{
	      ret+=num+"/"+den;
	    }

	    int sw = 1;
	    while(ret.length() <= spaces*2+1){
	      if(sw == 1){
	        ret+=" ";
	      }else{
	        ret=" "+ret;
	      }
	      sw*=-1;
	    }

	    return ret;
	  }
	}
