package Lab_3;
import Lab_3.Que.ArrayQueue;
import Lab_3.Que.CircularQueue;
import Lab_3.Que.Interface.Queue;
import Lab_3.Que.LinkedQueue;
import Lab_3.Stack.ArrayStack;
import Lab_3.Stack.DynamicArrayStack;
import Lab_3.Stack.Interface.Stack;
import Lab_3.Stack.LinkedStack;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<String> stack = null;
        Queue<String> queue = null;

        while (true) {
            System.out.println("Choose a data structure:");
            System.out.println("1. Stack");
            System.out.println("2. Queue");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    stack = selectStackImplementation(scanner);
                    performStackOperations(scanner, stack);
                    break;
                case 2:
                    queue = selectQueueImplementation(scanner);
                    performQueueOperations(scanner, queue);
                    break;
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Stack<String> selectStackImplementation(Scanner scanner) {
        System.out.println("Select Stack Implementation:");
        System.out.println("1. ArrayStack");
        System.out.println("2. LinkedStack");
        System.out.println("3. DynamicArrayStack");

        int stackChoice = scanner.nextInt();
        switch (stackChoice) {
            case 1:
                return new ArrayStack<>();
            case 2:
                return new LinkedStack<>();
            case 3:
                return new DynamicArrayStack<>();
            default:
                System.out.println("Invalid choice. Using ArrayStack.");
                return new ArrayStack<>();
        }
    }

    private static void performStackOperations(Scanner scanner, Stack<String> stack) {
        while (true) {
            System.out.println("\nChoose Stack Operation:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check if Empty");
            System.out.println("5. Return to Main Menu");

            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (operation) {
                case 1:
                    System.out.println("Enter element to push:");
                    String element = scanner.nextLine();
                    stack.push(element);
                    break;
                case 2:
                    if (!stack.isEmpty()) {
                        System.out.println("Popped element: " + stack.pop());
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;
                case 3:
                    if (!stack.isEmpty()) {
                        System.out.println("Top element: " + stack.peek());
                    } else {
                        System.out.println("Stack is empty.");
                    }
                    break;
                case 4:
                    System.out.println("Stack is empty: " + stack.isEmpty());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid operation. Please try again.");
            }
        }
    }


    private static Queue<String> selectQueueImplementation(Scanner scanner) {
        System.out.println("Select Queue Implementation:");
        System.out.println("1. ArrayQueue");
        System.out.println("2. LinkedQueue");
        System.out.println("3. CircularQueue");

        int queueChoice = scanner.nextInt();
        switch (queueChoice) {
            case 1:
                return new ArrayQueue<>();
            case 2:
                return new LinkedQueue<>();
            case 3:
                return new CircularQueue<>();
            default:
                System.out.println("Invalid choice. Using ArrayQueue.");
                return new ArrayQueue<>();
        }
    }

    private static void performQueueOperations(Scanner scanner, Queue<String> queue) {
        while (true) {
            System.out.println("\nChoose Queue Operation:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Check if Empty");
            System.out.println("5. Return to Main Menu");

            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (operation) {
                case 1:
                    System.out.println("Enter element to enqueue:");
                    String element = scanner.nextLine();
                    queue.enqueue(element);
                    break;
                case 2:
                    if (!queue.isEmpty()) {
                        System.out.println("Dequeued element: " + queue.dequeue());
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;
                case 3:
                    if (!queue.isEmpty()) {
                        System.out.println("Front element: " + queue.peek());
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;
                case 4:
                    System.out.println("Queue is empty: " + queue.isEmpty());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid operation. Please try again.");
            }
        }
    }
}
