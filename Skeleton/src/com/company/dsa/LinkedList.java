package com.company.dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    public LinkedList() {
    }

    public LinkedList(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    @Override
    public void addFirst(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    private boolean isEmpty(){
        return size == 0;
    }
    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node newNode = new Node(value);
            Node current = getNode(index);
            Node prevNode = current.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = current;
            current.prev = newNode;
            size++;
        }
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }



    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        return getNode(index).value;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T value = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return value;
    }


    @Override
    public int size() {
        return size;
    }
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }
}
