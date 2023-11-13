package Lab_3.Stack;

import Lab_3.Stack.Interface.Stack;

public class ArrayStack<E> implements Stack<E> {
    private static final int CAPACITY = 5;
    private E[] array;
    private int top = -1;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        array = (E[]) new Object[CAPACITY];
    }

    @Override
    public void push(E item) {
        if (size() == CAPACITY) {
            System.out.println("Стек переполнен. Невозможно добавить элемент.");
            return;
        }
        array[++top] = item;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            System.out.println("Стек пуст. Нет элементов для удаления.");
            return null;
        }
        E item = array[top];
        array[top] = null; // Помощь сборщику мусора
        top--;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            System.out.println("Стек пуст.");
            return null;
        }
        return array[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }
}
