class LinkedList {
  Node head;

  static class Node {
    int data;
    Node next;
    Node (int d) {
      data = d;
      next = null;
    }
  }

  // traversal, print list
  public void printList () {
    Node n = head;
    while (n != null) {
      System.out.print(n.data + " ");
      n = n.next;
    }
  }

  // Insertion
  // Complexity O(1)
  public void insertNode(int d) {
    Node newNode = new Node(d);
    newNode.next = head;
    head = newNode;
  }

  // Insert after a given node
  // complexity O(1)
  public void insertAfter(Node prev_node, int d) {
    if (prev_node == null) {
      System.out.println("The given previous node cannot be null");
    }

    Node newNode = new Node(d);
    newNode.next = prev_node.next;
    prev_node.next = newNode;
  }

  // Insert at the end of the link
  // complexity O(n)
  public void insertEnd(int d) {
    Node newNode = new Node(d);

    // if list is empty then head is the new node.
    if (head == null) {
      head = newNode;
    }

    Node temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    temp.next = newNode;
    newNode.next = null;
  }

  // Delete first occurence of given key
  public void Delete(int d) {
    // Store head node
    Node temp = head;
    Node prev = null;

    // If head node itself holds the key to be deleted
    if (temp != null && temp.data == d) {
      head = temp.next;
      return;
    }

    // Search for key to be deleted, keep track of the prev node
    // since we need to change prev.next.
    while (temp.next != null && temp.data != d) {
      prev = temp;
      temp = temp.next;
    }

    // If key not present in list
    if (temp == null) return;

    // Unlink the node from linked list
    prev.next = temp.next;
  }

  // Delete a node at given position
  public void deleteGivenPosition(int index) {
    // empty list
    if (head == null) {
      return;
    }

    // Store head
    Node temp = head;
    // delete the head
    if (index == 0) {
      head = temp.next;
      return;
    }

    // find the previous node of the node to be deleted
    for (int i = 0; temp != null && i < index-1; i++) {
      temp = temp.next;
    }

    // if position is more than number of nodes
    if (temp == null || temp.next == null) {
      return;
    }

    // temp is the previous of the node to be deleted
    // temp.next = node to be deleted
    // temp.next.next = node after the node to be deleted
    Node next = temp.next.next;
    temp.next = next;
  }
}
