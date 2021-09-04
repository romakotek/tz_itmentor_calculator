package com.burabayev;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RomanNumbersConverter {

//    private String num1;
//    private String num2;
//
//    public RomeNumbersConverter(String num1, String num2) {
//        this.num1 = num1;
//        this.num2 = num2;
//    }
//    10*10 = 100;


    private HashMap<String, Integer> map = new HashMap<String, Integer>();

    RomanNumbersConverter() {
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XI", 11);
        map.put("XII", 12);
        map.put("XIII", 13);
        map.put("XIV", 14);
        map.put("XV", 15);
        map.put("XVI", 16);
        map.put("XVII", 17);
        map.put("XVIII", 18);
        map.put("XIX", 19);
        map.put("XX", 20);
        map.put("XXI", 21);
        map.put("XXII", 22);
        map.put("XXIII", 23);
        map.put("XXIV", 24);
        map.put("XXV", 25);
        map.put("XXVI", 26);
        map.put("XXVII", 27);
        map.put("XXVIII", 28);
        map.put("XXIX", 29);
        map.put("XXX", 30);
        map.put("XXXI", 31);
        map.put("XXXII", 32);
        map.put("XXXIII", 33);
        map.put("XXXIV", 34);
        map.put("XXXV", 35);
        map.put("XXXVI", 36);
        map.put("XXXVII", 37);
        map.put("XXXVIII", 38);
        map.put("XXXIX", 39);
        map.put("XL", 40);
        map.put("XLI", 41);
        map.put("XLII", 42);
        map.put("XLIII", 43);
        map.put("XLIV", 44);
        map.put("XLV", 45);
        map.put("XLVI", 46);
        map.put("XLVII", 47);
        map.put("XLVIII", 48);
        map.put("XLIX", 49);
        map.put("L", 50);
        map.put("LI", 51);
        map.put("LII", 52);
        map.put("LIII", 53);
        map.put("LIV", 54);
        map.put("LV", 55);
        map.put("LVI", 56);
        map.put("LVII", 57);
        map.put("LVIII", 58);
        map.put("LIX", 59);
        map.put("LX", 60);
        map.put("LXI", 61);
        map.put("LXII", 62);
        map.put("LXIII", 63);
        map.put("LXIV", 64);
        map.put("LXV", 65);
        map.put("LXVI", 66);
        map.put("LXVII", 67);
        map.put("LXVIII", 68);
        map.put("LXIX", 69);
        map.put("LXX", 70);
        map.put("LXXI", 71);
        map.put("LXXII", 72);
        map.put("LXXIII", 73);
        map.put("LXXIV", 74);
        map.put("LXXV", 75);
        map.put("LXXVI", 76);
        map.put("LXXVII", 77);
        map.put("LXXVIII", 78);
        map.put("LXXIX", 79);
        map.put("LXXX", 80);
        map.put("LXXXI", 81);
        map.put("LXXXII", 82);
        map.put("LXXXIII", 83);
        map.put("LXXXIV", 84);
        map.put("LXXXV", 85);
        map.put("LXXXVI", 86);
        map.put("LXXXVII", 87);
        map.put("LXXXVIII", 88);
        map.put("LXXXIX", 89);
        map.put("XC", 90);
        map.put("XCI", 91);
        map.put("XCII", 92);
        map.put("XCIII", 93);
        map.put("XCIV", 94);
        map.put("XCV", 95);
        map.put("XCVI", 96);
        map.put("XCVII", 97);
        map.put("XCVIII", 98);
        map.put("XCIX", 99);
        map.put("C", 100);
    }


    int convertToArabicNumber(String romanNumber) throws Exception {
        Integer res = map.get(romanNumber);
        if(res == null) {
            throw new Exception("Not valid ARABIC number");
        }
        return res;
    }

    String convertToRomanNumber(int arabicNumber) throws Exception {
        for(Map.Entry<String, Integer> item : map.entrySet()){

            if(item.getValue() == arabicNumber) {
                return item.getKey();
            }
        }
        throw new Exception("Not valid ROMAN number");
    }
}

