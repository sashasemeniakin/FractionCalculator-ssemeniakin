//class to represent fraction
public class Fraction {
    private int whole = 0;
    private int numerator = 0;
    private int denominator = 1;

    //constructor
    public Fraction(int numerator,int denominator){
        this.numerator=numerator;
        this.denominator=denominator;
    }
    //convert proper fraction to improper
    public void convertToimproper(){
        //if there is no whole number, don't do anuything and return
        if (whole==0){
            return;
        }
        //if whole number is negative
        if (whole<0){
            numerator = whole*denominator - numerator;
        }else{
            // if whole number is positive
            numerator = whole*denominator + numerator;
        }
        //reset whole to zero after
        whole=0;
    }


    //constructor to create fraction from string input
    public Fraction(String input) {
        String[] firstSplited = input.split("_");
        //check length of firstSplited, if length is 1, then whole is not specified and if length is 2, whole is specified.
        if (firstSplited.length == 1) {
            if (firstSplited[0].contains("/")) {
                whole = 0;
                //split fraction with /
                String[] firstFraction = firstSplited[0].split("/");
                //read numerator and denominator
                if (firstFraction.length == 2) {
                    numerator = Integer.parseInt(firstFraction[0]);
                    denominator = Integer.parseInt(firstFraction[1]);
                } else {
                    throw new NumberFormatException("invalid fraction");
                }

            } else {
                //it is a whole number then you read it
                whole = Integer.parseInt(firstSplited[0]);
            }


        } else if (firstSplited.length == 2) {
            //fraction has a whole component
            whole = Integer.parseInt(firstSplited[0]);

            //split fraction by /
            String[] firstFraction = firstSplited[1].split("/");
            if (firstFraction.length == 2) {
                numerator = Integer.parseInt(firstFraction[0]);
                denominator = Integer.parseInt(firstFraction[1]);
            } else {
                throw new NumberFormatException("invalid fraction");
            }
        } else {
            throw new NumberFormatException("invalid number format");
        }

    }
    public int getWhole(){
        return whole;
    }
    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }

}