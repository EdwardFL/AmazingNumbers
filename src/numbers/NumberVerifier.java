package numbers;

public class NumberVerifier {

    boolean isOddNumber;
    boolean isEvenNumber;
    boolean isSquareNumber;
    boolean isBuzzNumber;
    boolean isSunnyNumber;
    public void verifyNumberParity(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("The number cannot be negative!");
        } else if (number % 2 == 0) {
            isEvenNumber = true;
        } else if (number % 2 != 0) {
            isOddNumber = true;
        }
    }

    public void checkSquareNumber(long number) {
        if (number > Math.sqrt(Long.MAX_VALUE)) {
            throw new IllegalArgumentException("Input value is too large to be represented by the long data type");
        } else if (number < 0) {
            isSquareNumber = false;
        } else {
            double square = Math.sqrt((double) number);
            if (square - Math.floor(square) == 0) {
                isSquareNumber = true;
            }
        }
    }

    public void checkBuzzNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("The number cannot be negative!");
        } else if ((number % 7 == 0) && (number % 10 == 7)) {
            isBuzzNumber = true;
        } else if ((number % 7 != 0) && (number % 10 == 7)) {
            isBuzzNumber = true;
        } else if ((number % 7 == 0) && (number % 10 != 7)) {
            isBuzzNumber = true;
        } else {
            isBuzzNumber = false;
        }
    }

    public void checkSunnyNumber(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("The number cannot be negative!");
        } else {
            double square = Math.sqrt((double) number + 1);
            if (square - Math.floor(square) == 0) {
                isSunnyNumber = true;
            }
        }
    }
}
