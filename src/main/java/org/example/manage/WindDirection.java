package org.example.manage;

//Класс описания ветра
public class WindDirection {

    //Метод, задающий переменную direct (направление ветра), c логической обработкой
    public static String directionText(double degree) {
        String[] direct = {"С", "СВ",
                "В", "ЮВ",
                "Ю", "ЮЗ",
                "З", "СЗ", "С"};

        return direct[(int) Math.round((degree % 360) / 45)];
    }

    //Метод, задающий переменную direct (направление ветра) в виде стрелок, с логической обработкой
    public static String directionSymb(double degree) {
        String[] direct = {"\u2193", "\u2199",
                "\u2190", "\u2196",
                "\u2191", "\u2197",
                "\u2192", "\u2198", "\u2193"};

        return direct[(int) Math.round((degree % 360) / 45)];
    }

}
