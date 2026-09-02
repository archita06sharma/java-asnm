import java.util.Scanner;

class palindrome {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Give input : ");
        int num = sc.nextInt();
        int numCopy = num;
        int rev = 0;

        while(num!=0){
            int rem = num %10;
            rev = rev*10 + rem;
            num/=10;
        }

       if(numCopy == rev){
        System.out.print("It is palindrome.");
       }else{
        System.out.print("Not a palindrome");
       }
    }
}