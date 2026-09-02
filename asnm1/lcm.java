import java.util.Scanner;

class lcm {
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

        long lcd= (acopy/Math.abs(a)) * bcopy ;

        System.out.print("The lcm is : " + lcd);

    }
}