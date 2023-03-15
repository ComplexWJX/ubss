package com.asiainfo.java.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForLoop
{

    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
//        Iterator<Integer> iterator = list.iterator();
//        for (;iterator.hasNext();)
//        {
//            System.out.println(iterator.next());
//        }
        int i = 0;
        boolean stop;
        for (;;) {
            i ++;
            stop = i == 40;
            System.out.println(i);
            if (stop) {
                break;
            }
        }
    }
}
