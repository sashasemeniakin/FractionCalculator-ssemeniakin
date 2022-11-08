import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Create a Scanner for user input
        Scanner input = new Scanner(System.in);
        String answer = produceAnswer(input.next());

        System.out.println(answer);
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        String[] splited = input.split("\\s+");
        if (splited.length != 3) {
            throw new RuntimeException("invalid number of operands");
        }
        String first = splited[0];
        String operator = splited[1];
        String second = splited[2];

        return second;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
