import java.util.Scanner;


//Luis Lopez
//Assignment 6

class graphApp
{
public static void main(String[] args){
	   
	   
	   
   Scanner userInput = new Scanner(System.in);
   int MenuChoice;//for do while loop
   String userWord;
   char userChar;
   Tree charTree = new Tree();
   Graph charGraph = new Graph();
 
   //Building BST first
   
   System.out.println("Enter a word in all caps and press ENTER key: ");
   userWord = userInput.nextLine();//reads user word as a String
   
   for(int i = 0; i < userWord.length(); i++) {//Inserting each Character into a new Tree or data structure if it cannot be inserted
 	 
 	charTree.insert(userWord.charAt(i));

 	  
   }
   
   
   System.out.println("Map the BST into:\n1. Directed Graph\n2. Undirected Graph\n");
   int mapChoice = userInput.nextInt();
   
   //Traverse through BST Twice
   //Once for Vertex
   //Second for Edges
   
   if(mapChoice == 1)//Directed Graph
   {
	   
	   charTree.preOrderTraversalVertex(charTree.getRoot(), charGraph);
	   charTree.preOrderTraversalEdges(charTree.getRoot(), charGraph, mapChoice);
	   
   }
   else if(mapChoice == 2)//Undirected Graph
   {
	   
	   charTree.preOrderTraversalVertex(charTree.getRoot(), charGraph);
	   charTree.preOrderTraversalEdges(charTree.getRoot(), charGraph, mapChoice);
	   
   }

   do {//Now we display the Menu
 	  
 	  
 	  System.out.println("\n\n1. Display the tree\n");
       System.out.println("2. Display the Vertex array.\n");
       System.out.println("3. Display the Adjacency Matrix \n");
       System.out.println("4. Given a vertex: Display ALL possible separate paths starting with that vertex in a Depth First Search pattern.\n");
       System.out.println("5. Given a vertex: Display ALL its adjacent vertices (one edge apart)\n");
       System.out.println("6. Given a vertex: Display ALL the vertices that are two edges away from it\n");
       System.out.println("7. Exit\n");
       int choice = userInput.nextInt();
       MenuChoice = choice;//assigns user's choice to MenuChoice for do while loop to work.
       switch(choice)
          {
          case 1:
        	  //Diplay BST in a tree format
             charTree.displayTree();
             break;
             
          case 2:
         	 //Display the Vertex array 
         	 charGraph.displayVertexList();
            
             break;
          case 3:
             //Display the Adjacency Matrix
        	  System.out.println("Adjacency Matrix:\n");
             charGraph.displayAdjMatrix();
        	  
             break;
          case 4:
        	//Given a vertex: Display ALL possible separate paths starting with that
        	  //vertex in a Depth First Search pattern:
        		  //Enter the letter: 
        	  System.out.println("Enter the letter\n");
         	 
             
             break;
             
          case 5:
        	  //Given a vertex: Display ALL its adjacent vertices (one edge apart)
        	  //Enter the letter:
        	  System.out.println("Enter the letter\n");
        	  userChar = userInput.next().charAt(0);
        	  
        	  System.out.printf("Adjacent vertices (one edge apart): ");
        	  charGraph.displayOneEdgeApart(userChar);
        	  
        	  
         	 break;
         	 
          case 6:
        	  //Given a vertex: Display ALL the vertices that are two edges away from it:
        	  //Enter the letter:
        	  System.out.println("Enter the letter\n");
        	  userChar = userInput.next().charAt(0); 
        	  
        	  System.out.printf("Adjacent vertices (two edges apart): ");
        	  charGraph.displayTwoEdgeApart(userChar);
        	  
         	 break;
         	 
          case 7:
        	  //EXIT
         	 break;
         	

          default:
             System.out.println("Invalid entry\n");
          }  // end switch
       
      
       
       }// end do
 	  while(MenuChoice != 7);
  

		}  // end main()
}  // end class TreeApp