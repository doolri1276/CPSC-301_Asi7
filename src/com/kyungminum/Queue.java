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
}
