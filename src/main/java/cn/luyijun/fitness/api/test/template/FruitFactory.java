package cn.luyijun.fitness.api.test.template;

import java.util.HashMap;

/**
 * Created by Jun on 2020/3/18 12:10.
 */
public class FruitFactory {

    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>(10);
        System.out.println(stringStringHashMap.size());
        System.out.println(stringStringHashMap.isEmpty());
        System.out.println(8 << 2);
        System.out.println(8 >> 2);
        System.out.println("==================");
        int n = 2;
        System.out.println(n>>>1);
        System.out.println(n>>>2);

    }

}
