package com.kyungminum;

public class MemoryManager {
    static private final int NULL = -1;
    private int[] memory; //memory we manage
    private int top;
    private int freestart; //start of free list

    public MemoryManager(int[] initalMemory){
        this.memory = initalMemory;
        this.top = memory.length;
        this.memory[0] = memory.length;
        this.memory[1] = -1;
        this.freestart = 0;
    }

    public int push(int requestSize){
        int oldtop = top;               //save the value of the previous top
        this.top -= (requestSize+1);
        if (top<freestart)
            throw new StackOverflowError();
        memory[top] = oldtop;
        memory[freestart]-=(requestSize+1);

        return top+1;
    }


    public void pop(){
        memory[freestart]+=(memory[top]-top);
        top = memory[top];
        memory[top]=0;
    }

    public int allocate(int requestSize){
        int size = requestSize+1;
        int p = freestart;
        int lag = NULL;
        while(p != NULL && memory[p]<size){
            lag = p;
            p = memory[p+1];
        }
        if (p == NULL || p > top-1)
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
        int addr = address-1; //real start of the block

        int p = freestart;
        int lag = NULL;
        while(p != NULL && p < addr){
            lag = p;
            p = memory[p+1];
        }
        if (addr + memory[addr] == p){
            memory[addr] += memory[p]; //add its size to ours
            p = memory[p+1];
        }
        if (lag == NULL){ //ours will be first free
            freestart = addr;
            memory[addr+1] = p;
        }
        else if (lag+memory[lag] == addr){ //block before is adjacent to ours
            memory[lag] += memory[addr];
            memory[lag+1] = p;
        }
        else { //neither: just a simple insertion
            memory[lag+1] = addr;
            memory[addr+1] = p;
        }
    }

    public void display(){
        for (int i = 0; i < memory.length; i++) {
            System.out.print(memory[i]+" ");
        }
        System.out.println();
    }

    public void display2(){
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