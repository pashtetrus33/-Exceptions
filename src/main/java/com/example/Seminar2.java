package src.main.java.com.example;

import java.io.*;
import java.util.HashMap;

public class Seminar2 {
    public static void main(String[] args) {

        //String[][] testArray = {{"5", "45"}, {"47", "0", "32"}, {"29", "0", "1"}, {"0", "459", "0"}};
        //System.out.println("Summa: " + sum2d(testArray));

        readFileAndCreateHashMap("seminar2");

    }

    public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    int val = Integer.parseInt(arr[i][j]);
                    if (val == 0) {
                        throw new RuntimeException("Нашли битое значение! " + i + "; " + j);
                    }
                    sum += val;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return sum;
    }


    /* Запишите в файл следующие строки:
Анна=4
Елена=5
Марина=6
Владимир=?
Константин=?
Иван=4
Реализуйте метод, который считывает данные из файла и сохраняет в двумерный массив (либо HashMap, если студенты с ним знакомы).
В отдельном методе нужно будет пройти по структуре данных, если сохранено значение ?, заменить его на соответствующее число.
Если на каком-то месте встречается символ, отличный от числа или ?,
бросить подходящее исключение.Записать в тот же файл данные с замененными символами ?.  */

    public static void readFileAndCreateHashMap(String file) {
        HashMap<String, Integer> dataFile = new HashMap<>();
        try {
            FileReader inputData = new FileReader(file);
            BufferedReader reader = new BufferedReader(inputData);
            try {
                String data = reader.readLine();
                if (data != null) {
                    while (data != null) {
                        if (!data.split("=")[1].equals("?") && !isNumberic(data.split("=")[1])){
                            throw new RuntimeException("Not digit and not '?'");
                        }
                        if ("?".equals(data.split("=")[1])) {
                            dataFile.put(data.split("=")[0], 100);
                        } else {
                            dataFile.put(data.split("=")[0], Integer.parseInt(data.split("=")[1]));
                        }
                        data = reader.readLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(dataFile);
        try {
            writeHashMapToFile(file,dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean isNumberic(String data){
        try{
            Integer.parseInt(data);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static void writeHashMapToFile(String fileName, HashMap<String, Integer> data) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName);

        for (String key : data.keySet()) {
            Integer value = data.get(key);

            if (value == null) {
                writer.println(key + "=?");
            } else {
                writer.println(key + "=" + value);
            }
        }

        writer.close();
    }
}
