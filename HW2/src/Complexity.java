//CS570
//Homework-Assignment-2-Complexity

//@author: Zimu Jiao
//SWID:10458119

// Notice: All the counter++ I put it before the output statement, so the output result will be total add 1

class Complexity{
    //The main
    public static void main(String[] args) {

        //method0(3);
        method1(3);     //O(n)=n^2
        method2(3);     //O(n)=n^3
        method3(64);    //O(n)=log n
        method4(4);     //O(n)=n*log n
        method5(256);   //O(n)=log log n
        method6(2);     //O(n)=2^n
    }

    //Example-method0: O(n)
    public static void method0(int n){
        int counter=0;

        for(int i=0;i<n;i++){
            System.out.println("Operation "+counter);counter++;
        }
    }
    //method1: O(n^2)
    public static void method1(int n){
        int counter=0;
        System.out.println("Method1 result of n="+n+":");

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                counter++;System.out.println("Operation "+counter);
            }
        }
    }
    //method2: O(n^3)
    public static void method2(int n){
        int counter=0;
        System.out.println("Method2 result of n="+n+":");

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    counter++;System.out.println("Operation "+counter);
                }
            }
        }
    }
    //method3: O(log n)---Binary search
    public static void method3(int n){
        int counter=0;
        System.out.println("Method3 result of n="+n+":");

        for(int i=1;i<n;i*=2){
            counter++;System.out.println("Operation "+counter);
        }
    }
    //method4: O(n*log n)---Comparison sort
    public static void method4(int n){
        int counter=0;
        System.out.println("Method4 result of n="+n+":");
        /* Fixed: the bound of n and log(n).
        origin:
            for(int i=n;i>1;i=i/2){
                for(int j=i;j<n;j++){
                    count
                }
            }
        */
        for(int i=0;i<n;i++){
            for(int j=n;j>1;j/=2){
                counter++;
                System.out.println("Operation "+counter);
            }
        }
    }
    //method5: O(loglog n)
    public static void method5(int n){
        int counter=0;
        System.out.println("Method5 result of n="+n+":");
        /*
        Fixed the bound:
        origin:
            for(int i=2;i<n;){
                i*=2;n/=2;
            }
        */
        for(int i=2;i<n;i=i*i){
            counter++;
            System.out.println("Operation "+counter);
            //resize the upper-bound and lower-bound
        }
    }
    //Optional--
    //method6: O(2^n)
    public static void method6(int n){
        //the counter of this function/method is a global variable: int counter_O2n
        System.out.println("Method6 result of n="+n+":");
        System.out.println("Operation time: "+O2n(n));
    }

    public static int O2n(int n){
        // "it is a fibonacci sequence, which complexity is (1.6)^n"
        // Fixed: Now, it's complexity is 2^n, since the return value changed.
        if (n == 0) {
            // Fixed: The ending condition is changed to n==0 from n==1, which will miss a counting loop.
            System.out.println("Operation ++");
            return 1;
        }else{
            // Fixed: Change the return value from O2n(n-1)+O2n(n-2) to followed:
            return O2n(n-1)+O2n(n-1);
        }
    }
}