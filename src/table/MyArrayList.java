package table;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private Object[] elementData;
    private static final int  DEFAULT_CAPACITY = 10;
    //存储元素个数
    private int theSize;
    //改变基础数组容量
    //size
    public int size() {
        return this.theSize;
    }
    //isempth
    public boolean isEmpty(){
        if (theSize == 0) {
            return true;
        }
        return false;
    }
    //clear
    public void clear() {
        this.theSize = 0;
        for (int i = 0; i < theSize; i++) {
            elementData[i] = null;
        }
    }

    //remove
    public AnyType remove(int i) {
        checkBound(i);
        AnyType oldElement = this.get(i);
        for (int j = i; j < theSize; j++) {
            elementData[j] = elementData[j + 1];
        }
        elementData[--theSize] = null;//clear to let GC do it's work
        theSize--;
        return oldElement;
    }

    //get
    public AnyType get(int index) {
        checkBound(index);
        return elementData(index);
    }

    //set
    public AnyType set(int index, AnyType newElement) {
        checkBound(index);
        AnyType oldElement = elementData(index);
        elementData[index] = newElement;
        return oldElement;
    }

    //add
    public void add(AnyType element) {
        add(element, size());
    }

    public void add(AnyType element, int index) {
        if (elementData.length ==  size()) {
            ensureCapacity(theSize * 2 + 1);
        }
        for (int i = theSize; i > index; i++) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = element;
        theSize++;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }
        //判断是否超过当前容量
        //扩容
        Object[] newDataElement = new Object[newCapacity];
        for (int i = 0; i < theSize; i++) {
            newDataElement[i] = elementData[i];
        }
        elementData = newDataElement;
    }

    AnyType elementData(int index) {
        return (AnyType) elementData[index];
    }

    private void checkBound(int index) {
        if (index < 0 || index >= theSize) {
            throw new ArrayIndexOutOfBoundsException(outOfBoundMsg(index));
        }
    }
    private String outOfBoundMsg(int index) {
        return "下标" + index + "size" + theSize;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super AnyType> action) {

    }

    @Override
    public Spliterator<AnyType> spliterator() {
        return null;
    }
    //add

}
