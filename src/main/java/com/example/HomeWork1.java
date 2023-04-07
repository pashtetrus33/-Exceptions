package src.main.java.com.example;

import java.io.FileReader;

public class HomeWork1 {

    // ���������� 3 ������, ����� � ������ �� ��� �������� ������ ����������

    public static void main(String[] args) {

        System.out.println("Quotient: " + mathDivision(10, 2));

        System.out.println("Number: " + getMemberByIndex(new int[]{4, 5, 6, 7,}, 1));

        //fileReading("file");
        int[] result = substructElementsOfArray(new int[]{6, 8, 5, 4}, new int[]{0, 4, 7});
        if (result != null) {
            printArray(result);
        }

        result = divisionElementsOfArray(new int[]{8, 5, 4}, new int[]{0, 4, 7});
        if (result != null) {
            printArray(result);
        }
    }

    //����� 1 ArithmeticException
    private static double mathDivision(int dividend, int divisor) {
        try {
            return dividend / divisor;
        } catch (Exception e) {
            throw new ArithmeticException("Division by zero");
        }
    }

    //����� 2 IndexOutOfBoundsException
    private static int getMemberByIndex(int[] array, int index) {
        int result = 0;
        try {
            return array[index];
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    //����� 3 FileNotFoundException
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

    //���������� �����, ����������� � �������� ���������� ��� ������������� �������, � ������������ ����� ������,
    // ������ ������� �������� ����� �������� ��������� ���� �������� �������� � ��� �� ������.
    // ���� ����� �������� �� �����, ���������� ���-�� ���������� ������������.

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

    //* ���������� �����, ����������� � �������� ���������� ��� ������������� �������, � ������������ ����� ������,
    // ������ ������� �������� ����� �������� ��������� ���� �������� �������� � ��� �� ������. ���� ����� �������� �� �����,
    // ���������� ���-�� ���������� ������������.
    // �����: ��� ���������� ������ ������������ ����������, ������� ������������ ����� ������� - RuntimeException, �.�. ����.

    private static int[] divisionElementsOfArray(int[] array1, int[] array2) {
        int[] result = new int[array1.length];
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; i++) {
                try {
                    result[i] = array1[i] / array2[i];
                } catch (Exception e) {
                    throw new RuntimeException("������� �� ����");
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
}
