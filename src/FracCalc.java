import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
        //Read the input from the user and call produceAnswer with an equation
        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Ask user for equation or quit to quit
        System.out.println("enter your equation or type quit: ");
        // read user's input
        String input = scanner.nextLine();

        //keep going until user quits
        while (!input.equalsIgnoreCase("quit")) {
            //produce answer with given input
            System.out.println(produceAnswer(input));
            //ask again if the user wants to enter equation or quit
            System.out.println("enter your equation or type quit: ");
            //read user input
            input = scanner.nextLine();
        }

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
        //split input on space
        String[] splited = input.split("\\s+");
        //if input doesn't have 3 components, error out
        if (splited.length != 3) {
            throw new RuntimeException("invalid number of operands");
        }
        //read first and second fraction input and operator
        String first = splited[0];
        String operator = splited[1];
        String second = splited[2];

        //create freactions from input strings
        Fraction firstFraction = new Fraction(first);
        Fraction secondFraction = new Fraction(second);

        Fraction result;
        //convert fractions to improper fractions
        firstFraction.convertToimproper();
        secondFraction.convertToimproper();

        if(operator.contains("+")){
            // if the operation is addition it should cross multiply the fractions and then divide the numerator by denominator
            int numerator = (firstFraction.getNumerator()* secondFraction.getDenominator())+(secondFraction.getNumerator()* firstFraction.getDenominator());
            int denominator = (firstFraction.getDenominator()* secondFraction.getDenominator());
            result = new Fraction(numerator,denominator);
        } else if (operator.contains ("-")) {
            //if operator is subtraction,
            int numerator = (secondFraction.getDenominator()* firstFraction.getNumerator()-firstFraction.getDenominator()* secondFraction.getNumerator()); //numerator
            int denominator = (firstFraction.getDenominator()* secondFraction.getDenominator()); //denominator
            result = new Fraction(numerator,denominator);
        } else if (operator.contains ("*")) {
            //if operator is multiplication
            int numerator = (firstFraction.getNumerator()* secondFraction.getNumerator());
            int denominator = (firstFraction.getDenominator()* secondFraction.getDenominator());
            result = new Fraction(numerator,denominator);
        }else if (operator.contains ("/")) {
            //if operator is division
            int numerator = (firstFraction.getNumerator()* secondFraction.getDenominator());
            int denominator = (secondFraction.getNumerator()* firstFraction.getDenominator());
            result = new Fraction(numerator,denominator);
        }else{
            //unknown operator
            throw new RuntimeException("invalid operator");
        }
        //resturn result without simplifying
        return result.getNumerator()+"/"+result.getDenominator();
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    
}
