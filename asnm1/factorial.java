import java.util.Scanner;

class factorial{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number whose factrial is needed : ");

        int num = sc.nextInt();
        long ans  = 1;

        for(int i=1;i<=num;i++){
            ans *= i;
        }

        System.out.println("factorial : "+ ans);
    }
}