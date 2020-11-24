package com.kyungminum;

public interface Worklist<T> {
    void add(T item);
    boolean hasMore();
    T remove();
}
