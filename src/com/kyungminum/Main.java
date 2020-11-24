package com.kyungminum;

public class Main {

    public static void main(String[] args) {
        MemoryManager m = new MemoryManager(new int[10]);
//        int p1 = m.push(4);
        //int p2 = m.push(4);
//        m.top = 9;
        int h3 = m.allocate(2);
        m.display2();
        m.allocate(2);
        m.display2();
        m.deallocate(1);
        m.display2();
        m.allocate(2);
//        m.allocate(2);
//        m.allocate(2);
//        m.deallocate(1);
//        m.deallocate(1);
//        m.allocate(2);
//        m.deallocate(0);
        //int p4 = m.allocate(4);
        m.display2();


/*        m.display();
        m.deallocate(p3);
        m.deallocate(p4);
        int p5 = m.allocate(5);
        m.display();
*/



    }
}
