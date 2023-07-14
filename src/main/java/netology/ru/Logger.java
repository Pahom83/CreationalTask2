package netology.ru;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    protected int num = 1;

    public void log(String msg) {
        System.out.println("[" + localTime() + " " + num++ + "] " + msg);
    }


    // В этом поле храним ссылку на тот
    // единственный объект этого класса
    // который будем отдавать пользователям
    private static Logger logger;

    // Запрещаем пользователям пользоваться
    // конструктором нашего класса
    private Logger() {
    }

    // Пользователи, которым нужен объект
    // нашего класса, получают всегда один
    // и тот же объект, который мы храним
    // в приватном статичном поле, которое
    // мы заполняем в этом методе если оно
    // до того не было заполнено
    public static Logger getInstance() {
        return logger == null ? new Logger() : logger;
    }

    private static String localTime(){
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss");
        stringBuilder.append(LocalDateTime.now().format(formatter));
        return stringBuilder.toString();
    }
}