package Lab_3.Que;

import Lab_3.Que.Interface.Queue;

public class CircularQueue<E> implements Queue<E> {
    private static final int CAPACITY = 5;
    private E[] queue;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public CircularQueue() {
        queue = (E[]) new Object[CAPACITY];
    }

    @Override
    public void enqueue(E item) {
        if (size == CAPACITY) {
            System.out.println("Очередь переполнена.");
            return;
        }
        rear = (rear + 1) % CAPACITY;
        queue[rear] = item;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь пуста.");
            return null;
        }
        E item = queue[front];
        queue[front] = null;
        front = (front + 1) % CAPACITY;
        size--;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            System.out.println("Очередь пуста.");
            return null;
        }
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}