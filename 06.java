package com.company;

import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String input = "5\t1\t10\t0\t1\t7\t13\t14\t3\t12\t8\t10\t7\t12\t0\t6";

        String[] list = input.split("\t");
        Set<String> hash = new HashSet<>();

        List<Integer> newList = new LinkedList<>();
        for(String vl : list) {
            newList.add(Integer.valueOf(vl));
        }

        int size = list.length;
        boolean stop;
        int iteractions = 0;

        do {
            int max = Collections.max(newList);
            int idx = newList.indexOf(max);

            newList.set(idx, 0);
            if(idx + 1 >= size) {
                idx = 0;
            }else{
                idx++;
            }

            while(max > 0){
                newList.set(idx, newList.get(idx) + 1);

                if(idx + 1 >= size) {
                    idx = 0;
                }
                else {
                    idx++;
                }
                max--;
            }

            iteractions++;
            stop = hash.add(newList.toString());
        }while (stop);

        System.out.print(iteractions);
    }
}
