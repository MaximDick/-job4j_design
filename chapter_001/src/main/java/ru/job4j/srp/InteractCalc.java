package ru.job4j.srp;


import jdk.dynalink.Operation;
import ru.job4j.calculator.Calculator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class InteractCalc {
//    private Scanner scanner;
//    private List<String> menu = new ArrayList<>();
//    private Map<Integer, BinaryOperator> operations = new HashMap<>();
//    Calculator calculator = new Calculator();
//
//    /**
//     * Menu for calculator.
//     */
//    private void showMenu() {
//        System.out.println("+. Add");
//        System.out.println("-. Subtract");
//        System.out.println("*. Multiply");
//        System.out.println("/. Divide");
//    }
//
//    /**
//     * Должен считывать с консоли целое число и возвращать его.
//     * method getInt() should read an integer from the console and return it.
//     */
//    private int getInt() {
//        System.out.println("Enter value:");
//        int num;
//        if (scanner.hasNextInt()) {
//            num = scanner.nextInt();
//        } else {
//            System.out.println("Вы допустили ошибку при вводе числа. Попробуйте еще раз.");
//            scanner.next();
//            num = getInt();
//        }
//        return num;
//    }
//
//    /**
//     * Должен считывать с консоли какое-то значение и возвращать символ с операцией (+, -, * или /).
//     * Method getOperation()should read some value from the console and return
//     * a character with the operation (+, -, * or /).
//     */
//    private char getOperation() {
//        System.out.println("Enter operation:");
//        char operation;
//        if (scanner.hasNext()) {
//            operation = scanner.next().charAt(0);
//        } else {
//            System.out.println("Вы допустили ошибку при вводе операции. Попробуйте еще раз.");
//            scanner.next();
//            operation = getOperation();
//        }
//        return operation;
//    }
//
//    /**
//     * Должен выполнять над числами num1 и num2 арифметическую операцию, заданную operation.
//     * Method calc() must perform the arithmetic operation specified by operation on numbers num1 and num2.
//     */
//    private int calc(int num1, int num2, char operation) {
//        int result;
//        switch (operation) {
//            case '+':
//                result = num1 + num2;
//                break;
//            case '-':
//                result = num1 - num2;
//                break;
//            case '*':
//                result = num1 * num2;
//                break;
//            case '/':
//                result = num1 / num2;
//                break;
//            default:
//                System.out.println("Операция не распознана. Повторите ввод.");
//                result = calc(num1, num2, getOperation());
//        }
//        return result;
//    }
//
//    private void run() {
//        this.showMenu();
//        int num1 = getInt();
//        int num2 = getInt();
//        char operation = getOperation();
//        int result = calc(num1, num2, operation);
//        System.out.println("Result operation: " + result);
//    }
//
//    public static void main(String[] args) {
//        new InteractCalc().run();
//    }

//    private Scanner scanner;
//    protected List<String> menu = new ArrayList<>();
//    protected Map<Integer, Operation> operations = new HashMap<>();
//
//
//    {
//        this.menu.add("1. Multiply");
//        this.menu.add("2. Divide");
//        this.menu.add("3. Add");
//        this.menu.add("4. Subtract");
//    }
//
//    {
//        this.operations.put(1, Calculator::multiply);
//        this.operations.put(2, Calculator::div);
//        this.operations.put(3, Calculator::add);
//        this.operations.put(4, Calculator::subtract);
//    }
//
//
//    public InteractCalc() {
//        this.scanner = new Scanner(System.in);
//    }
//
//    public InteractCalc(Scanner scanner) {
//        this.scanner = scanner;
//    }
//
//    /**
//     * Show all operations.
//     */
//    private void showMenu() {
//        this.menu.forEach(System.out::println);
//    }
//
//    /**
//     * Get required user input.
//     *
//     * @param msg to show
//     * @return user input
//     */
//    private int getInput(String msg) {
//        System.out.print(msg);
//        return this.scanner.nextInt();
//    }
//
//    /**
//     * Calculate basic math.
//     */
//    public void run() {
//        this.showMenu();
//        var operation = this.getInput("Choose operation: ");
//        var first = this.getInput("Input a number: ");
//        var second = 0;
//        if (operation > 0 && operation < 5) {
//            second = this.getInput("Input the 2nd number: ");
//        }
//        this.operations.get(operation).calc(first, second);
//    }
//
//    public static void main(String[] args) {
//        new InteractCalc().run();
//    }
}
