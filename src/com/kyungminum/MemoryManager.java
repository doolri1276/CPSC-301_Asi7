package com.kyungminum;

public class MemoryManager {
    static private final int NULL = -1;
    private int[] memory; //memory we manage
    private int top;
    private int freestart; //start of free list

    public MemoryManager(int[] initalMemory){
        this.memory = initalMemory;
        top = memory.length;
        memory[0] = memory.length;
        memory[1] = NULL;
        freestart = 0;
    }

    public int push(int requestSize){
        int oldtop = top;
        top -= (requestSize+1);
        if (top<0) throw new StackOverflowError();
        memory[top] = oldtop;
        return top+1;
    }

    public void pop(){
        top = memory[top];
    }

    public int allocate(int requestSize){
        int size = requestSize+1;
        int p = freestart;
        int lag = NULL;
        while(p != NULL && memory[p]<size){
            lag = p;
            p = memory[p+1];
        }
        if (p == NULL)
            throw new OutOfMemoryError();
        int nextFree = memory[p+1];
        int unused = memory[p]-size;
        if (unused > 1){
            nextFree = p + size;
            memory[nextFree] = unused;
            memory[nextFree+1] = memory[p+1];
            memory[p]= size;
        }
        if (lag == NULL) freestart = nextFree;
        else memory[lag+1] = nextFree;
        return p+1;
    }

    public void deallocate(int address){
        int addr = address-1;
        memory[addr+1] = freestart;
        freestart = addr;
    }
}
