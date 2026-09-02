import java.util.Scanner;

class gcd {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two numbers : ");

        int a = sc.nextInt();
        int b = sc.nextInt();

        int acopy=a;
        int bcopy=b;
        
        while(b!=0){
            int temp = b;
            b= a%b;
            a = temp;
        }

        System.out.print("The gcd of "+ acopy + " and "+ bcopy + " is : " + Math.abs(a) );

    }
}