package table;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExercisesTable {

    @Test
    public void main() {
//        List L1 = new ArrayList();
//        L1.add(2);
//        L1.add(4);
//        List L2 = new ArrayList();
//        L2.add(1);
//        L2.add(5);
//        L2.add(7);
//        L2.add(9);
//        L2.add(10);
//        List<Integer> list = andSet(L1, L2);
//        System.out.println("长度： " + list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.printf(" " + list.get(i));
//        }

    }

    //通过只调用链交换两元素
    public void secondDuplex(Node optionNode) {
        Node preNode = optionNode.previous;
        Node nextNode = optionNode.next;
        preNode.next = nextNode;
        nextNode.previous = preNode;
        optionNode.previous = nextNode;
        optionNode.next = nextNode.next;
        nextNode.next = optionNode;
        optionNode.next.previous = optionNode;
    }

    public void secondSimplex(SimPlexNode preNode) {
        SimPlexNode optionNdoe = preNode.next;
        SimPlexNode nextNode = optionNdoe.next;
        preNode.next = nextNode;
        optionNdoe.next = nextNode.next;
        nextNode.next = optionNdoe;
    }

    <AnyType> Node constructNodeList(AnyType[] source) {
        Node root = new Node(source[0], null, null);
        Node preNode = root;
        Node newNode = null;
        for (int i = 1; i < source.length; i++) {
            newNode = new Node(source[i], preNode, null);
            preNode.next = newNode;
            preNode = newNode;
        }
        return root;
    }

    //给定两个已排序的L1 L2 只用基本的表操作实现L交L2
    public List myIntersection(List<Integer> L1, List<Integer> L2) {
        //find remove insert
        //思路 关键字 有序 嵌套循环 使用
        int index = 0;
        List returnList = new LinkedList();//插入删除开销小
        for(int i = 0; i < L1.size(); i++) {
            for(int j = index; j < L2.size(); j++) {
                if (L1.get(i) <= L2.get(j)) {
                    if (L1.get(i) == L2.get(j))
                    returnList.add(L1.get(i));
                    index = j;
                    break;
                }
            }

        }
        return returnList;
    }

    public <AnyType extends Comparable<? super AnyType>> List intersection(List<AnyType> list1, List<AnyType> list2) {
        //此为标准答案
        //充分利用了L1和L2的有序性 如果item1 < item2 推进item1 等于 记录相等值同时推进 小于 推进L2
        //知道L1 或 L2中有一个没有下一项为止
        //截至条件选的好 我写的版本必然便利玩L1
        //泛型使用还要加强 迭代器还不熟
        Iterator<AnyType> it1 = list1.iterator();
        Iterator<AnyType> it2 = list2.iterator();
        List<AnyType> returnList = new LinkedList<AnyType>();

        AnyType item1 = null, item2 = null;
        if (it1.hasNext() && it2.hasNext()) {
            item1 = it1.next();
            item2 = it2.next();
        }

        while (item1 != null && item2 != null) {
            int compareItem = item1.compareTo(item2);
            if (compareItem == 0) {
                returnList.add(item1);
                item1 = it1.hasNext() ? it1.next() : null;
                item2 = it2.hasNext() ? it2.next() : null;
            } else if (compareItem < 0) {
                item1 = it1.hasNext() ? it1.next() : null;
            } else {
                item2 = it2.hasNext() ? it2.next() : null;
            }
        }
        return returnList;
    }

    //两个已排序的集合list1 list2 1
    public <AnyType extends Comparable<? super AnyType>> List andSet(List<AnyType> list1, List<AnyType> list2) {
        Iterator<AnyType> it1 = list1.iterator();
        Iterator<AnyType> it2 = list2.iterator();
        List returnList = new LinkedList();
        AnyType item1 = null, item2 = null;
        if (it1.hasNext() && it2.hasNext()) {
            item1 = it1.hasNext() ? it1.next() : null;
            item2 = it2.hasNext() ? it2.next() : null;
        }
        while (item1 != null && item2 != null) {
            int compareItem = item1.compareTo(item2);
            if (compareItem == 0) {
                returnList.add(item1);
                item1 = it1.hasNext() ? it1.next() : null;
                item2 = it2.hasNext() ? it2.next() : null;
            } else if (compareItem < 0) {
                returnList.add(item1);
                item1 = it1.hasNext() ? it1.next() : null;
            } else {
                returnList.add(item2);
                item2 = it2.hasNext() ? it2.next() : null;
            }
        }
        while (it1.hasNext()) {
            returnList.add(item1);
            item1 = it1.next();
        }
        for (;it2.hasNext(); item2 = it2.next()){
            returnList.add(item2);
        }
        if (item1 != null) {
            returnList.add(item1);
        }
        if (item2 != null) {
            returnList.add(item2);
        }
        return returnList;
    }
    @Test
    public void test2(){
        System.out.println(josephus(1, 5));
    }
    //
    public int josephus(int M, int N) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            list.add(i+1);
        }
        Iterator<Integer> it = list.iterator();
        int index = 0;
//        int item = it.next();
        while (list.size() > 1) {
            toNext(list, it);
            index++;
            if (index % M == 0) {
                it.remove();
                index = 0;
                toNext(list, it);
            }
        }
        return list.get(0);
    }

    void toNext(List list, Iterator it) {
        if (!it.hasNext()) {
            it = list.iterator();
        } else {
            it.next();
        }
    }
}
class SimPlexNode<AnyType>{
    SimPlexNode next;
    AnyType element;

    SimPlexNode(AnyType value, SimPlexNode next) {
        this.element = value;
        this.next = next;
    }
}

