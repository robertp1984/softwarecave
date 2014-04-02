package com.example.evalpostfix;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class PostfixEvaluator {

    private Deque<Double> args;

    public PostfixEvaluator() {
        args = new LinkedList<>();
    }

    double evaluate(String expr) {
        args.clear();
        try (Scanner scanner = new Scanner(expr)) {
            while (scanner.hasNext()) {
                String token = scanner.next();
                processToken(token);
            }
        }

        if (args.size() == 1) {
            return args.pop();
        } else {
            throw new IllegalArgumentException("Invalid number of operators");
        }
    }

    private void processToken(String token) {
        switch (token) {
            case "+":
                addArgs();
                break;
            case "-":
                subArgs();
                break;
            case "*":
                mulArgs();
                break;
            case "/":
                divArgs();
                break;
            default:
                try {
                    double arg = Double.parseDouble(token);
                    args.push(arg);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number: " + token, e);
                }
        }
    }

    private void addArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 + arg2);
    }

    private void subArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 - arg2);
    }

    private void mulArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 * arg2);
    }

    private void divArgs() {
        checkArgumentsSize();
        double arg2 = args.pop();
        double arg1 = args.pop();
        args.push(arg1 / arg2);
    }

    private void checkArgumentsSize() {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Not enough parameters for operation");
        }
    }
}
