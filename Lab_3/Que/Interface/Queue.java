package Lab_3.Que.Interface;


public interface Queue<E> {
    void enqueue(E item);
    E dequeue();
    E peek();
    boolean isEmpty();
    int size();
}