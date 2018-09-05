package com.company;

import java.util.*;

public class Main {

    static Map<String, Leaf> set = new TreeMap<>();

    public static void main(String[] args) {
        String input = "pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)\n";

        String[] lines = input.split("\n");

        for (String line : lines){
            String[] split = line.split(" ");
            //int weight = Integer.valueOf(split[1].subSequence(split[1].indexOf("(") + 1, split[1].indexOf(")")).toString());

            Leaf parent = createOrRetrieve(split[0]);

            if(split.length > 2){
                for(int i = 3; i < split.length; i++){
                    String son = split[i].replace(',',' ').trim();
                    Leaf sonLeaf = createOrRetrieveParent(son, parent);
                    parent.addNode(sonLeaf);
                }
                set.put(parent.id, parent);
            }
        }

        System.out.println(findRoot());
    }

    static String findRoot(){
        Leaf any = new ArrayList<>(set.values()).get(0);

        do {
            any = any.parent;
        }while(any.parent != null);

        return any.toString();
    }

    static Leaf createOrRetrieve(String id){
        Leaf leaf = set.get(id);
        if(leaf != null) return leaf;

        leaf = new Leaf(0, id, null);
        set.put(id, leaf);
        return leaf;
    }

    static Leaf createOrRetrieveParent(String id, Leaf parent){
        Leaf leaf = set.get(id);

        if(leaf != null){
            leaf.addParent(parent);
        }else{
            leaf = new Leaf(0, id, parent);
        }

        set.put(id, leaf);
        return leaf;
    }

    static class Leaf {
        int weight;
        String id;
        Leaf parent;
        List<Leaf> nodes;

        Leaf(int weight, String id, Leaf parent) {
            this.weight = weight;
            this.id = id;
            this.parent = parent;
            this.nodes = new ArrayList<>();
        }

        Leaf(int weight, String id, Leaf parent, List<Leaf> nodes) {
            this.weight = weight;
            this.id = id;
            this.parent = parent;
            this.nodes = nodes;
        }

        void addNode(Leaf node){
            nodes.add(node);
        }

        void addParent(Leaf parent){
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Leaf leaf = (Leaf) o;
            return Objects.equals(id, leaf.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Leaf{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
