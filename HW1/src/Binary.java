//CS570
//Homework-Assignment-1

//@author: Zimu Jiao
//SWID:10458119

//Basic part----------
//--------------------
import java.util.Arrays;
class BinaryNumber {
    private int[] data;
    private boolean overflow=false;

    //constructor-with int length
    public BinaryNumber(int length){
        if(length<=0){
            length=4;
        }
        // Fixed: check the input length, if it's not positive, reset length as 4.
        data = new int[length];
    }

    //constructor-with String str
    public BinaryNumber(String str){
        data=new int[str.length()];

        // Fixed: check the str, whether empty or not.
        if(str==null){
            str="0000";
        }
        // Fixed: if the string is not a Binary Number String, just take the 1 and 0 and ignore other number:
        // Example: Str"31020" will turn to be Binary Number:01000. The non-1 and non-0 will set as 0.
        for(int i=0;i<str.length();i++){
            int temp=Character.getNumericValue(str.charAt(i));
            if(temp!=1 && temp!=0){
                temp=0;
            }
            data[i]=temp;
        }
    }

    //function to get the length of data, and return it (int)
    public int getLength(){
        return data.length;
    }

    //function to get a digit value from data, and return it (int)
    public int getDigit(int index){
        // Fixed: check the index value negative or not:
        if(index<0){
            System.out.println("\tThe input is negative, please input a non-negative index.");
            return 0;
        }

        if(index >= data.length){
            System.out.println("\tThe input is out of range\n\tPlease input 0-"+this.data.length);
            return 0;
        }else{
            return data[index];
        }
    }

    //functino to turn data(BinaryNumebr) into Decimal Number,and return it (int)
    public int toDecimal(){
        int Decimal=0;
        for(int i=0;i<data.length;i++){
            Decimal=Decimal+data[i]*(int)Math.pow(2,data.length-1-i);
        }
        return Decimal;
    }

    //function to Shift data right *amount digit
    public void shiftRight(int amount){
        String s="";
        for(int i=0;i<amount;i++){
            s=s.concat("0");
        }
        for(int i=0;i<data.length;i++){
            s=s.concat(Integer.toString(data[i]));
        }
        data=new int[s.length()];
        for(int i=0;i<s.length();i++){
            data[i]=Character.getNumericValue(s.charAt(i));
        }

        printBN();
    }

    //Additional part--------------
//-----------------------------
    public void add(BinaryNumber b){
        // Fixed: The old version misunderstanding the requirement, now it's fixed:
        if(this.getLength()!= b.getLength()){
            System.out.println("The Binary Numbers are not coincide.\nPlease choose two equal-length Numbers.");
        }else{
            int C_digit=0; //The carried digit
            for(int i=getLength()-1;i>=0;i--){
                data[i]=this.getDigit(i)+b.getDigit(i)+C_digit;
                if(data[i]>1){
                    data[i]=data[i]%2;
                    C_digit=1;
                }else{
                    C_digit=0;
                }
            }

            // While Carried_Digits =1 means length is not fit, create a new BinaryNumber to save it.
            if(C_digit==1) {
                overflow = true;
            }
        }
    }

    //function to clear the flag of Overflow
    public void clearOverflow(){
        this.overflow=false;
    }

    //function to transfer the value of Binary Number to a String
    public String toString(){
        if(this.overflow){
            System.out.println("Overflow!");
        }

        String str="";
        for(int i=0;i<data.length;i++){
            str=str.concat(Integer.toString(data[i]));
        }
        data=new int[str.length()];
        for(int i=0;i<str.length();i++){
            data[i]=Character.getNumericValue(str.charAt(i));
        }
        return str;
    }



    // function to print the BinaryNumber, used in other function to check result
// --------------------------------------------------------------------------
    public void printBN(){
        // Fixed: when overflow is true, the output will changed:
        if(this.overflow){
            System.out.println("The binary number is Overflow,it's value is: "+this);
        }
        System.out.println("The binary number is: "+this);
    }
    //---------------------------------------------------------------------------
//Using below part to check those function above-------
//-----------------------------------------------------
    public static void main(String[] args) {
        BinaryNumber test= new BinaryNumber(0);
        BinaryNumber BN1 = new BinaryNumber("1101");
        BinaryNumber BN2 = new BinaryNumber("1010");

        test.printBN();
        BN1.printBN();
        BN2.printBN();

        System.out.println("\noutput the info of BN1");
        BN1.printBN();
        System.out.println("The length of BN1 is: "+BN1.getLength());
        System.out.println("The no.-1 of BN1 is: "+BN1.getDigit(-1));
        System.out.println("The Decimal of BN1 is: "+BN1.toDecimal());
        System.out.println("The 2 Right-Shift-Result of BN1 is:");
        BN1.shiftRight(2);


        System.out.println("\noutput the info of BN2");
        BN2.printBN();
        System.out.println("The length of BN2 is: "+BN2.getLength());
        System.out.println("The no.4 of BN2 is: "+BN2.getDigit(4));
        System.out.println("The Decimal of BN2 is: "+BN2.toDecimal());
        System.out.println("The 1 Right-Shift-Result of BN2 is:");
        BN2.shiftRight(1);

        // Do add-operation and toString function

        BinaryNumber Bn3=new BinaryNumber("1000");
        BinaryNumber Bn4=new BinaryNumber("1111");

        Bn3.printBN();
        Bn4.printBN();
        System.out.println("\noutput the result of Bn3 and Bn4:");

        Bn3.add(Bn4);
        Bn3.printBN();
        Bn4.printBN();

        String str1=BN1.toString();
        System.out.println(str1);
    }
}
