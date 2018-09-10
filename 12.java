package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        Map<Integer, Node> nodes = new HashMap<>();

        String input = "0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5";

        String[] lines = input.split("\n");
        int lineCount = 0;

        for (String line : lines){

            int idx = Integer.valueOf(lines[lineCount++].split(" ")[0]);
            Node left = nodes.get(idx);
            if(left == null) left = new Node(idx);
            String[] split = line.split(" ");

            for(int i = 2; i < split.length; i++){
                String son = split[i].replace(',',' ').trim();

                int id = Integer.valueOf(son);

                Node actual = nodes.get(id);
                if(actual == null){
                    actual = new Node(id);
                }

                actual.connected.add(left);
                left.connected.add(actual);
                nodes.put(id, actual);
            }

            nodes.put(left.id, left);
        }

        dfs(nodes);
        System.out.println();
    }

    static void dfs(Map<Integer, Node> graph){
        Map<Integer, Boolean> visited = new HashMap<>(graph.size());
        int depth = dfsRecur(graph.get(0), visited, new AtomicInteger(0));
        String.valueOf(depth);
    }

    static int dfsRecur(Node node, Map<Integer, Boolean> visited, AtomicInteger count){
        visited.put(node.id, true);
        count.incrementAndGet();

        for (Node actual : node.connected){
            if(!Boolean.TRUE.equals(visited.get(actual.id))){
                dfsRecur(actual, visited, count);
            }
        }

        return count.get();
    }

    static class Node {
        int id;
        Set<Node> connected = new HashSet<>();

        Node(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';


        }
    }

}
