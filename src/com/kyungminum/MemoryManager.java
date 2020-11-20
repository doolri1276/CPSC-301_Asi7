package com.kyungminum;

public class MemoryManager {
    static private final int NULL = -1;
    private int[] memeory;
    private int top;
    private int freestart;

    public MemoryManager(int[] initalMemory){
        this.memeory = initalMemory;
        top = memeory.length;
        memeory[0] = memeory.length;
        memeory[1] = NULL;
        freestart = 0;
    }

    public int push(int requestSize){
        int oldtop = top;
        top -= (requestSize+1);
        if (top<0) throw new StackOverflowError();
        memeory[top] = oldtop;
        return top+1;
    }

    public void pop(){
        top = memeory[top];
    }

    public int allocate(int requestSize){
        int size = requestSize+1;
        int p = freestart;
        int lag = NULL;
        while(p != NULL && memeory[p]<size){
            lag = p;
            p = memeory[p+1];
        }
        if (p == NULL)
            throw new OutOfMemoryError();
        int nextFree = memeory[p+1];
        int unused = memeory[p]-size;
        if (unused > 1){
            nextFree = p + size;
            memeory[nextFree] = unused;
            memeory[nextFree+1] = memeory[p+1];
            memeory[p]= size;
        }
        if (lag == NULL) freestart = nextFree;
        else memeory[lag+1] = nextFree;
        return p+1;
    }

    public void deallocate(int address){
        int addr = address-1;
        memeory[addr+1] = freestart;
        freestart = addr;
    }

}
