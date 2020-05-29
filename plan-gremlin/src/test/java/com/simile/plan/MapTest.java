package com.simile.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by yitao on 2020/05/25
 */
public class MapTest {

    public static void main(String[] args) {
        List<String> array = new ArrayList<>();
        array.add("1");
        array.add("2");
        array.add("3");
        array.stream().forEach(item->{
            if(item.equals("2")){
                return;
            }
            System.out.println(item);
        });
//        for (int i = 0; i < 5; i++) {
//            Map<String,String> map = getMap();
//            Map<String,String> map2 = getMap2();
//            System.out.println(map.toString().equals(map2.toString()));
//            System.out.println(map.toString());
//            System.out.println(map2.toString());
//        }
    }

    public static Map getMap(){
        HashMap<String,String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i+"",i+"");
        }
        return map;
    }

    public static Map getMap2(){
        HashMap<String,String> map = new HashMap<>();
        for (int i = 99; i >=0; i--) {
            map.put(i+"",i+"");
        }
        return map;
    }
}
