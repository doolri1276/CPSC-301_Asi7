package com.kyungminum;

public class Main {

    public static void main(String[] args) {
        MemoryManager m = new MemoryManager(new int[10]);
        m.push(4);
        m.allocate(2);
        m.display();
//        m.allocate(2);
//        m.display();
        m.pop();
        m.display();

        Queue<Integer> queue = new Queue<Integer>();
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);
        queue.print();
        queue.remove();
        queue.remove();
        queue.print();

    }
}
