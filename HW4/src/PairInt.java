

/*
	@author: Zimu Jiao
*/

public class PairInt{
    private int x;
    private int y;

    public PairInt(int x,int y){
        this.x=x;
        this.y=y;
    }

    //getX/Y() methods:
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    // setX/Y() methods:
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public boolean equals(Object p){
        if (p !=null){
            PairInt Pair= (PairInt) p;
            return Pair.x==this.x && Pair.y==this.y;	// When this.x/y value == Object p.x/y return true.
        }else {
            return false;
        }
    }

    public String toString(){
        return "("+ x +","+ y +")";	// Turn to String and return.
    }

    public PairInt copy(){
        PairInt result= new PairInt(x,y);
        return result;
    }
}