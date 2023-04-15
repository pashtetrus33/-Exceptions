package src.main.java.com.example;

import java.io.File;

public class Seminar3 {
}

class ElementException extends Exception {
    public ElementException(String message) {
        super(message);
    }

    public static <T> void getElement(T[] array, int index) throws ElementException {
        if (index < 0 || index >= array.length) {
            throw new ElementException("Элемент с индексом " + index + " отсутсвует в массиве");
        }
        T element = array[index];
        if (element == null) {
            throw new ElementException("Элемент с индексом" + index + " null!");
        }
    }

    public static void main(String[] args) throws ElementException {
        getElement(new Integer[]{1, 2, null, 6}, 2);
    }
}

class NoFile extends Exception {
    public NoFile() {
        super("Нету такого файла");
    }

    public static void readFile(String fileName) throws NoFile {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new NoFile();
        }
    }

    public static void main(String[] args) throws NoFile {
        readFile("README.md");
    }
}

class ZeroExc extends Exception {
    public ZeroExc() {
        super("Деление на ноль невозможно!");
    }

    public static int division(int num1, int num2) throws ZeroExc {
        if (num2 == 0) {
            throw new ZeroExc();
        }
        return num1 / num2;
    }

    public static void main(String[] args) throws ZeroExc {
        System.out.println(division(2, 0));
    }
}

class SumArray {

    public static void main(String[] args) {
        String[][] arr = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "34"}
        };

        try {
            int sum = sumArray(arr);
            System.out.println("Сумма: " + sum);
        } catch (MyArraySize | MyArrayData e) {
            System.out.println(e.getMessage());
        }
    }

    public static int sumArray(String[][] arr) throws MyArraySize, MyArrayData {
        int sum = 0;
        if (arr.length != 4) {
            throw new MyArraySize();
        }
        for (int i = 0; i < 4; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySize();
            }
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayData();
                }
            }
        }
        return sum;
    }
}

class MyArrayData extends Throwable {
    public MyArrayData() {
        super("Неверный формат данных");
    }
}

class MyArraySize extends Throwable {
    public MyArraySize() {
        super("Размер массива должен быть 4x4");
    }
}




