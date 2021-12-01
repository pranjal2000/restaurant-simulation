import java.util.Vector;

// import javafx.stage.PopupBuilder;

public class AVLTree {

    private Node root;
  
    private class Node {
      private int id;
      private customer customer;
      private int balance;
      private int height;
      private Node left, right, parent;
  
      Node(int k, Node p, customer c) {
        id = k;
        this.customer =c;
        parent = p;
      }
    }
    public int sum = 0;
    public int count =0;

    public boolean allCustomerLeft(int K){
      return allCustomerLeftHelper(root, K);
    }

    public boolean allCustomerLeftHelper(Node root, int K){
      if(root==null){
        return true;
      }
      
      return root.customer.counter_no == K || allCustomerLeftHelper(root.left, K) || allCustomerLeftHelper(root.right, K);
      
    }

    public boolean insert(int id, customer c) {
      if (root == null) root = new Node(id, null, c);
      else {
        Node n = root;
        Node parent;
        while (true) {
          if (n.id == id) return false;
  
          parent = n;
  
          boolean goLeft = n.id > id;
          n = goLeft ? n.left : n.right;
  
          if (n == null) {
            if (goLeft) {
              parent.left = new Node(id, parent, c);
            } else {
              parent.right = new Node(id, parent, c);
            }
            rebalance(parent);
            break;
          }
        }
      }
      return true;
    }
  
    private void delete(Node node) {
      if (node.left == null && node.right == null) {
        if (node.parent == null) root = null;
        else {
          Node parent = node.parent;
          if (parent.left == node) {
            parent.left = null;
          } else parent.right = null;
          rebalance(parent);
        }
        return;
      }
      if (node.left != null) {
        Node child = node.left;
        while (child.right != null) child = child.right;
        node.id = child.id;
        delete(child);
      } else {
        Node child = node.right;
        while (child.left != null) child = child.left;
        node.id = child.id;
        delete(child);
      }
    }
  
    public void delete(int delKey) {
      if (root == null) return;
      Node node = root;
      Node child = root;
  
      while (child != null) {
        node = child;
        child = delKey >= node.id ? node.right : node.left;
        if (delKey == node.id) {
          delete(node);
          return;
        }
      }
    }
  
    private void rebalance(Node n) {
      setBalance(n);
  
      if (n.balance == -2) {
        if (height(n.left.left) >= height(n.left.right)) n = rotateRight(n);
        else n = rotateLeftThenRight(n);
  
      } else if (n.balance == 2) {
        if (height(n.right.right) >= height(n.right.left)) n = rotateLeft(n);
        else n = rotateRightThenLeft(n);
      }
  
      if (n.parent != null) {
        rebalance(n.parent);
      } else {
        root = n;
      }
    }
  
    private Node rotateLeft(Node a) {
  
      Node b = a.right;
      b.parent = a.parent;
  
      a.right = b.left;
  
      if (a.right != null) a.right.parent = a;
  
      b.left = a;
      a.parent = b;
  
      if (b.parent != null) {
        if (b.parent.right == a) {
          b.parent.right = b;
        } else {
          b.parent.left = b;
        }
      }
  
      setBalance(a, b);
  
      return b;
    }
  
    private Node rotateRight(Node a) {
  
      Node b = a.left;
      b.parent = a.parent;
  
      a.left = b.right;
  
      if (a.left != null) a.left.parent = a;
  
      b.right = a;
      a.parent = b;
  
      if (b.parent != null) {
        if (b.parent.right == a) {
          b.parent.right = b;
        } else {
          b.parent.left = b;
        }
      }
  
      setBalance(a, b);
  
      return b;
    }
  
    private Node rotateLeftThenRight(Node n) {
      n.left = rotateLeft(n.left);
      return rotateRight(n);
    }
  
    private Node rotateRightThenLeft(Node n) {
      n.right = rotateRight(n.right);
      return rotateLeft(n);
    }
  
    private int height(Node n) {
      if (n == null) return -1;
      return n.height;
    }
  
    private void setBalance(Node... nodes) {
      for (Node n : nodes) {
        reheight(n);
        n.balance = height(n.right) - height(n.left);
      }
    }
  
    public void printBalance() {
      printBalance(root);
    }
  
    private void printBalance(Node n) {
      if (n != null) {
        printBalance(n.left);
        System.out.printf("%s ", n.balance);
        printBalance(n.right);
      }
    }
  
    private void reheight(Node node) {
      if (node != null) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
      }
    }
  
    public customer search(int id) {
      Node result = searchHelper(this.root, id);
      if (result != null) return result.customer;
  
      return null;
    }
  
    private Node searchHelper(Node root, int id) {
      // root is null or id is present at root
      if (root == null || root.id == id) return root;
  
      // id is greater than root's id
      if (root.id > id)
        return searchHelper(root.left, id); // call the function on the node's left child
  
      // id is less than root's id then
      // call the function on the node's right child as it is greater
      return searchHelper(root.right, id);
    }


    // Vector<customer> res =new Vector<customer>();
    public Vector<customer> searchTime(int t) {
      // Vector<customer> res =new Vector<customer>();
      Vector<customer> res =new Vector<customer>();
      searchTimeHelper(this.root, t, res);
      if (res.size()>0){
        // Vector<customer> ans = new Vector<>();
        // while(res.isEmpty()==false){
        //   ans.add(res.lastElement());
        //   res.remove(res.size()-1);
        // }
        // for(int i=0;i<res.size();i++){
        //   ans.add(res.get(i));
        //   // System.out.println(ans.get(i));
        // }
        // res.clear();
        return res;
      }
  
      return null;
    }
  
    private  void searchTimeHelper(Node root, int t,Vector<customer> res) {
      // root is null or id is present at root
      if(root == null) return;
      if (root.customer.arrival_time == t) res.add(root.customer);
  
      // id is greater than root's id
      // if (root.customer.arrival_time  > t)
      searchTimeHelper(root.left, t,res); // call the function on the node's left child
  
      // id is less than root's id then
      // call the function on the node's right child as it is greater
      searchTimeHelper(root.right, t,res);
    }
    public Vector<Integer> sumAllTree(){
      // int sum=0;
      // int count=0;
      // if(root==null) return 0;
      sumAllTreeHelper(this.root);

      Vector<Integer> ans = new Vector<>();
      ans.add(this.sum);
      ans.add(this.count);
      this.sum=0;
      this.count=0;
      return ans;

    }
    public void inorder(){
      inorderHelper(this.root);
    }
    private void inorderHelper(Node root){
      if(root==null) return;

      inorderHelper(root.left);
      System.out.println("id: " + root.customer.id+ " order: "+ root.customer.order+" time: "+root.customer.arrival_time);
      inorderHelper(root.right);
    }


    private void sumAllTreeHelper(Node root){
      if(root==null) {
        return;
      }
      this.sum = this.sum + root.customer.waiting_time;
      this.count +=1;
      sumAllTreeHelper(root.left);
      sumAllTreeHelper(root.right);
    }
    // public static void main(String[] args) {
    //   AVLTree tree = new AVLTree();
  
    //   System.out.println("Inserting values 1 to 10");
    //   for (int i = 1; i < 10; i++) tree.insert(i,c);
  
    //   System.out.print("Printing balance: ");
    //   tree.printBalance();
    // }
  }