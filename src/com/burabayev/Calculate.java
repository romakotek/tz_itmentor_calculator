package com.burabayev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Calculate {
    private final static RomanNumbersConverter romanNumbersConverter = new RomanNumbersConverter();
    private final static String arithmethicOperators = "[\\/\\+\\-\\*]{1}";
    private String given;
    private boolean isRomanNumbers = false;

    enum ArithmethicOperation {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
    }
    enum NumberType {
        ROMAN, ARABIC
    }

    Calculate(String given) {
        this.given = given;
//         this.tokens = given.split(" ");
//         this.pos = 0;
    }

    private String[] getNumbers() {
        return given.split(arithmethicOperators);
    }

    private ConvertResult toInt(String numberAsStr) throws Exception {
        try {
            return new ConvertResult(Integer.parseInt(numberAsStr), NumberType.ARABIC);
        } catch (NumberFormatException e) {
            int res = romanNumbersConverter.convertToArabicNumber(numberAsStr);
            isRomanNumbers = true;
            return new ConvertResult(res, NumberType.ROMAN);
        }
    }

    private ArithmethicOperation getArithmethicOperation() throws Exception {

        Matcher matcher = Pattern
                .compile(arithmethicOperators)
                .matcher(this.given);
        String operation = "";
        if (matcher.find()) {
            operation = matcher.group();
        }

        if (operation.equals("+")) {
            return ArithmethicOperation.ADDITION;
        } else if (operation.equals("-")) {
            return ArithmethicOperation.SUBTRACTION;
        } else if (operation.equals("*")) {
            return ArithmethicOperation.MULTIPLICATION;
        } else if (operation.equals("/")) {
            return ArithmethicOperation.DIVISION;
        }
        throw new Exception("Operation not found");
    }

    String getResult() throws Exception {
        int res = calc();
        if(isRomanNumbers) {
            return romanNumbersConverter.convertToRomanNumber(res);
        } else {
            return String.valueOf(res);
        }
    }

    private int calc() throws Exception {
        String[] numbers = getNumbers();
        ConvertResult convertResult1 = toInt(numbers[0]);
        ConvertResult convertResult2 = toInt(numbers[1]);
        int num1 = convertResult1.number;
        int num2 = convertResult2.number;

        if(convertResult1.numberType == convertResult2.numberType) {
            if(convertResult1.numberType == NumberType.ARABIC) {
                isRomanNumbers = false;
            } else {
                isRomanNumbers = true;
            }

            if (
                    num1 >= 1 && num1 <= 10
                            && num2 >= 1 && num2 <= 10
            ) {
                ArithmethicOperation arithmethicOperation = getArithmethicOperation();
                if (arithmethicOperation == ArithmethicOperation.ADDITION) {
                    return num1 + num2;
                } else if (arithmethicOperation == ArithmethicOperation.DIVISION) {
                    return num1 / num2;
                } else if (arithmethicOperation == ArithmethicOperation.SUBTRACTION) {
                    return num1 - num2;
                } else if (arithmethicOperation == ArithmethicOperation.MULTIPLICATION) {
                    return num1 * num2;
                }
            } else {
                throw new IllegalArgumentException("Numbers are out of range[1;10]");
            }
        } else {
            throw new Exception("Number types are different");
        }
        throw new Exception("Not found");
    }

    class ConvertResult {
        private int number;
        private NumberType numberType;

        public ConvertResult(int number, NumberType numberType) {
            this.number = number;
            this.numberType = numberType;
        }

        public int getNumber() {
            return number;
        }

        public NumberType getNumberType() {
            return numberType;
        }
    }
}
