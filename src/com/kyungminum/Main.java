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

    }
}
