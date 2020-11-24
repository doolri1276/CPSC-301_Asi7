package com.kyungminum;

public class StackManager {
    private int[] memory;
    private int top;

    public StackManager(int[] memory) {
        this.memory = memory;
        top=memory.length;
    }

    public int push(int requestSize){
        int oldTop=top;
        top -= (requestSize +1);
        if(top<0) throw new StackOverflowError();
        memory[top] = oldTop;
        return top+1;
    }

    public void pop(){
        top = memory[top];
    }
}
