import java.util.Scanner;

class reverse {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        System.out.print("Enter the power : ");
        int power= sc.nextInt();

        double ans=1;

        for(int i=0;i<power;i++){
            ans*=num; 
        }

        System.out.print("Answer is : "+ans);
    
    }
}

// import java.util.Scanner;

// class Power {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         System.out.print("Enter base: ");
//         double base = sc.nextDouble();

//         System.out.print("Enter exponent: ");
//         int exponent = sc.nextInt();

//         double result = Math.pow(base, exponent);

//         System.out.println("Result = " + result);
//     }
// }