package numbers;

import java.util.*;

public class Main {

    protected static boolean isEvenNumber;
    private static boolean isOddNumber;
    private static boolean isBuzzNumber;
    private static boolean isDuckNumber;
    private static boolean isPalindromicNumber;
    private static boolean isGapfulNumber;
    private static boolean isSpyNumber;
    private static boolean isSunnyNumber;
    private static boolean isSquareNumber;
    private static boolean isJumpingNumber;
    private static boolean isHappyNumber;
    private static boolean isSadNumber;
    private static String parameterNumber;
    private static boolean excludeProperty;
    private static long userNumber = 0;
    private static final String[] propertiesList = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        welcomeUsers();
        String userRequest;
        do {
            System.out.println("\nEnter a request: ");

            userRequest = scanner.nextLine();
            String[] splitLine = userRequest.split(" ");
            userNumber = Long.parseLong(splitLine[0]);
            int secondParameter = 0;
            boolean hasOneParameter = splitLine.length == 1;
            boolean hasTwoParameters = splitLine.length == 2;
            boolean hasProperties = splitLine.length > 2;

            if (userNumber == 0) {
                System.out.println("Goodbye!");
                return;
            } else if (hasOneParameter) {
                if (checkFirstParameter(userNumber)) {
                    checkNumberProperties(userNumber);
                    printNumberProperties(userNumber);
                }
            } else if (hasTwoParameters) {
                secondParameter = Integer.parseInt(splitLine[1]);
                if (checkFirstParameter(userNumber) && checkSecondParameter(secondParameter)) {
                    long[] userNumbers = new long[secondParameter];
                    userNumbers[0] = userNumber;
                    for (int i = 0; i < secondParameter; i++) {
                        userNumbers[i] = userNumbers[0] + i;
                    }
                    for (int i = 0; i < userNumbers.length; i++) {
                        checkNumberProperties(userNumbers[i]);
                        System.out.println(printNumbersProperties(userNumbers[i]));
                    }
                }
            } else if (hasProperties) {
                secondParameter = Integer.parseInt(splitLine[1]);
                int numberOfProperties = splitLine.length - 2;
                String[] properties = new String[numberOfProperties];
                for (int i = 2; i < splitLine.length; i++) {
                    properties[i - 2] = splitLine[i];
                }
                if (checkFirstParameter(userNumber) && checkSecondParameter(secondParameter) && checkProperties(properties)) {
                    int countNumbersToBeGenerated = Integer.parseInt(splitLine[1]);
                    long[] userNumbers = new long[countNumbersToBeGenerated];
                    userNumbers[0] = userNumber;
                    boolean isFound;
                    long number = userNumber;
                    for (int i = 0; i < countNumbersToBeGenerated; i++) {
                        isFound = false;
                        while (!isFound) {

                            checkNumberProperties(number);
                            String numberProperties = printNumbersProperties(number);
                            int countProperties = 0;

                            for (int j = 0; j < properties.length; j++) {

                                if (numberProperties.toLowerCase().contains(properties[j].toLowerCase())) {
                                    countProperties++;
                                } else if (checkForNegativeProperties(properties[j])  && !numberProperties.toLowerCase().contains(properties[j].substring(properties[j].indexOf("-") + 1, properties[j].length()).toLowerCase())) {
                                    countProperties++;
                                } else {
                                    continue;
                                }
                            }
                            if (properties.length == countProperties) {
                                System.out.println(numberProperties);
                                isFound = true;
                                userNumbers[i] = number;
                            } else {
                                number++;
                                continue;
                            }
                            number++;
                        }
                    }
                }
            }
        } while (userNumber != 0);

    }

    public static void verifyNumberParity(long userNumber) {
        isEvenNumber = false;
        isOddNumber = false;
        if (userNumber % 2 == 0) {
            isEvenNumber = true;
        } else if (userNumber % 2 != 0) {
            isOddNumber = true;
        }
    }

    public static boolean checkForNegativeProperties(String property){
        if (property.contains("-")) {
            if (isParameterInList(property.substring(property.indexOf("-") + 1, property.length()))) {
                excludeProperty = true;
                return true;
            }
        }
        excludeProperty = false;
        return false;
    }

    public static boolean checkProperties(String[] properties) {
        List<String> wrongProperties = new ArrayList<>();
        String[] tempProperties = Arrays.copyOf(properties, properties.length);
        int count = 0;

        for (int i = 0; i < properties.length; i++) {
            if(checkForNegativeProperties(properties[i])) {
                continue;
            }
            if (!isParameterInList(properties[i])) {
                wrongProperties.add(properties[i]);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("The property [" + wrongProperties.get(0) + "] is wrong");
            System.out.println("Available properties: " + Arrays.toString(propertiesList));
            return false;
        } else if (count > 1) {
            System.out.println("The properties " + Arrays.toString(wrongProperties.toArray()) + " are wrong");
            System.out.println("Available properties: " + Arrays.toString(propertiesList));
            return false;
        } else {
            if (properties.length >= 2) {
                for (int i = 0; i < properties.length; i++) {
                    for (int j = 0; j < properties.length; j++) {
                        if (isMutuallyExclusive(properties[i], properties[j])) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    public static boolean isParameterInList(String parameter) {
        boolean isAvailable = false;
        String tempParameter = parameter;
        for (int i = 0; i < propertiesList.length; i++) {
            if (parameter.contains("-")) {
                tempParameter = parameter.substring(parameter.indexOf("-"), parameter.length());
            }
            if (propertiesList[i].toLowerCase().contains(tempParameter.toLowerCase())) {
                isAvailable = true;
                break;
            } else {
                isAvailable = false;
            }
        }
        return isAvailable;
    }


    public static boolean checkFirstParameter(long firstParameter) {
        try {
            if (firstParameter < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("The first parameter should be a natural number or zero.");
            userNumber = -1;
            return false;
        }
    }

    public static boolean checkSecondParameter(int secondParameter) {
        try {
            if (secondParameter <= 0) {
                System.out.println("The second parameter should be a natural number.");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("The second parameter should be a natural number.");
            userNumber = -1;
            return false;
        }
    }

    public static boolean isMutuallyExclusive(String first, String second) {
        boolean isMutuallyExclusive = false;
        if (first.toLowerCase().equals("even") && second.toLowerCase().equals("odd") ||
                first.toLowerCase().equals("-even") && second.toLowerCase().equals("-odd") ||
                first.toLowerCase().equals("duck") && second.toLowerCase().equals("spy") ||
                first.toLowerCase().equals("-duck") && second.toLowerCase().equals("-spy") ||
                first.toLowerCase().equals("sunny") && second.toLowerCase().equals("square") ||
                first.toLowerCase().equals("happy") && second.toLowerCase().equals("sad")||
                first.toLowerCase().equals("-happy") && second.toLowerCase().equals("-sad")||
                first.length() > second.length() && first.toLowerCase().contains(second.toLowerCase()) ||
                second.length() > first.length() && second.toLowerCase().contains(first.toLowerCase())) {
            isMutuallyExclusive = true;
            System.out.printf("\nThe request contains mutually exclusive properties: [%s, %s]\n" +
                    "There are no numbers with these properties.\n", first, second);
        }
        return isMutuallyExclusive;
    }

    public static boolean checkHappyNumber(long userNumber) {

        long number = userNumber;
        int sum = 0;

        Set<Long> seen = new HashSet<>();

        while (sum != 1 && !seen.contains(number)) {
            seen.add(number);
            sum = 0;
            while (number > 0) {
                int digit = (int) (number % 10);
                sum += digit * digit;
                number /= 10;
            }
            number = sum;
        }

        if (sum == 1) {
            isHappyNumber = true;
            return true;
        } else {
            isHappyNumber = false;
            return false;
        }
    }

    public static void checkUnhappyNumber() {
        if (!isHappyNumber) {
            isSadNumber = true;
        } else {
            isSadNumber = false;
        }
    }

    public static void checkJumpingNumber(long userNumber) {
        isJumpingNumber = true;
        String[] stringNumber = String.valueOf(userNumber).split("");
        for (int i = 0; i < stringNumber.length - 1; i++) {
            if (!(Integer.parseInt(stringNumber[i]) + 1 == Integer.parseInt(stringNumber[i + 1])
                    || (Integer.parseInt(stringNumber[i]) - 1 == Integer.parseInt(stringNumber[i + 1])))) {
                isJumpingNumber = false;
                break;
            }
        }
    }
    public static void checkSpyNumber(long userNumber) {
        isSpyNumber = false;
        String[] stringNumber = String.valueOf(userNumber).split("");
        int sum = 0;
        int product = 1;
        for (int i = 0; i < stringNumber.length; i++) {
            sum += Integer.parseInt(stringNumber[i]);
            product *= Integer.parseInt(stringNumber[i]);
        }
        if (sum == product) {
            isSpyNumber = true;
        } else {
            isSpyNumber = false;
        }
    }

    public static void checkSquareNumber(long userNumber) {
        isSquareNumber = false;
        double square = Math.sqrt((double) userNumber);
        if (square - Math.floor(square) == 0) {
            isSquareNumber = true;
        }
    }

    public static void checkSunnyNumber(long userNumber) {
        isSunnyNumber = false;
        double square = Math.sqrt((double) userNumber + 1);
        if (square - Math.floor(square) == 0) {
            isSunnyNumber = true;
        }
    }

    public static void checkBuzzNumber(long userNumber) {
        isBuzzNumber = false;
        if ((userNumber % 7 == 0) && (userNumber % 10 == 7)) {
            isBuzzNumber = true;
        } else if ((userNumber % 7 != 0) && (userNumber % 10 == 7)) {
            isBuzzNumber = true;
        } else if ((userNumber % 7 == 0) && (userNumber % 10 != 7)) {
            isBuzzNumber = true;
        }
        else {
            isBuzzNumber = false;
        }
    }

    public static void checkDuckNumber(String userNumber) {
        isDuckNumber = false;
        boolean firstDigit = true;

        for (int i = 0; i < userNumber.length(); i++) {
            if (userNumber.charAt(i) == '0' && firstDigit) {
                isDuckNumber = false;
                return;
            } else if (userNumber.charAt(i) == '0')  {
                isDuckNumber = true;
                return;
            } else {
                isDuckNumber = false;
            }
            firstDigit = false;
        }
    }

    public static void checkPalindromicNumber(String userNumber) {
        isPalindromicNumber = false;
        String reverse = "";
        for (int i = userNumber.length() - 1; i >= 0; i--) {
            reverse += userNumber.charAt(i);
        }
        if(userNumber.equals(reverse)) {
            isPalindromicNumber = true;
        } else {
            isPalindromicNumber = false;
        }
    }

    public static void checkGapfulNumber(String userNumber) {
        isGapfulNumber = false;
        long number = Long.parseLong(userNumber);
        String firstAndLastCharConcatenation = Character.toString(userNumber.charAt(0)) + Character.toString(userNumber.charAt(userNumber.length() - 1));
        if(userNumber.length() < 3) {
            isGapfulNumber = false;
        } else if ((number >= 3) && (number % Integer.parseInt(firstAndLastCharConcatenation) == 0)) {
            isGapfulNumber = true;
        } else {
            isGapfulNumber = false;
        }
    }

    public static void checkNumberProperties(long userNumber) {
        verifyNumberParity(userNumber);
        checkBuzzNumber(userNumber);
        checkDuckNumber(Long.toString(userNumber));
        checkPalindromicNumber(Long.toString(userNumber));
        checkGapfulNumber(Long.toString(userNumber));
        checkSpyNumber(userNumber);
        checkSquareNumber(userNumber);
        checkSunnyNumber(userNumber);
        checkJumpingNumber(userNumber);
        checkHappyNumber(userNumber);
        checkUnhappyNumber();
    }

    public static void printNumberProperties(long userNumber) {
        System.out.println("\nProperties of " + userNumber + "\n\tbuzz: " + isBuzzNumber
                + "\n\tduck: " + isDuckNumber
                + "\n\tpalindromic: " + isPalindromicNumber
                + "\n\tgapful: " + isGapfulNumber
                + "\n\tspy: " + isSpyNumber
                + "\n\tsquare: " + isSquareNumber
                + "\n\tsunny: " + isSunnyNumber
                + "\n\tjumping: " + isJumpingNumber
                + "\n\thappy: " + isHappyNumber
                + "\n\tsad: " + isSadNumber
                + "\n\teven: " + isEvenNumber
                + "\n\todd: " + isOddNumber);
    }

    public static String printNumbersProperties(long userNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(userNumber);
        sb.append(" is ");
        if (isBuzzNumber) sb.append("buzz, ");
        if (isDuckNumber) sb.append("duck, ");
        if (isPalindromicNumber) sb.append("palindromic, ");
        if (isGapfulNumber) sb.append("gapful, ");
        if (isSpyNumber) sb.append("spy, ");
        if (isSquareNumber) sb.append("square, ");
        if (isSunnyNumber) sb.append("sunny, ");
        if (isJumpingNumber) sb.append("jumping, ");
        if (isHappyNumber) sb.append("happy, ");
        if (isSadNumber) sb.append("sad, ");
        if (isEvenNumber) sb.append("even, ");
        if (isOddNumber) sb.append("odd, ");

        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public static void welcomeUsers() {
        System.out.println("Welcome to Amazing Numbers! \n");

        System.out.println("Supported requests: \n" +
                "- enter a natural number to know its properties; \n" +
                "- enter two natural numbers to obtain the properties of the list; \n" +
                "  * the first parameter represents a starting number; \n" +
                "  * the second parameter shows how many consecutive numbers are to be processed; \n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space; \n" +
                "- enter 0 to exit.");
    }
}