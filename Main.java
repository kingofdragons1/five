import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // считываем дату из консоли и преобразует ее в объект Date
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        System.out.println("Введите дату в формате dd.MM.yyyy:");
        Date date = format.parse(scanner.nextLine());

        // создаем обьект Calendar, для выполнения различных операций с датой
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // увеличиваем дату на 45 дней
        calendar.add(Calendar.DAY_OF_MONTH, 45);
        System.out.println("Дата после увеличения на 45 дней: " + format.format(calendar.getTime()));

        // сдвигаем дату на начало года
        calendar.setTime(date); // возвращаемся к исходной дате
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        System.out.println("Дата после сдвига на начало года: " + format.format(calendar.getTime()));

        // увеличиваем дату на 10 рабочих дней
        calendar.setTime(date);
        int addedDays = 0;
        while (addedDays < 10) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++addedDays;
            }
        }
        System.out.println("Дата после увеличения на 10 рабочих дней: " + format.format(calendar.getTime()));

        // вводим вторую дату и сохраняем в другой переменной класса Date
        System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
        Date secondDate = format.parse(scanner.nextLine());

        // считаем количество рабочих дней между первой и второй датой
        calendar.setTime(date);
        int workingDays = 0;
        if (secondDate.before(date)) {
            Date temp = date;
            date = secondDate;
            secondDate = temp;
        }

        while (date.before(secondDate)) { // вычисляем количество рабочих дней,
            calendar.setTime(date);
            // условие для корректной обрабатки случаев, когда вторая дата раньше первой
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workingDays;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date = calendar.getTime();
        }

        System.out.println("Количество рабочих дней между введенными датами: " + workingDays);
    }
}
