package table;

import java.util.Iterator;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private Node<AnyType> first;

    private Node<AnyType> last;

//    private static final int DEFAULT_SIZE = 10;

    private int size;

    public int size(){
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public AnyType get(int index) {
        checkElementIndex(index);
        return node(index).element;
    }

    public void set(int index, AnyType value) {
        checkElementIndex(index);
        node(index).element = value;
    }

    public void clear() {
        Node temp;
        for (Node x = first; x.next == null;) {
            temp = x.next;
            x.next = null;
            x.element = null;
            x.previous = null;
            x = temp;
        }
        this.first = this.last = null;
        this.size = 0;
    }

    Node<AnyType> node(int index) {
        Node<AnyType> temp;
        if (index < this.size >> 1) {
            temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = this.last;
            for (int i = size -1; i > index; i--) {
                temp = temp.previous;
            }
        }
        return temp;
    }

    void checkElementIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(OutOfBoundMsg(index));
        }
    }

    String OutOfBoundMsg(int index) {
        return "size: " + size + " Index: " + index;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }

}
class Node<AnyType>{
    AnyType element;
    Node<AnyType> next;
    Node<AnyType> previous;

    Node(AnyType value, Node<AnyType> pre, Node<AnyType> nex) {
        this.next = nex;
        this.previous = pre;
        this.element = value;
    }

}
