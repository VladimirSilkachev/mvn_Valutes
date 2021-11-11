package com.app;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String time;
        String code;


        Scanner sc = new Scanner(System.in);
        System.out.println("Для выхода напишите exit.");
        while (true) {
            StringBuilder url = new StringBuilder("https://www.cbr.ru/scripts/XML_daily.asp?date_req=");

            System.out.println("Введите дату. Для выбора самой актуальной даты оставьте поле пустым.");
            time = sc.nextLine();
            time = time.replaceAll("\\.","/").replaceAll("\\s+", "/");
            url.append(time);
            if (time.equals("exit")){ break;}

            System.out.println("Введите код валюты");
            code = sc.nextLine().toUpperCase(Locale.ROOT);

            if (code.equals("EXIT")){ break;}

            if (code.length() != 3) {
                System.out.println("Недопустимый код валюты");
                continue;
            }

            Parser x = new Parser(url.toString(), code);
            System.out.println(x.domParser());



        }
    }
}
