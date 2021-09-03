package com.burabayev;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RomeNumbersConverter {

//    private String num1;
//    private String num2;
//
//    public RomeNumbersConverter(String num1, String num2) {
//        this.num1 = num1;
//        this.num2 = num2;
//    }
//    10*10 = 100;


    private HashMap<String, Integer> map = new HashMap();

    RomeNumbersConverter() {
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
    }


    int convertToArabNumber(String romeNumber) throws Exception {
        Integer res = map.get(romeNumber);
        if(res == null) {
            throw new Exception("Not valid rome number");
        }
        return res;
    }

    String convertToRomeNumber(int arabNumber) throws Exception {
        for(Map.Entry<String, Integer> item : map.entrySet()){

            if(item.getValue() == arabNumber) {
                return item.getKey();
            }
        }
        throw new Exception("Not valid arab number");
    }
}

