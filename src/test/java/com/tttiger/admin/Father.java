package com.tttiger.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/09 12:49
 * @Description
 */
public class Father {


    static {
        System.out.println("Father static");
    }
    private static Temp temp = new Temp("father");


    public Father() {
        System.out.println("Father constructor");
    }

    {
        System.out.println("Father constructor block");
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

    }
}


class Son extends Father {
    static {
        System.out.println("son static");
    }
    private static Temp temp = new Temp("Son");

    public Son() {
        System.out.println("son constructor");
    }

    {
        System.out.println("son constructor block");
    }
}


class Temp{
    public Temp(String name) {
        System.out.println("Temp constructor");
    }
}