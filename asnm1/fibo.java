import java.util.Scanner;

class fibo {
    public static void main(String[] args){
        int a=0,b=1;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of terms of fibonacci series required : ");
        int num = sc.nextInt();

        System.out.println(a);
        System.out.println(b);
        

        for(int i=0;i<num-2;i++){
            int c = a+b;
            System.out.println(c);
            a=b;
            b=c;
        }
    }
}