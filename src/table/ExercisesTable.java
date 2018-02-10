package table;

import org.junit.Test;

public class ExercisesTable {

    @Test
    public void main() {
        first(new int[]{1,2,3,4,5,6,7,8,9}, new int[] {2,5,7});
    }
    public void first(int[] L, int[] P) {
        for (int i = 0; i < P.length; i++) {
            if (P[i] < L.length) {
                System.out.println(L[P[i]]);
            } else {
                return;
            }
        }
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
}
class SimPlexNode<AnyType>{
    SimPlexNode next;
    AnyType element;

    SimPlexNode(AnyType value, SimPlexNode next) {
        this.element = value;
        this.next = next;
    }
}

