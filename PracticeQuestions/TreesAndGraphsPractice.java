import java.util.*;
import java.io.*;

public class TreesAndGraphsPractice {

  public class Node {
    int data;
    Node left;
    Node right;

    public Node(int d) {
      this.data = d;
      this.left = null;
      this.right = null;
    }
  }

  Node createMinimalBST(int array[]) {
    return createMinimalBST(array, 0, array.length-1);
  }

  Node createMinimalBST(int arr[], int start, int end) {
    if (end < start) {
      return null;
    }

    int mid = start + ((end - start) / 2);
    Node n = new Node(arr[mid]);
    n.left = createMinimalBST(arr, 0, mid - 1);
    n.right = createMinimalBST(arr, mid + 1, end);

    return n;
  }

  void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists,
                              int level) {
    if (root == null) return;

    LinkedList<Node> list = null;
    if (lists.size() == level) { // then level is not contained in this list
      list = new LinkedList<Node>();
      // Levels are always traversed in order. So if this is the first time
      // we've visited level i, we must have seen levels 0 through i - 1.
      // We can therefore safely add the level at the end.
      lists.add(list);
    } else {
      list = lists.get(level);
    }
    list.add(root);
    createLevelLinkedList(root.left, lists, level + 1);
    createLevelLinkedList(root.right, lists, level + 1);
  }

  ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {
    ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
    createLevelLinkedList(root, lists, 0);
    return lists;
  }

  // This solution works but slow. O(n log n)
  public static boolean isBalanced(Node root) {
    if (root == null) return true; // Base case
    int heightDiff = getHeight(root.left) - getHeight(root.right);
    if (Math.abs(heightDiff) > 1) {
      return false;
    } else {
      return isBalanced(root.left) && isBalanced(root.right);
    }
  }

  public static int getHeight(Node root) {
    if (root == null) return -1;
    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
  }

  // Better solution
  public static int checkHeight(Node root) {
    if (root == null) return -1;

    int leftHeight = checkHeight(root.left);
    if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //error

    int rightHeight = checkHeight(root.right);
    if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

    int heightDiff = leftHeight - rightHeight;
    if (Math.abs(heightDiff) > 1) {
      return Integer.MIN_VALUE; // error
    } else {
      return Math.max(leftHeight, rightHeight) + 1;
    }
  }


  // Have a min, max range when we traverse to the left
  // the min is neg infinity and max is root.
  public static boolean validateBST(Node node) {
    return validateBSTUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static boolean validateBSTUtil(Node node, int min, int max) {
    if (node == null) return true;
    if (node.data < min || node.data > max) return false;
    return validateBSTUtil(node.left, min, node.data - 1) &&
            validateBSTUtil(node.right, node.data + 1, max);
  }

  // Can also do in-order traversal while checking if its sorted (increasing)
  // last_printed is just using pass-by-reference to keep track.
  // we can also have a global variable instead.
  public static boolean checkBST(Node n, int[] last_printed) {
    if (n == null) return true;

    // Check / recurse left
    if (!checkBST(n.left, last_printed)) return false;

    // Check current
    if (last_printed[0] != Integer.MIN_VALUE && n.data <= last_printed[0]) {
      return false;
    }
    last_printed[0] = n.data;

    // Check / recurse right
    if (!checkBST(n.right, last_printed)) return false;

    return true;
  }

  public static Node inOrderSucc(Node n) {
    if (n == null) { return null; }

    // Found a right subtree, return leftmost node of that tree
    if (n.right != null) {
      return leftMostChild(n.right);
    }
    else {
      Node q = n;
      Node parent = q.parent;
      // Go up until we are a left child
      while (parent != null && parent.left != q) {
        q = parent;
        parent = parent.parent;
      }
      return parent;
    }
  }

  Node leftMostChild(Node n) {
    if (n == null) { return null; }
    while (n.left != null) {
      n = n.left;
    }
    return n;
  }

  Node commonAncestor(Node root, Node p, Node q) {
    // Check if either node is not in the tree or if one covers the other
    if (!covers(root, p) || !covers(root, q)) {
      return null;
    } else if (covers(p, q)) {
      return p;
    } else if (covers(q, p)) {
      return q;
    }

    // Traverse upwards until you find a node that covers q
    Node sibling = getSibling(p);
    Node parent = p.parent;
    while (!covers(sibling, q)) {
      sibling = getSibling(parent);
      parent = parent.parent;
    }
    return parent;
  }

  boolean covers(Node root, Node p) {
    if (root == null) return false;
    if (root == p) return true;
    return covers(root.left, p) || covers(root.right, p);
  }

  Node getSibling(Node node) {
    if (node.parent.left == node) {
      return node.parent.right;
    } else if (node.parent.right == node) {
      return node.parent.left;
    }
  }
}
