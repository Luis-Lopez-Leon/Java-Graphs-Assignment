
//Luis Lopez
//Assignment 6

class Node
   {
   public char charData;              // char data item (key)
 
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   public Node parent;			//this node's parent
   

   public void displayNode()      // display yourself
      {
      System.out.print('{');
      System.out.print(charData);
      System.out.print("}");
      }
   }  // end class Node