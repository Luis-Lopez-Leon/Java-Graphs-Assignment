import java.util.*;               // for Stack class

//Luis Lopez
//Assignment 6

class Tree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                           // (assumes non-empty tree)
      Node current = root;               // start at root
      while(current.charData != key)        // while no match,
         {
         if(key < current.charData)         // go left?
            current = current.leftChild;
         else                            // or go right?
            current = current.rightChild;
         if(current == null)             // if no child,
            return null;                 // didn't find it
         }
      return current;                    // found it
      }  // end find()
// -------------------------------------------------------------
   public void preOrderTraversalVertex(Node localRoot, Graph graph1)//Using modified preOrder Traversal to visit every node in the tree
   													//and map vertices into the Graph's vertexList
   {
   if(localRoot != null)
      {
	   
	 //Mapping vertex starts here
	 
	  graph1.addVertex(localRoot);
	   
	  preOrderTraversalVertex(localRoot.leftChild, graph1);
	  preOrderTraversalVertex(localRoot.rightChild, graph1);
      
  
      }
   }
// -------------------------------------------------------------
   public void preOrderTraversalEdges(Node localRoot, Graph graph1, int mapChoice)//Using modified preOrder Traversal to visit every node in the tree
																					//and map edges into the Graph
   	  {
	   
	   //Adjacency matrix is based on Parent-Child relationship
	   
	   
	   if(localRoot != null)
	   {

		   //Find the parent on the vertex list and saves the index so I can look for its children later
		   int parent = 0;
		   
		   for(int i = 0; i < graph1.nVerts; i++)
		   {
			   if(localRoot == graph1.vertexList[i].label)
			   {
				   parent = i;//saves index
				   break;
			   }
		   }
		   
		   //Mapping edges starts here
		   
		   if(mapChoice == 1)//Directed Graph - one way edges
		   {
			   			  
			   for(int i = 0; i < graph1.nVerts; i++)//search through the vertext list to find parent's children
			   {
				   
				   if(localRoot.leftChild == graph1.vertexList[i].label)//find the parent's left child on the vertex list
				   {
					   
					   graph1.addEdgeDirected(parent, i);//create edge with parent and child
	 
				   }
				   
				   if(localRoot.rightChild == graph1.vertexList[i].label)//find the parent's right child on the vertex list
				   {
					   
					   graph1.addEdgeDirected(parent, i);//create edge with parent and child
					   
				   }

			   }
			   
		   }
		   else if(mapChoice == 2)//Undirected Graph - two way edges
		   {
			   
			   
			   for(int i = 0; i < graph1.nVerts; i++)//search through the vertext list to find parent's children
			   {
				   
				   if(localRoot.leftChild == graph1.vertexList[i].label)//find the parent's left child on the vertex list
				   {
					   
					   graph1.addEdgeUndirected(parent, i);//create edge with parent and child
	 
				   }
				   
				   if(localRoot.rightChild == graph1.vertexList[i].label)//find the parent's right child on the vertex list
				   {
					   
					   graph1.addEdgeUndirected(parent, i);//create edge with parent and child
					   
				   }

			   }
			   
		   }
		   
		   preOrderTraversalEdges(localRoot.leftChild, graph1, mapChoice);
		   preOrderTraversalEdges(localRoot.rightChild, graph1, mapChoice);





	   }
	   
   	  }
// -------------------------------------------------------------
   public Node getRoot()
   {
	   
	   return root;
	   
   }
// -------------------------------------------------------------
   public void insert(char id)
      {
      Node newNode = new Node();    // make new node
      newNode.charData = id;           // insert data
      //newNode.dData = dd;
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
         {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
            {
            parent = current;
            if(id < current.charData)  // go left?
               {
               current = current.leftChild;
               if(current == null)  // if end of the line,
                  {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                  }
               }  // end if go left
            else                    // or go right?
               {
               current = current.rightChild;
               if(current == null)  // if end of the line
                  {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                  }
               }  // end else go right
            }  // end while
         }  // end else not root
      }  // end insert()
// -------------------------------------------------------------
   public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
      Node current = root;
      Node parent = root;
      boolean isLeftChild = true;

      while(current.charData != key)        // search for node
         {
         parent = current;
         if(key < current.charData)         // go left?
            {
            isLeftChild = true;
            current = current.leftChild;
            }
         else                            // or go right?
            {
            isLeftChild = false;
            current = current.rightChild;
            }
         if(current == null)             // end of the line,
            return false;                // didn't find it
         }  // end while
      // found node to delete

      // if no children, simply delete it
      if(current.leftChild==null &&
                                   current.rightChild==null)
         {
         if(current == root)             // if root,
            root = null;                 // tree is empty
         else if(isLeftChild)
            parent.leftChild = null;     // disconnect
         else                            // from parent
            parent.rightChild = null;
         }

      // if no right child, replace with left subtree
      else if(current.rightChild==null)
         if(current == root)
            root = current.leftChild;
         else if(isLeftChild)
            parent.leftChild = current.leftChild;
         else
            parent.rightChild = current.leftChild;

      // if no left child, replace with right subtree
      else if(current.leftChild==null)
         if(current == root)
            root = current.rightChild;
         else if(isLeftChild)
            parent.leftChild = current.rightChild;
         else
            parent.rightChild = current.rightChild;

      else  // two children, so replace with inorder successor
         {
         // get successor of node to delete (current)
         Node successor = getSuccessor(current);

         // connect parent of current to successor instead
         if(current == root)
            root = successor;
         else if(isLeftChild)
            parent.leftChild = successor;
         else
            parent.rightChild = successor;

         // connect successor to current's left child
         successor.leftChild = current.leftChild;
         }  // end else two children
      // (successor cannot have a left child)
      return true;                                // success
      }  // end delete()
// -------------------------------------------------------------
   // returns node with next-highest value after delNode
   // goes to right child, then right child's left descendents
   private Node getSuccessor(Node delNode)
      {
      Node successorParent = delNode;
      Node successor = delNode;
      Node current = delNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != delNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = delNode.rightChild;
         }
      return successor;
      }
// -------------------------------------------------------------
   public void traverse(int traverseType)
      {
      switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.charData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.charData + " ");
         inOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.charData + " ");
         }
      }
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.charData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   }  // end class Tree