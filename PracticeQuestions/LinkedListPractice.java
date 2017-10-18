import java.io.*;
import java.util.*;

public class LinkedListPractice {

  public static class Node {
    int data;
    Node next;

    public Node () {
      this.next = null;
    }

    public Node (int d) {
      this.data = d;
      this.next = null;
    }
  }

  static Node head;

  static void removedups(Node n) {
    HashSet<Integer> list = new HashSet<Integer>();
    Node previous = n;
    while (n != null) {
      if (list.contains(n.data)) {
        previous.next = n.next;
        n = n.next;
      }
      else {
        list.add(n.data);
        previous = n;
      }
    }
    n = n.next;
  }

  static Node kthtolast(Node head, int k) {
    Node p1 = head;
    Node p2 = head;
    for (int i = 0; i < k; i++) {
      if (p1 == null) return null;  // out of bounds
      p1 = p1.next;
    }
    // Move at same pace, when p1 is at end, then p2 will be at k'th last element
    while (p1 != null) {
      p1 = p1.next;
      p2 = p2.next;
    }
    return p2;
  }

  static Node kthtolast_recur(Node head, int k, int[] counter) {
    if (head == null) {
      return null;
    }
    Node node = kthtolast_recur(head.next, k, counter);
    counter[0] = counter[0] + 1;
    if (counter[0] == k) {
      return head;
    }
    return node;
  }

  static void deletemiddle(Node tobedeleted) {
    if (tobedeleted == null || tobedeleted.next == null) {
      return;
    }
    tobedeleted.data = tobedeleted.next.data;
    tobedeleted.next = tobedeleted.next.next;
  }

  static Node partition(int partvalue) {
    Node n = head;
    Node list1 = head;
    Node list1head = list1;
    Node list2 = head;
    Node list2head = list2;

    while (n != null) {
      Node next = n.next;
      n.next = null;
      if (n.data < partvalue) {
        list1.next = n;
        list1 = n;
      } else {
        list2.next = n;
        list2 = n;
      }
      n = next;
    }
    list2.next = null;
    list1.next = list2head.next;
    return list1head.next;
  }

  public static int sumlists(Node n1, Node n2) {
    int num1 = 0;
    int num2 = 0;

    int multiplier = 1;
    while(n1 != null) {
      num1 += n1.data * multiplier;
      multiplier = multiplier*10;
      n1 = n1.next;
      System.out.println(String.valueOf(num1));
    }

    multiplier = 1;
    while(n2 != null) {
      num2 += n2.data * multiplier;
      multiplier = multiplier*10;
      n2 = n2.next;
      System.out.println(String.valueOf(num2));
    }

    int out = num1 + num2;
    return out;

  }

  public static Node sumlists_recur(Node n1, Node n2, int carry) {
    if(n1 == null && n2 == null && carry == 0) {
      return null;
    }

    Node result = new Node(-1);
    int value = carry;
    if (n1 != null) {
      value += n1.data;
    }
    if (n2 != null) {
      value += n2.data;
    }

    result.data = value % 10; // get ones digit of number

    // Recurse
    if (n1 != null || n2 != null) {
      Node more = sumlists_recur(n1 == null ? null : n1.next,
                                n2 == null ? null : n2.next,
                                value >= 10 ? 1 : 0);
      result.next = more;
    }
    return result;
  }

  // traversal, print list
  public static void printList (Node x) {
    Node n = x;
    while (n != null) {
      System.out.print(n.data + " ");
      n = n.next;
    }
  }

  public static Node insertNode(Node n, int d) {
    Node newNode = new Node(d);
    newNode.next = n;
    n = newNode;
    return n;
  }

  public static void main(String [] args) {
    testsum();
  }

  public static void testsum() {
    Node n1 = new Node(6);
    n1 = insertNode(n1, 1);
    n1 = insertNode(n1, 7);

    Node n2 = new Node(2);
    n2 = insertNode(n2, 9);
    n2 = insertNode(n2, 5);

    printList(sumlists_recur(n1, n2, 0));
    System.out.println("");


    n1 = new Node(6);
    n1 = insertNode(n1, 1);

    n2 = new Node(2);
    n2 = insertNode(n2, 9);
    n2 = insertNode(n2, 5);

    printList(sumlists_recur(n1, n2, 0));
  }

  // Reverse a linkedlist and return that cloned list
  Node reverseAndClone(Node node) {
    Node head = null;
    while (node != null) {
      Node n = new Node(node.data); // Clone
      n.next = head;
      head = n;
      node = node.next
    }
    return head;
  }

  boolean isPalindrome(Node head) {
    Node reversed = reverseAndClone(head);
    return isEqual(head, reversed);
  }

  boolean isEqual(Node l1, Node l2) {
    while (l1 != null && l2 != null) {
      if (l1.data != two.data) {
        return false;
      }
      one = one.next;
      two = two.next;
    }
    return one == null && two == null;
  }

  boolean isPalindromeStack(Node head) {
    Node fast = head;
    Node slow = head;

    Stack<Integer> stack = new Stack<Integer>();

    while (fast != null && fast.next != null) {
      stack.push(slow.data);
      slow = slow.next;
      fast = fast.next.next;
    }

    // Skip middle, dont want to compare since its a palindrome
    if (fast != null) {
      slow = slow.next;
    }

    while (slow != null) {
      Integer top = stack.pop();
      if (slow.data != top) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }

  boolean isCircular(Node head) {
    if (head == null || head.next == null) {
      return false;
    }
    Node fast = head;
    Node slow = head;
    while(fast != null && fast.next != null && slow != null) {
      if (fast == slow) {
        return true;
      }
      fast = fast.next.next;
      slow = slow.next;
    }
    return false;
  }

  void findCircularDelete(Node head) {
    if (head == null || head.next == null) {
      return;
    }
    Node fast = head;
    Node slow = head;
    while(fast != null && fast.next != null && slow != null) {
      if (fast == slow) {
        break;
      }
      fast = fast.next.next;
      slow = slow.next;
    }

    // Error check at end of the while loop, when there is no circular
    if (fast == null || fast.next == null) {
      return;
    }

    // Found the circular point
    if (slow == fast) {
      slow = head;
      while (slow.next != fast.next) {
        slow = slow.next;
        fast = fast.next;
      }
      // Fast will now be at the looping point
      fast.next = null;
    }
  }
}
