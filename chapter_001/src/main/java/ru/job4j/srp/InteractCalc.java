package ru.job4j.srp;

import java.util.Scanner;

public class InteractCalc {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Menu for a calculater/
     */
    private void showMenu() {
        System.out.println("+. Add");
        System.out.println("-. Subtract");
        System.out.println("*. Multiply");
        System.out.println("/. Divide");
    }

    /**
     * Должен считывать с консоли целое число и возвращать его.
     * method getInt() should read an integer from the console and return it.
     */
    private int getInt() {
        System.out.println("Enter value:");
        int num;
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
        } else {
            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
            scanner.next();
            num = getInt();
        }
        return num;
    }

    /**
     * Должен считывать с консоли какое-то значение и возвращать символ с операцией (+, -, * или /).
     * Method getOperation()should read some value from the console and return
     * a character with the operation (+, -, * or /).
     */
    private char getOperation() {
        System.out.println("Enter operation:");
        char operation;
        if (scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
            scanner.next();
            operation = getOperation();
        }
        return operation;
    }

    /**
     * Должен выполнять над числами num1 и num2 арифметическую операцию, заданную operation.
     * Method calc() must perform the arithmetic operation specified by operation on numbers num1 and num2.
     */
    private int calc(int num1, int num2, char operation) {
        int result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = calc(num1, num2, getOperation());
        }
        return result;
    }

    private void run() {
        this.showMenu();
        int num1 = getInt();
        int num2 = getInt();
        char operation = getOperation();
        int result = calc(num1, num2, operation);
        System.out.println("Result operation: " + result);
    }

    public static void main(String[] args) {
        new InteractCalc().run();
    }

}
