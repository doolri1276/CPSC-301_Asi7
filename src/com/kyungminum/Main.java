package com.kyungminum;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        queue.print();

        queue.add(1);
        queue.add(2);

        queue.print();

        System.out.println(queue.remove());

        queue.print();

        queue.add(3);
        queue.add(4);

        queue.print();

        System.out.println(queue.remove());
        queue.print();
    }
}
