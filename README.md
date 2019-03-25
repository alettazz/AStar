**8-Puzzle solved with A * Search using two Heuristics.**

Considering a 3x3 grid, the Puzzle board is filled with numbers from 1-8 and a 0 representing the hole. Using A Star Search algorithm the solution provided compares two Heuristics: 
> 1. The number of tiles not being on their "goal" place;
> 2. Using the Manhattan Distance.
  
 Program is configured to run on commandline taking some linkers neccessary and some optional.
 
**Compile:**
          javac ASolver.java
 
**Use:**
 
 necessary
 
      java ASolver -input <inputFile> -h <heuristicType>  
      
      OR
      
      java ASolver <initialPuzzle> -h <heuristicType>
      
      WHERE
      
      heuristicType: 1 - for using the number of tiles not on their goal place
                     2 - for using Manhattan Distance
      
      inputFile: 5 6 8 all separated by a *space* and aligned 3-by-3 in *separate lines*
                 4 0 1
                 2 3 7
 
 optional
 
      -solseq : to print out step by step the puzzle's tile pushes
      
      -pcost : to print the cost of the Heuristic
      
      -nvisited : to print the number of nodes that were visited during the search,
                  the number of times successors have been visited
      
      -rand <N> : to print out the N.-th push
      
**EXAMPLE**

javac ASolver.java

java ASolver 5 6 8 4 0 1 2 3 7 -rand 5 -pcost -nvisited -h 1  

this example uses the heuristic of number of tiles out of their goal place 

giving a **cost**: 26

**nodes visited**:27

**rand** 5 :
    
     5 | 8 | 1
     ----------
     4 | 3 | 6
     ----------
     2 | 0 | 7

**EXAMPLE 2.**

javac ASolver.java

java ASolver -input in.txt -pcost -h 2 -nvisited -rand 5

this example uses the heuristic of Manhattan Distance

giving a **cost**: 20

**nodes visited**:21

**rand** 5 :
    
    5 | 1 | 6
    ----------
    4 | 3 | 8
    ----------
    2 | 0 | 7


the **int.txt** :
     
     5 6 8
     4 0 1 
     2 3 7
     //the same initial state as in previous e.g. 

**CONCLUSION**

The Manhattan Distance gives better results, finds the solution with less cost visiting less successor nodes and evaluating less.
