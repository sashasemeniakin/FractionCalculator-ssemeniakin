import java.util.Scanner;


/**
 * Fractional Calculator class
 */
public class FracCalc {


    /**
     * main method
     * @param args command line args
     */
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
    /**
     * function to parse input and produce the answer
     *  // This function takes a String 'input' and produces the result
     *     //
     *     // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
     *     //      e.g. input ==> "1/2 + 3/4"
     *     //
     *     // The function should return the result of the fraction after it has been calculated
     *     //      e.g. return ==> "1_1/4"
     * @param input String input with operands and operator
     * @return proper function in String format
     */
    public static String produceAnswer(String input)
    {
        //split input on space
        String[] splited = input.split("\\s+");

        int index = 0;
        String first;
        String operator;
        String second;
        String resultString = null;
        while(index < splited.length-1) {
            if(index == 0){
                //we're just starting so read first and second fraction input and operator
                first = splited[0];
                operator = splited[1];
                second = splited[2];
                index = 2;
            } else {
                //we're already looked at numbers before index
                //first is the result of previous loop
                first = resultString;
                operator = splited[index + 1];
                second = splited[index + 2];
                index = index + 2;
            }

            //create fractions from input strings
            Fraction firstFraction = new Fraction(first);
            Fraction secondFraction = new Fraction(second);

            Fraction resultFraction;
            //convert fractions to improper fractions
            firstFraction.convertToimproper();
            secondFraction.convertToimproper();

            if (operator.contains("+")) {
                // if the operation is addition it should cross multiply the fractions and then divide the numerator by denominator
                int numerator = (firstFraction.getNumerator() * secondFraction.getDenominator()) + (secondFraction.getNumerator() * firstFraction.getDenominator());
                int denominator = (firstFraction.getDenominator() * secondFraction.getDenominator());
                resultFraction = new Fraction(numerator, denominator);
            } else if (operator.contains("-")) {
                //if operator is subtraction,
                int numerator = (secondFraction.getDenominator() * firstFraction.getNumerator() - firstFraction.getDenominator() * secondFraction.getNumerator()); //numerator
                int denominator = (firstFraction.getDenominator() * secondFraction.getDenominator()); //denominator
                resultFraction = new Fraction(numerator, denominator);
            } else if (operator.contains("*")) {
                //if operator is multiplication
                int numerator = (firstFraction.getNumerator() * secondFraction.getNumerator());
                int denominator = (firstFraction.getDenominator() * secondFraction.getDenominator());
                resultFraction = new Fraction(numerator, denominator);
            } else if (operator.contains("/")) {
                //if operator is division
                int numerator = (firstFraction.getNumerator() * secondFraction.getDenominator());
                int denominator = (secondFraction.getNumerator() * firstFraction.getDenominator());
                resultFraction = new Fraction(numerator, denominator);
            } else {
                //unknown operator
                throw new RuntimeException("invalid operator");
            }
            resultString = simplifyFraction(resultFraction);
        }
        //resturn result without simplifying
        return resultString;
    }

    /**
     * simplify fraction
     * @param fraction Fraction object to simplify
     * @return String representation of the simplified fraction
     */
    static String simplifyFraction(Fraction fraction) {
        //divide numerator by denominator and take the whole from it.
        int whole = fraction.getNumerator() / fraction.getDenominator();
        // You then take the denominator and mupltiply by the whole
        int newnum = fraction.getDenominator() * whole;
        // subtract that number from numerator.
        int numerator = fraction.getNumerator() - newnum;

        if (numerator==0){
            return whole+"";
        }

        int greatestCommonDenomator = Math.abs(findGreatestCommonDenominator (numerator, fraction.getDenominator()));
        int resultNumerator = numerator / greatestCommonDenomator;
        int resultDenominator  = fraction.getDenominator() / greatestCommonDenomator;

        boolean answerIsNegative = false;
        if((resultDenominator < 0 && resultNumerator >0) ||
                (resultDenominator>0 && resultNumerator<0)) {
            answerIsNegative = true;
        }
        if (resultNumerator<0) {
            resultNumerator = resultNumerator*-1;
        }
        if(resultDenominator < 0) {
            resultDenominator = resultDenominator * -1;
        }

        if(whole == 0) {
           if(answerIsNegative) {
               return "-"+resultNumerator +"/"+ resultDenominator;
           } else {
               return resultNumerator + "/" + resultDenominator;
           }
        }


        return whole +"_"+ resultNumerator +"/"+ resultDenominator;
    }

    /**
     * function to find greatest common denominator
     * @param a first parameter, denominator at the first pass
     * @param b second parameter, numerator at the first pass
     * @return greatest common denominator
     */
    static int findGreatestCommonDenominator(int a, int b)
    {
        if (b == 0)
            return a;
        return findGreatestCommonDenominator(b, a % b);
    }


}
