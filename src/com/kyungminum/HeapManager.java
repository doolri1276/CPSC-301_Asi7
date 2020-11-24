package com.kyungminum;

public class HeapManager {
    static private final int NULL = -1;
    public int[] memory;
    private int freeStart;

    public HeapManager(int[] initialMemory){
        memory = initialMemory;
        memory[0] = memory.length;
        memory[1] = NULL;
        freeStart = 0;
    }

    public int allocate(int requestSize){
        int size = requestSize+1;
        int p=freeStart;
        int lag=NULL;
        while(p!=NULL && memory[p]<size){
            lag = p;
            p=memory[p+1];
        }
        if(p==NULL)
            throw new OutOfMemoryError();
        int nextFree = memory[p+1];

        int unused = memory[p]-size;
        if (unused>1){
            nextFree = p+size;
            memory[nextFree] = unused;
            memory[nextFree+1] = memory[p+1];
            memory[p] = size;
        }

        if(lag==NULL) freeStart = nextFree;
        else memory[lag +1] = nextFree;
        return p+1;
    }

    public void deallocate(int address){
        int addr = address-1;
        memory[addr+1] = freeStart;
        freeStart = addr;
    }
}
