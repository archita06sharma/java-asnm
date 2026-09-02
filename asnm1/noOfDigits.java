import java.util.Scanner;

class noOfDigits {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number :");
        int num = sc.nextInt();
        int numcopy = num;

        int count=0;

        if(num == 0) {
            count = 1;
        }

        while(num!=0){
            count++;
            num/=10;
        }

        System.out.print("Number of digits of "+numcopy+" are "+count);
    }
}