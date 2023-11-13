package Lab_3.Stack;
import Lab_3.Stack.Interface.Stack;

import java.util.ArrayList;

public class DynamicArrayStack<E> implements Stack<E> {
    private ArrayList<E> stack;

    public DynamicArrayStack() {
        stack = new ArrayList<>();
    }

    @Override
    public void push(E item) {
        stack.add(item);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст.");
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст.");
        }
        return stack.get(stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
