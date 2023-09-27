import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> deque = new Deque<>();

        for (int N = Integer.parseInt(br.readLine()); N > 0; N--) {
            String[] command = br.readLine().split(" ");
            String method = command[0];
            Integer value = command.length == 2 ? Integer.parseInt(command[1]) : null;
            Integer frontValue, backValue;

            switch (method) {
                case "push_front":
                    deque.pushFront(value);
                    break;
                case "push_back":
                    deque.pushBack(value);
                    break;
                case "pop_front":
                    frontValue = deque.popFront();
                    bw.write((frontValue == null ? -1 : frontValue) + "\n");
                    break;
                case "pop_back":
                    backValue = deque.popBack();
                    bw.write((backValue == null ? -1 : backValue) + "\n");
                    break;
                case "size":
                    bw.write(deque.getSize() + "\n");
                    break;
                case "empty":
                    bw.write((deque.isEmpty() ? 1 : 0) + "\n");
                    break;
                case "front":
                    frontValue = deque.peekFront();
                    bw.write((frontValue == null ? -1 : frontValue) + "\n");
                    break;
                case "back":
                    backValue = deque.peekBack();
                    bw.write((backValue == null ? -1 : backValue) + "\n");
                    break;
            }
        }

        bw.close();
    }
}

class Deque<T> {

    private class Node {
        T value;
        Node prev;
        Node next;

        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public Deque() {}

    public int getSize() {
        return size;
    }

    public void pushFront(T value) {
        if (isEmpty()) {
            head = tail = new Node(value, null, null);
        } else {
            head = new Node(value, null, head);
            head.next.prev = head;
        }

        size++;
    }

    public void pushBack(T value) {
        if (isEmpty()) {
            head = tail = new Node(value, null, null);
        } else {
            tail = new Node(value, tail, null);
            tail.prev.next = tail;
        }

        size++;
    }

    public T popFront() {
        if (isEmpty()) {
            return null;
        }

        T value = head.value;
        head = head.next;
        if (head != null)
            head.prev = null;

        size--;
        if (size == 0)
            clear();

        return value;
    }

    public T popBack() {
        if (isEmpty()) {
            return null;
        }

        T value = tail.value;
        tail = tail.prev;
        if (tail != null)
            tail.next = null;

        size--;
        if (size == 0)
            clear();

        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peekFront() {
        if (isEmpty()) {
            return null;
        }

        return head.value;
    }

    public T peekBack() {
        if (isEmpty()) {
            return null;
        }

        return tail.value;
    }

    private void clear() {
        head = tail = null;
    }
}