package src.main.java.com.example;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeWork2 {

    /*Урок 2. Исключения и их обработка
1. Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение.
Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.
2. Если необходимо, исправьте данный код (задание 2 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

3. Дан следующий код, исправьте его там, где требуется (задание 3 https://docs.google.com/document/d/17EaA1lDxzD5YigQ5OAal60fOFKVoCbEJqooB9XfhT7w/edit)

4. Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. Пользователю должно показаться сообщение, что пустые строки вводить нельзя.*/

    public static void main(String[] args) {
        // Проверка задания 1
        System.out.println("Результат: " + floatNumberConsoleInput());
        // Проверка задания 2
        taskTwoMethod(new int[]{4, 5, 6, 7, 5, 3, 23,}, 5);

        // Проверка задания 4
        System.out.println("Результат (не пустая строка): " + consoleNotEmptyInput());

    }

    // Задание 1
    public static float floatNumberConsoleInput() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите дробное число: ");
                return scanner.nextFloat();

            } catch (InputMismatchException e) {
                System.out.println("Ошибка. Вы ввели не число.");
            }
        }
    }

    // Задание 2
    public static void taskTwoMethod(int[] intArray, double d) {
        try {
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (Exception e) {
            System.out.println("Catching exception: " + e);
        }
    }


//    Задание 3

//    public static void main(String[] args) throws Exception {
//        try {
//            int a = 90;
//            int b = 3;
//            System.out.println(a / b);
//            printSum(23, 234);
//            int[] abc = {1, 2};
//            abc[3] = 9;
//        }  catch (NullPointerException ex) {
//            System.out.println("Указатель не может указывать на null!");
//        } catch (IndexOutOfBoundsException ex) {
//            System.out.println("Массив выходит за пределы своего размера!");
//        }
//        catch (Throwable ex) {
//            System.out.println("Что-то пошло не так...");
//        }
//    }
//
//    public static void printSum(Integer a, Integer b) {
//        System.out.println(a + b);
//    }

    // Задание 4
    public static String consoleNotEmptyInput() {

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите не пустую строку: ");
                String result = scanner.nextLine();
                if ("".equals(result)) {
                    throw new RuntimeException();
                }
                return result;

            } catch (Exception e) {
                System.out.println("Ошибка. Вы ввели пустую строку");
            }
        }
    }


}
