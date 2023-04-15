package src.main.java.com.example;

/*Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол
Форматы данных:
фамилия, имя, отчество - строки
дата_рождения - строка формата dd.mm.yyyy
номер_телефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки,
обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают,
 нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои.
 Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.*/

import javax.print.attribute.standard.MediaSize;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class SizeInputDataException extends Exception {
    public SizeInputDataException(String s) {
        super(s);
    }
}

class WrongDataTypeException extends Exception {
    public WrongDataTypeException(String s) {
        super(s);
    }
}

public class HomeWork3 {

    private static final int SURNAME_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int MIDDLE_NAME_INDEX = 2;
    private static final int BIRTHDAY_INDEX = 3;
    private static final int PHONE_INDEX = 4;
    private static final int SEX_INDEX = 5;

    public static void main(String[] args) {
        while (true) {
            try {
                writeUserDataToFile(getUserData());
            } catch (SizeInputDataException | WrongDataTypeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Нажмите 'e' и клавишу 'ENTER' для выхода, либо клавишу 'ENTER' для продолжения");
            String decision = scanner.nextLine();
            if(((decision).equalsIgnoreCase("e")) || (decision.equalsIgnoreCase("е"))){
                break;
            }
        }
    }

    private static void writeUserDataToFile(String[] dataArray) throws IOException {
        try (FileWriter fileWriter = new FileWriter(dataArray[SURNAME_INDEX], true)) {
            fileWriter.write(
                    "<" + dataArray[SURNAME_INDEX] + ">" +
                            "<" + dataArray[NAME_INDEX] + ">" +
                            "<" + dataArray[MIDDLE_NAME_INDEX] + ">" +
                            "<" + dataArray[BIRTHDAY_INDEX] + ">" +
                            "<" + dataArray[PHONE_INDEX] + ">" +
                            "<" + dataArray[SEX_INDEX].toLowerCase() + ">\n");

        }
    }

    public static String[] getUserData() throws SizeInputDataException, WrongDataTypeException {
        String userData, surname, name, middle_name, birthday;
        int phone_number;
        char sex;
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Введите через пробел:
                Фамилию
                Имя
                Отчество
                Дату рождения (dd.mm.yyyy)
                Номер телефона (целое беззнаковое число без форматирования)
                Пол (m/f)
                :\s""");
        userData = scanner.nextLine();
        String[] dataArray = userData.split(" ");

        checkRequiredSize(dataArray);

        checkSurnameFormat(dataArray[SURNAME_INDEX]);

        checkNameFormat(dataArray[NAME_INDEX]);

        checkMiddleNameFormat(dataArray[MIDDLE_NAME_INDEX]);

        checkBirthdayFormat(dataArray[BIRTHDAY_INDEX]);

        checkPhoneFormat(dataArray[PHONE_INDEX]);

        checkSexFormat(dataArray[SEX_INDEX]);

        System.out.println(userData);
        return dataArray;
    }

    private static void checkSurnameFormat(String data) throws WrongDataTypeException {
        if (!data.matches("^([А-Я][а-яё]{1,30}([-][А-ЯЁ][а-яё]{1,30})?|[A-Z][a-z]{1,30}([-][A-Z][a-z]{1,30})?)$")) {
            throw new WrongDataTypeException("Неверный формат фамилии");
        }
    }

    private static void checkNameFormat(String data) throws WrongDataTypeException {
        if (!data.matches("^([А-Я][а-яё]{1,30}([-][А-ЯЁ][а-яё]{1,30})?|[A-Z][a-z]{1,30}([-][A-Z][a-z]{1,30})?)$")) {
            throw new WrongDataTypeException("Неверный формат имени");
        }
    }

    private static void checkMiddleNameFormat(String data) throws WrongDataTypeException {
        if (!data.matches("^([А-Я][а-яё]{1,30}([-][А-ЯЁ][а-яё]{1,30})?|[A-Z][a-z]{1,30}([-][A-Z][a-z]{1,30})?)$")) {
            throw new WrongDataTypeException("Неверный формат отчества");
        }
    }

    private static void checkPhoneFormat(String data) throws WrongDataTypeException {
        try {
            long result = Long.parseUnsignedLong(data);

        } catch (Exception e) {
            throw new WrongDataTypeException("Неверный формат номера телефона");
        }
    }

    private static void checkSexFormat(String data) throws WrongDataTypeException {
        if (!("f".equalsIgnoreCase(data) || "m".equalsIgnoreCase(data))) {
            throw new WrongDataTypeException("Неверный формат записи пола");
        }

    }

    private static void checkRequiredSize(String[] dataArray) throws SizeInputDataException {
        if (dataArray.length < 6) {
            throw new SizeInputDataException("Вы ввели меньше данных, чем требуется");
        } else if (dataArray.length > 6) {
            throw new SizeInputDataException("Вы ввели больше данных, чем требуется");
        }
    }

    private static void checkBirthdayFormat(String data) throws WrongDataTypeException {
        Matcher matcher;
        final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.]((19|20)\\d\\d)";
        Pattern pattern = Pattern.compile(DATE_PATTERN);

        matcher = pattern.matcher(data);

        if (matcher.matches()) {
            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month.equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month.equals("06") ||
                                month.equals("09"))) {
                    throw new WrongDataTypeException("only 1,3,5,7,8,10,12 has 31 days");//
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            throw new WrongDataTypeException("В феврале нет 30 и 31 числа");
                        }
                    } else {

                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            throw new WrongDataTypeException("В феврале в этом месяце 28 дней");
                        }
                    }
                }
            } else {
                throw new WrongDataTypeException("Ошибка в дате рождения");
            }
        } else {
            throw new WrongDataTypeException("Ошибка в дате рождения!");
        }
    }
}
