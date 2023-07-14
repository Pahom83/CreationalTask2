package netology.ru;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String hello = "Добро пожаловать!";
    public static String inputData =
            "Введите 2 числа (целые, положительные):\n1. Размер формируемого списка.\n2. Максимальное значение чисел в списке.";
    public static int sizeList;
    public static int maxIntInList;
    public static int filterInt;
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Запуск программы");
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        logger.log("Просим пользователя ввести данные.");
        System.out.println(hello);
        System.out.println(inputData);
        String inputString;
        for (int i = 0; i < 2; i++){
            if (i == 0){
                System.out.print("Введите размер списка: ");
                inputString = input.nextLine();
                sizeList = parseInt(inputString);
                if (sizeList == -1){
                    System.out.println("Введено отрицательное число! Повторите ввод.");
                    i--;
                } else if (sizeList == -2){
                    System.out.println("Неверный ввод. Повторите!");
                    i--;
                } else {
                    logger.log("Размер списка выбран равным " + sizeList + ".");
                }
            } else {
                System.out.print("Введите верхнюю границу для значений: ");
                inputString = input.nextLine();
                maxIntInList = parseInt(inputString);
                if (maxIntInList == -1){
                    System.out.println("Введено отрицательное число! Повторите ввод.");
                    i--;
                } else if (maxIntInList == -2){
                    System.out.println("Неверный ввод! Повторите.");
                    i--;
                } else {
                    logger.log("Максимальный элемент в списке равен " + maxIntInList + ".");
                }
            }
        }
        logger.log("Создаем и наполняем список.");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < sizeList; i++){
            arrayList.add(random.nextInt(maxIntInList));
        }
        System.out.println("Получившийся список: " + arrayList);
        System.out.println("Список необходимо пропустить через фильтр, фильтр отбросит все значения ниже введенного значения.");
        System.out.print("Введите число от 0 до " + maxIntInList + ": ");
        while (true){
            inputString = input.nextLine();
            filterInt = parseInt(inputString);
            if (filterInt == -1){
                System.out.println("Введено отрицательное число! Повторите ввод.");
            } else if (filterInt == -2){
                System.out.println("Неверный ввод. Повторите!");
            } else if (filterInt > maxIntInList){
                logger.log("Введенное число слишком велико. Просим повторить ввод.");
                System.out.println("Введенное число слишком велико. Введите число от 0 до " + maxIntInList + ".");
            } else {
                logger.log("Размер списка выбран равным " + filterInt + ".");
                break;
            }
        }
        Filter filter = new Filter(filterInt);
        ArrayList<Integer> filteredArrayList = new ArrayList<>(filter.filterOut(arrayList));
        logger.log("Выводим итоговый список.");
        System.out.println("Список после фильтрации: " + filteredArrayList + ".");
        System.out.println("В итоговом списке " + filteredArrayList.size() + " элементов.");
        logger.log("Завершаем программу.");
    }

    private static int parseInt(String inputString) {
        Logger logger = Logger.getInstance();
        int result;
        try {
            result = Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            logger.log("Получено исключение \"NumberFormatException\" с сообщением: " + "'" + e.getLocalizedMessage() + "'");
            logger.log("Введены неверные данные, просим повторить ввод.");
            return -2;
        }
        if (result < 0){
            logger.log("Введено отрицательное число, что противоречит условиям. Просим повторить ввод.");
            return -1;
        }
        return result;
    }
}