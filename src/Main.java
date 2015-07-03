import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static double f(double x) {
        double y = (x-1)*(x-2)*(x-3)*(x-4); //ここで方程式を定義
        return y;
    }

    public static double bisection(double a, double b){

        double threshold = 1.0E-10;
        double negativeThreshold = -1.0E-10; //ブレの許容値を定義
        double Solution = 0;
        double Median; //中間値

        if (a>b) { //if !a<b swap the two
            double inputBuffer;
            inputBuffer = a;
            a = b;
            b = inputBuffer;
        }

        if (f(a)==0) Solution = a; //Entered values are already solution!
        if (f(b)==0) Solution = b;
        if (Solution != 0) {
            return Solution;
        }

        if (f(a)*f(b)>0){ //we cant go here cuz pre-check is already enforced
            System.out.println("No answer or more than 2 answers were found between the values you entered.");
            System.exit(0);
        }

        while (true) { //Main Loop
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
        return Solution;
    }

    public static void main(String[] args) throws IOException {

        double Solution = 0;
        double a, b; //Input
        final double STEP = 1.0; //Difference
        double rangeFrom, rangeTo;


        try {
            rangeFrom = Double.parseDouble(args[0]);
            rangeTo = Double.parseDouble(args[1]);

            if (rangeFrom>rangeTo) { //if !a<b swap the two
                double inputBuffer;
                inputBuffer = rangeFrom;
                rangeFrom = rangeTo;
                rangeTo = inputBuffer;
            }

            a = rangeFrom;
            b = rangeFrom + STEP;

            while (true) {

                if (f(a) * f(b) < 0) {
                    Solution = bisection(a, b);
                    System.out.println("Answer is " + Solution);
                }

                a = a + STEP;
                b = b + STEP;

                if (b >= rangeTo){
                    break;
                }
            }

            System.out.println("End");
        } catch (NumberFormatException e) {
            System.out.println("Invalid character/Too big or small number entered. Program unexpectedly terminated!");
        } finally {
        }
    }
}
