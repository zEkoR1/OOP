package Lab_3.Stack;

import Lab_3.Stack.Interface.Stack;

public class LinkedStack<E> implements Stack<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> top = null; // Вершина стека
    private int size = 0;       // Размер стека

    @Override
    public void push(E item) {
        if (size == 5) {
            System.out.println("Стек переполнен. Невозможно добавить элемент.");
            return;
        }
        Node<E> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            System.out.println("Стек пуст. Нет элементов для удаления.");
            return null;
        }
        E item = top.data;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            System.out.println("Стек пуст.");
            return null;
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return size;
    }
}
