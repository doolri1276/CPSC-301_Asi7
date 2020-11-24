package com.kyungminum;

public class MemoryManager {
    static private final int NULL = -1;
    private int[] memory; //memory we manage
    public int top;
    private int freestart; //start of free list

    public MemoryManager(int[] initalMemory){
        this.memory = initalMemory;
        this.top = memory.length;
        this.memory[0] = memory.length;
        this.memory[1] = -1;
        this.freestart = 0;
    }

    //장군
    public int push(int requestSize){
        int oldtop = top;               //save the value of the previous top
        this.top -= (requestSize+1);
        int highestHeap = findHeapPeak();
        if (top<0||top-1<highestHeap)
            throw new StackOverflowError();
        memory[top] = oldtop;
        memory[highestHeap]-=(requestSize+1);

        return top+1;
    }

    private int findHeapPeak(){
        int p = freestart;
        int highest=p;
        while (p!=-1){
            p = memory[p+1];
            highest=Math.max(p, highest);
        }
        return highest;
    }


    //쇼지
    public void pop(){
        int oldtop = top;
        top = memory[top];
        memory[oldtop] = 0;
        int peak = findHeapPeak();
        memory[findHeapPeak()]+=top-oldtop;
    }

    //경민
    public int allocate(int requestSize){
        int size = requestSize+1;
        int p = freestart;
        int lag = NULL;
        while(p != NULL && memory[p]<size){
            lag = p;
            p = memory[p+1];
        }

        if (p == NULL || p+size > top-2)
            throw new OutOfMemoryError();
        int nextFree = memory[p+1];
        int unused = memory[p]-size;

        if (unused >1){
            nextFree = p + size;
            memory[nextFree] = unused;
            memory[nextFree+1] = memory[p+1];
            memory[p]= size;
            memory[p+1]=0;
        }

        memory[p+1]=0;

        if (lag == NULL) freestart = nextFree;
        else memory[lag+1] = nextFree;
        return p+1;
    }

    public void deallocate(int address){
        int addr = address-1; //real start of the block
        memory[addr+1] = freestart;
        freestart = addr;

    }

    public void display(){
        System.out.println("==========");
        System.out.println("TOP : "+top);
        System.out.println("FREE : "+freestart);
        System.out.println("==========");
        for (int i = memory.length-1; i >=0; i--) {
            System.out.println(i+": "+memory[i]);
        }
        System.out.println();
    }
}