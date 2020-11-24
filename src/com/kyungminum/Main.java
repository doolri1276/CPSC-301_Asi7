package com.kyungminum;

public class Main {

    public static void main(String[] args) {
        MemoryManager m = new MemoryManager(new int[20]);
        int p1 = m.push(4);
        int p2 = m.push(4);
        int h3 = m.allocate(4);
        //int p4 = m.allocate(4);
        m.display2();


/*        m.display();
        m.deallocate(p3);
        m.deallocate(p4);
        int p5 = m.allocate(5);
        m.display();
*/

//        MemoryManager m1 = new MemoryManager(new int[10]);
//        int p1 = m.push(4);
//        int p2 = m.push(4);
//        m.display();

//        m.pop();
//        int p3 = m.allocate(4);
//        int p4 = m.allocate( 1);

//       m.display2();
//        m.deallocate(p3);
//        m.deallocate(p4);
//        int p5 = m.allocate(5);
//        m.display();



//        Queue<Integer> queue = new Queue<>();
//
//        queue.print();
//
//        queue.add(1);
//        queue.add(2);
//
//        queue.print();
//
//        System.out.println(queue.remove());
//
//        queue.print();
//
//        queue.add(3);
//        queue.add(4);
//
//        queue.print();
//
//        System.out.println(queue.remove());
//        queue.print();

    }
}
