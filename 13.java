package com.company;

import java.util.*;

public class Main {

    static List<Layer> firewall = new LinkedList<>();

    public static void main(String[] args) {
        String input = "0: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4";

        String[] lines = input.split("\n");


        for (String line : lines){
            String[] current = line.split(":");

            firewall.add(new Layer(Integer.valueOf(current[0]), Integer.valueOf(current[1].trim())));
        }

        int finalScore = 0;

        for (int i = 0; i < firewall.size(); i++) {
            Layer layer = firewall.get(i);

            int lastScanIdx = (layer.idx % (layer.depth * 2 - 2));

            if(lastScanIdx == 0){
                finalScore += layer.depth * layer.idx;
            }
        }

        System.out.println(finalScore);
    }

    static class Layer{
        int idx;
        int depth;

        Layer(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Layer{" +
                    "idx=" + idx +
                    ", depth=" + depth +
                    '}';
        }
    }

}
