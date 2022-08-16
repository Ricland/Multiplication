package com.company;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    static String reverse (String str) { //метод, принимающий строку и возвращающий соотвтетствующую строку с символами в обратном порядке
        String rev_str = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            rev_str += str.charAt(i);
        }
        return rev_str;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String a = in.nextLine(); //строка, содержащая множитель a
        String b = in.nextLine(); //строка, содержащая множитель b

        Instant start = Instant.now();
        String products [] = new String [b.length()]; //массив с произведениями
        int res, r = 0, z = 0, maxLen, vZero; //res — результат умножения чисел из соответствующих разрядов, r — значение прибавки
        String additive = "", result = "";

        for (int i = b.length() - 1; i >= 0; i--) { //вычисление произведений
            products [z] = "";
            for (int j = a.length() - 1; j >= 0; j--) {
                res = Character.getNumericValue(b.charAt(i)) * Character.getNumericValue(a.charAt(j)) + r;
                r = res > 9 ? res / 10 : 0;
                if (j != 0) {
                    products [z] += res % 10;
                } else {
                    products [z] += reverse(String.valueOf(res));
                }
            }
            r = 0;
            z++;
        }
        res = 0;
        z = 0;

        for (int i = 0; i < products.length; i++) { //выравнивание строк произведений
            products [i] = additive + products [i];
            additive += "0";
        }
        maxLen = products [products.length - 1].length();
        for (String i : products) {
            vZero = maxLen - i.length();
            for (int j = 0; j < vZero; j++) {
                products [z] += "0";
            }
            z++;
        }

        for (int i = 0; i < products [0].length(); i++) { //вычисление суммы произведений
            for (int j = 0; j < products.length; j++) {
                res += Character.getNumericValue(products [j].charAt(i));
            }
            res += r;
            r = res > 9 ? res / 10 : 0;
            if (i != products [0].length() - 1) {
                result += res % 10;
            } else {
                result += reverse(String.valueOf(res));
            }
            res = 0;
        }
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println(reverse(result)); //вывод результата умножения a на b
        System.out.println("Прошло времени, мс: " + elapsed);
    }
}
