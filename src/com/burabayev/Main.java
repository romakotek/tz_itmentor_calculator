package com.burabayev;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//        •	Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b,
// a - b, a * b, a / b.
//        • Данные передаются в одну строку (смотри пример)! Решения, в которых каждое число и арифмитеческая
//  операция передаются с новой строки считаются неверными.
//        •	Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
//        +	Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не
// ограничиваются по величине и могут быть любыми.
//        +	Калькулятор умеет работать только с целыми числами.
//        •	Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем
// строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
//        •	При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских -
// ответ ожидается арабскими.
//        +	При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
//        +	При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
// приложение выбрасывает исключение и завершает свою работу.
//        +	Результатом операции деления является целое число, остаток отбрасывается.
//        +	Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
//        - Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат
// работы меньше единицы, выбрасывается исключение

// a + b, a - b, a * b, a / b
// 1/2 -> 0
// 3+4 -> 7
// 5-10 -> -5
// 1+5  -> 6
// 0+1 -> Exception()
// 1-11 -> Exception()

// I + V, X + V, V - X, I + II -> throw Exception()
// I + 1, ... -> throw Exception()
// I + V, X + V, X - V, I + II -> VI, XV, V, III
//

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Type some operation with two integers in one line bellow and press enter:");
            String given = scanner.nextLine();
            Calculate calcApp = new Calculate(given);

            try {
                System.out.println(calcApp.getResult());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Calculate {
    private final static RomeNumbersConverter romeNumbersConverter = new RomeNumbersConverter();
    private final static String arithmethicOperators = "[\\/\\+\\-\\*]{1}";

//     private String[] tokens;
//     private int pos;
    private String given;

    private boolean isRomeNumbers = false;

    enum ArithmethicOperation {
        SUM, MINUS, MULTIPLE, DIVIDE
    }
    enum NumberType {
        ROME, ARAB
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
        // написать mapper для римских чисел
        try {
            return new ConvertResult(Integer.parseInt(numberAsStr), NumberType.ARAB);
        } catch (NumberFormatException e) {
            int res = romeNumbersConverter.convertToArabNumber(numberAsStr);
            isRomeNumbers = true;
            return new ConvertResult(res, NumberType.ROME);
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
            return ArithmethicOperation.SUM;
        } else if (operation.equals("-")) {
            return ArithmethicOperation.MINUS;
        } else if (operation.equals("*")) {
            return ArithmethicOperation.MULTIPLE;
        } else if (operation.equals("/")) {
            return ArithmethicOperation.DIVIDE;
        }
        throw new Exception("Operation not found");
    }

    String getResult() throws Exception {
        int res = calc();
        if(isRomeNumbers) {
            return romeNumbersConverter.convertToRomeNumber(res);
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
            if(convertResult1.numberType == NumberType.ARAB) {
                isRomeNumbers = false;
            } else {
                isRomeNumbers = true;
            }

            if (
                    num1 >= 1 && num1 <= 10
                            && num2 >= 1 && num2 <= 10
            ) {
                ArithmethicOperation arithmethicOperation = getArithmethicOperation();
                if (arithmethicOperation == ArithmethicOperation.SUM) {
                    return num1 + num2;
                } else if (arithmethicOperation == ArithmethicOperation.DIVIDE) {
                    return num1 / num2;
                } else if (arithmethicOperation == ArithmethicOperation.MINUS) {
                    return num1 - num2;
                } else if (arithmethicOperation == ArithmethicOperation.MULTIPLE) {
                    return num1 * num2;
                }
            } else {
                throw new IllegalArgumentException("Numbers are out of range[1;10]");
            }
        } else {
            throw new Exception("Number types is different");
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

