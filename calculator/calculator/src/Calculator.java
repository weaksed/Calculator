import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
import java.util.*;

public class Calculator {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1st value:");
        String input1 = scanner.next();

        System.out.println("\nEnter operation:");
        String operator = scanner.next();

        System.out.println("\nEnter 2nd value:");
        String input2 = scanner.next();

        boolean isNum1Int = true;
        boolean isNum2Int = true;

        int num1 = 0;
        int num2 = 0;

        try {
            num1 = Integer.parseInt(input1);
        } catch (NumberFormatException e) {
            isNum1Int = false;
            num1 = convertToInteger(input1);
        }

        try {
            num2 = Integer.parseInt(input2);
        } catch (NumberFormatException e) {
            isNum2Int = false;
            num2 = convertToInteger(input2);
        }

        if (isNum1Int != isNum2Int) {
            throw new Exception ("Values format is different.");
        }

        int result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                System.out.println("\nIncorrect operator");
                return;
        }

        if (isNum1Int) { //проверяем одно значение, т.к. если значения будут разными, вызовется ошибка, что числа разныепо формату
            System.out.println("\nresult:\n" + result);
        }
        else {
            System.out.println("\nresult:\n" + convertToRoman(result));
        }
    }

    public static int convertToInteger(String roman) throws Exception {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        int count = 1;
        char lastSymbol = roman.charAt(0);

        for (int i = 1; i < roman.length(); i++) {
            if (roman.charAt(i) == lastSymbol) {
                count += 1;
                if (count == 4) {
                    throw new Exception ("Roman value can't contain more than 4 sequential symbols.");
                }
            }
            else {
                count = 1;
                lastSymbol = roman.charAt(i);
            }
        }

        for (int i = roman.length() - 1; i >= 0; i--) {
            int curValue = romanMap.get(roman.charAt(i));
            if (curValue < prevValue) {
                result -= curValue;
            } else {
                result += curValue;
            }
            prevValue = curValue;
        }

        return result;
    }

    public static String convertToRoman(int number) throws Exception {
        if (number < 1 || number > 3999) {
            throw new Exception("Incorrect roman value.");
        }

        String[] romanNumerals = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        StringBuilder result = new StringBuilder();

        int i = values.length - 1;
        while (number > 0) {
            if (number >= values[i]) {
                result.append(romanNumerals[i]);
                number -= values[i];
            } else {
                i--;
            }
        }

        return result.toString();
    }
}