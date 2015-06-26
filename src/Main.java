import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static double f(double x) {
        double y = (x-1)*(x-2)*(x-3)*(x-4); //Define Equation
        return y;
    }

    public static void main(String[] args) throws IOException {

        double threshold = 1.0E-10;
        double negativeThreshold = -1.0E-10;
        double Solution = 0;
        double a, b; //Input
        double Median;

        //System.out.println("TEXT HERE");

        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));

        try {
            a = Double.parseDouble(args[0]);
            b = Double.parseDouble(args[1]);
            /*System.out.println("a?");
            a = Double.parseDouble(br.readLine());
            System.out.println("b?");
            b = Double.parseDouble(br.readLine());*/
            if (a>b) { //if !a<b swap the two
                double inputBuffer;
                inputBuffer = a;
                a = b;
                b = inputBuffer;
            }

            if (f(a)==0) Solution = a; //Entered values are already solution!
            if (f(b)==0) Solution = b;
                if (Solution != 0) {
                    System.out.println("Answer is " + Solution);
                    System.exit(0);
                }

            if (f(a)*f(b)>0){
                System.out.println("No answer or more than 2 answers were found between the values you entered.");
                System.exit(0);
            }

            while (true) {
                Median = (a+b)/2;
                if (f(a)*f(Median) <0) {
                    //Solution found bet/ a & Median
                    b = Median;
                } else if (f(b)*f(Median) <0) {
                    //Solution found bet/ a & Median
                    a = Median;
                } else {
                    System.out.println("Something goes wrong");
                    System.exit(0);
                }

                if (negativeThreshold <= f(Median) && threshold >= f(Median)) {
                    Solution = Median;
                    break;
                }

            }

            System.out.println("Answer is " + Solution);
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid character/Too big or small number entered. Program unexpectedly terminated!");
        } finally {
            br.close();
        }
    }
}
