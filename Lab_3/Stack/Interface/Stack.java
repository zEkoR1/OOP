package Lab_3.Stack.Interface;

public interface Stack<E> {
    void push(E item);  // Добавляет элемент в вершину стека
    E pop();            // Удаляет и возвращает элемент с вершины стека
    E peek();           // Возвращает элемент с вершины стека, не удаляя его
    boolean isEmpty();  // Проверяет, пуст ли стек
    int size();         // Возвращает размер стека
}
