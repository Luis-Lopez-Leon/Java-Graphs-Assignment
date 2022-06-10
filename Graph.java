//Luis Lopez
//Assignment 6


class Graph
   {
   private final int MAX_VERTS = 20;
   public Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   public int nVerts;          // current number of vertices
   private StackX theStack;
// ------------------------------------------------------------
   public Graph()               // constructor
      {
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      
      for(int y=0; y<MAX_VERTS; y++)      // set adjacency
         for(int x=0; x<MAX_VERTS; x++)   //    matrix to 0
            adjMat[x][y] = 0;
      
      theStack = new StackX();
      }  // end constructor
// ------------------------------------------------------------
   public void addVertex(Node lab)
      {
      vertexList[nVerts++] = new Vertex(lab);
      }
// ------------------------------------------------------------
   public void addEdgeUndirected(int start, int end)
      {
      adjMat[start][end] = 1;
      adjMat[end][start] = 1;
      }
   public void addEdgeDirected(int start, int end) // directed graph
      {
      adjMat[start][end] = 1;
      }
// ------------------------------------------------------------
   public void displayVertex(int v)
      {
      System.out.print(vertexList[v].label);
      }
// ------------------------------------------------------------
   public void displayVertexList()// Display whole Vertex list.
   	  {
	   System.out.printf("Vertex List: ");
	   for(int i = 0; i < nVerts; i++)
	   {
		   
		   System.out.printf("{%s}",vertexList[i].label.charData);
		   
	   }

      }
// ------------------------------------------------------------
   public void displayAdjMatrix()// Display AdjMatrix in a tabular format with rows and columns for readability.
      {
	   
	   System.out.print("  ");
	   for(int i = 0; i < nVerts; i++)//rows
	   {
		   
		   System.out.printf("%s ",vertexList[i].label.charData);//prints the letters from vertex list
		   
	   }
	   System.out.println();
	   for(int i = 0; i < nVerts; i++)//rows 
	   {
		   
		   System.out.printf("%s ",vertexList[i].label.charData);//prints the letters from vertex list
		   
		    for (int j = 0; j < nVerts; j++)//columns
		    {
		    	
		        System.out.print(adjMat[i][j] + " ");//prints the AdjMatrix
		    }
		    System.out.println();
		}
	   
	   
	 
	   
   
      }
   
// ------------------------------------------------------------
   public void displayOneEdgeApart(char userChar)
   {
	   //Using AdjMatrix:
	   //Find the user's char in the row first, then iterate through that row and output the labels where matrix is 1
	   
	   for(int i = 0; i < nVerts; i++)//rows
	   {
		   
		   if(userChar == vertexList[i].label.charData)//row was found
		   {
			   
			   for(int j = 0; j < nVerts; j++)//columns
			   {
				   
				   if(adjMat[i][j] == 1)//look through the row and find the 1's
				   {
					   					
					   
					   System.out.printf("'%s' ",vertexList[j].label.charData);//Print the adjacent vertex labels
					   
					   
				   }
				   
			   }
			   
		   }
		   
	   }
	   
	   
   }
   
   public void displayTwoEdgeApart(char userChar)
   {
	   
	   //Similar as the above method
	   //Using AdjMatrix:
	   //Find the user's char in the row first, then iterate through that row and output the labels where matrix is 1
	   
	   for(int i = 0; i < nVerts; i++)//rows
	   {
		   
		   if(userChar == vertexList[i].label.charData)//row was found
		   {
			   
			   for(int j = 0; j < nVerts; j++)//columns
			   {
				   
				   if(adjMat[i][j] == 1)//look through the row and find the 1's for the FIRST ADJACENT
				   {
					   //We print that then we look for its adjacents
					   System.out.printf("'%s' ",vertexList[j].label.charData);//Print the adjacent vertex labels
					   
					   
					   //Do the same but instead of the user's starting vertex, we use the adjacent vertex we found and skip the initial vertex if come across it
					   for(int k = 0; k < nVerts; k++)//rows
					   {
						   
						   if(vertexList[j].label.charData == vertexList[k].label.charData)//row was found
						   {
							   
							   for(int l = 0; l < nVerts; l++)//columns
							   {
								   
								   if(adjMat[k][l] == 1)//look through the row and find the 1's
								   {
									   					
									   if(vertexList[l].label.charData == userChar)//skips the initial vertex
										   continue;
									   
									   System.out.printf("'%s' ",vertexList[l].label.charData);//Print the adjacent vertex labels
									   
									   
								   }
								   
							   }
							   
						   }
						   
					   }
					   
				   }
				   
			   }
			   
		   }
		   
	   }
	   
	   
   }
   

// ------------------------------------------------------------
   public void dfs()  // depth-first search
   {                                 // begin at vertex 0
   vertexList[0].wasVisited = true;  // mark it
   displayVertex(0);                 // display it
   theStack.push(0);                 // push it

   while( !theStack.isEmpty() )      // until stack empty,
      {
      // get an unvisited vertex adjacent to stack top
      int v = getAdjUnvisitedVertex( theStack.peek() );
      if(v == -1)                    // if no such vertex,
         theStack.pop();
      else                           // if it exists,
         {
         vertexList[v].wasVisited = true;  // mark it
         displayVertex(v);                 // display it
         theStack.push(v);                 // push it
         }
      }  // end while

   // stack is empty, so we're done
   for(int j=0; j<nVerts; j++)          // reset flags
      vertexList[j].wasVisited = false;
   }  // end dfs
// ------------------------------------------------------------
   // returns an unvisited vertex adj to v
   public int getAdjUnvisitedVertex(int v)
      {
      for(int j=0; j<nVerts; j++)
         if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
            return j;
      return -1;
      }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
   }  // end class Graph