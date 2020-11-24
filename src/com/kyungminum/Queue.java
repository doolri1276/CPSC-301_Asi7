package com.kyungminum;


public class Queue<T> implements Worklist<T> {
    private Node<T> top = null;

    @Override
    public void add(T item) {
        top = new Node<>(item, top);
    }

    @Override
    public boolean hasMore() {
        return top!=null;
    }

    @Override
    public T remove() {
        Node<T> cur=top;
        while(cur.getNextNode().getNextNode()!=null){
            cur=cur.getNextNode();
        }
        T data = cur.getNextNode().getData();
        cur.setNextNode(null);
        return data;
    }

    public void print(){
        if(top==null){
            System.out.println("Queue is empty");
        }else{
            Node<T> cur=top;
            while(cur!=null){
                System.out.println(cur);
                cur=cur.getNextNode();
            }
        }
        System.out.println();
    }


}
