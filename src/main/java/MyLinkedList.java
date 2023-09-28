package main.java;

class MyLinkedList<T> {
    private Node<T> head;

    public MyLinkedList() {
        head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public T getNext() {
        if (head != null) {
            T data = head.data;
            head = head.next;
            return data;
        }
        return null;
    }

    public T searchByTransactionId(int transactionId) {
        Node<T> current = head;
        while (current != null) {
            if (current.data instanceof Transaction) {
                Transaction transaction = (Transaction) current.data;
                if (transaction.getTransactionId() == transactionId) {
                    return current.data;
                }
            }
            current = current.next;
        }
        return null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
