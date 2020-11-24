package com.kyungminum;

public class MemoryManager {
    static private final int NULL = -1;
    private int[] memory; //memory we manage
    public int top;
    private int freestart; //start of free list

    public MemoryManager(int[] initalMemory){
        this.memory = initalMemory;
        this.top = memory.length;           //where stack starts
        this.memory[0] = memory.length;     //initial blocks left
        this.memory[1] = -1;
        this.freestart = 0;                 //where heap starts
    }

    //장군
    public int push(int requestSize){
        int oldtop = top;                       //save the value of the previous top
        this.top -= (requestSize+1);            //updates top
        int highestHeap = findHeapPeak();       //find the highest point where heap is used up to
        if (top<0||top-1<highestHeap)           //checks if new block goes over array or into the heap
            throw new StackOverflowError();
        memory[top] = oldtop;                   //save oldTop value to new top index
        memory[highestHeap]-=(requestSize+1);   //update amount of blocks left

        return top+1;                           //return index of top
    }

    private int findHeapPeak(){                 //looks for the highest index where heap is used up to
        int p = freestart;
        int highest=p;
        while (p!=-1){                          //last block of heap is -1
            p = memory[p+1];
            highest=Math.max(p, highest);
        }
        return highest;
    }


    //쇼지
    public void pop(){
        int oldtop = top;                       //save the top index
        top = memory[top];                      //update top index
        memory[oldtop] = 0;                     //delete the memory saved
        memory[findHeapPeak()]+=top-oldtop;     //update amount of blocks left
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

        if (p == NULL || p+size > top-2)            //checks if heap goes over array or into stack
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