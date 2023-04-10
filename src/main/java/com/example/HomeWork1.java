package src.main.java.com.example;

import java.io.FileReader;

public class HomeWork1 {

    // Реализуйте 3 метода, чтобы в каждом из них получить разные исключения

    public static void main(String[] args) {

        System.out.println("Quotient: " + mathDivision(10, 2));

        System.out.println("Number: " + getMemberByIndex(new int[]{4, 5, 6, 7,}, 1));

        //fileReading("file");
        int[] result = substructElementsOfArray(new int[]{6, 8, 5}, new int[]{0, 4, 7});
        if (result != null) {
            printArray(result);
        }

        result = divisionElementsOfArray(new int[]{8, 5, 4}, new int[]{2, 4, 7});
        if (result != null) {
            printArray(result);
        }


        final char FINDCHAR = '5';
        final int STRINGNUMBER = 9;
        System.out.println("Строка №" + STRINGNUMBER + ": "+ listGenerate(STRINGNUMBER));
        System.out.println("Число цифр " + FINDCHAR + " в строке " + STRINGNUMBER + " равно " + countDigit(listGenerate(STRINGNUMBER),FINDCHAR));
        System.out.println(countDigit(listGenerate(9),'5') + " " + countDigit(listGenerate(9),'7'));
    }

    //Метод 1 ArithmeticException
    private static double mathDivision(int dividend, int divisor) {
        try {
            return dividend / divisor;
        } catch (Exception e) {
            throw new ArithmeticException("Division by zero");
        }
    }

    //Метод 2 IndexOutOfBoundsException
    private static int getMemberByIndex(int[] array, int index) {
        int result = 0;
        try {
            return array[index];
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    //Метод 3 FileNotFoundException
    private static void fileReading(String file) {
        try (FileReader reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {

                System.out.print((char) c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
    // каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке.
    // Если длины массивов не равны, необходимо как-то оповестить пользователя.

    private static int[] substructElementsOfArray(int[] array1, int[] array2) {
        int[] result = new int[array1.length];
        if (array1.length == array2.length) {

            for (int i = 0; i < array1.length; i++) {
                result[i] = array1[i] - array2[i];
            }
            return result;
        } else {
            System.out.println("Arrays length is different!");
            return null;
        }
    }

    //* Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
    // каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке. Если длины массивов не равны,
    // необходимо как-то оповестить пользователя.
    // Важно: При выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException, т.е. ваше.

    private static int[] divisionElementsOfArray(int[] array1, int[] array2) {
        int[] result = new int[array1.length];
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; i++) {
                try {
                    result[i] = array1[i] / array2[i];
                } catch (Exception e) {
                    throw new RuntimeException("Деление на ноль");
                }
            }
            return result;
        } else {
            System.out.println("Arrays length is different!");
            return null;
        }
    }

    private static void printArray(int[] array) {
        System.out.print("Result array: ");
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();
    }


    /*Строки, состоящие из последовательностей цифр,
          формируются следующим образом. Первая строка состоит из
          четырех единиц. Каждая из последующих строк создается
          следующим действием: берется предыдущая строка и после
          каждой ее цифры вставляется цифра на единицу большая. Вот
          первые 3 строки, созданные по этому правилу:
          (1) 1111
          (2) 12121212
          (3) 1223122312231223
          Сколько цифр 5 и сколько цифр 7 будет в строке с номером
          (9)?
          В ответе укажите через пробел два целых числа: сначала
          количество цифр 5 в девятой строке, а затем количество цифр
          7 в девятой строке*/

    public static String listGenerate(int stringNumber) {
        String result = "1 1 1 1";
        String[] array;
        for (int i = 0; i < stringNumber-1; i++) {
            array = result.split(" ");
            result = "";
            for (String str : array) {
                int num = Integer.parseInt(str) + 1;
                result += str + " " + num + " ";
            }
        }
        return result;
    }
    private static int countDigit(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }
}

