package com.kyungminum;

public class Main {

    public static void main(String[] args) {
        MemoryManager m = new MemoryManager(new int[20]);
        int p1 = m.push(4);
        int p2 = m.push(4);
        int p3 = m.allocate(4);
        //int p4 = m.allocate(4);
        m.display();


/*        m.display();
        m.deallocate(p3);
        m.deallocate(p4);
        int p5 = m.allocate(5);
        m.display();
*/

    }
}
