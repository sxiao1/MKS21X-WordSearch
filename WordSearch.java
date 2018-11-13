import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    public WordSearch(int rows,int cols,String fileName){
      seed = (int)(Math.random());
      randgen = new Random(seed);
      data = new char[rows][cols];
      wordsAdded = new ArrayList<String>();
      wordsToAdd = new ArrayList<String>();
      for (int i = 0; i < rows; i ++){
        for(int x = 0; x < cols; x++){
          data[i][x] = '_';
        }
      }
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String word = in.nextLine();
          wordsToAdd.add(word);
        }
      } catch (FileNotFoundException e){
        e.printStackTrace();
      }
    }

    public WordSearch(int rows,int cols,String fileName, int randSeed){
      this(rows, cols, fileName);
      randgen = new Random(randSeed);
      seed = randSeed;
    }

    private void clear(){
      for(int i = 0; i < data.length; i ++){
        for(int x = 0; x < data[i].length; x++){
          data[i][x] = '_';
        }
      }
    }
    private boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
      if (rowIncrement == 0 && colIncrement ==0){
	       return false;
	   }
     try{
       if(r < 0 || c < 0 || r >= data.length || c >= data[r].length){
         return false;
       }
       for( int i = 0; i < word.length(); i ++){
         if (data[r][c] != '_' && data[r][c]!= word.charAt(i)){
           return false;
         }
         r += rowIncrement;
         c += colIncrement;
       }
       wordsAdded.add(word);
       wordsToAdd.remove(word);

     }catch (ArrayIndexOutOfBoundsException e){
       return false;
     } catch (IndexOutOfBoundsException e){
       return false;
     }
     return true;
   }

   private boolean addAllWords(){
     int num = 0;
     while(num < 100 && wordsToAdd.size() > 0){
       int row = randgen.nextInt(data.length);
       int col = randgen.nextInt(data[0].length);
       int colIncrement = randgen.nextInt(3);
       int rowIncrement = randgen.nextInt(3);
       String newword = wordsToAdd.get(randgen.nextInt(wordsToAdd.size()));
       addWord(newword, row, col, rowIncrement, colIncrement);
       num ++;
     }
     return true;
   }


    public String toString(){
      String newstr = "|";
      for(int i = 0; i < data.length; i++){
        for(int x = 0; x < data[i].length; x++){
          newstr+= data[i][x] + " ";
        }
        newstr += "|";
        newstr += "\n |";
        }
      return newstr;
    }
    public boolean addWordHorizontal(String word,int row, int col){
      if(col > data[row].length && col + word.length() > data[row].length && row < 0 && col < 0){
        return false;
      }
      for(int i = 0; i < word.length(); i++){
        if(data[row][col + i] != '_' && data[row][col + i] != word.charAt(i)){
            return false;
          }
        else{
          data[row][col + i] = word.charAt(i);
        }
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      if(row + word.length() > data.length && row > data.length && row < 0 && col < 0 && col > data[row].length){
        return false;
      }
      for(int i = 0; i < word.length(); i++){
        if(data[row + i][col] != '_' && data[row + i][col] != word.charAt(i)){
            return false;
          }
        else{
          data[row + i][col] = word.charAt(i);
        }
      }
      return true;
    }
    public boolean addWordDiagonal(String word, int row, int col){
      if (word.length() + row > data.length && word.length() + col > data[row].length && row > data.length && row < 0 && col < 0 && col > data[row].length){
        return false;
      }
      else{
        for(int i = 0; i < word.length(); i++){
          if(data[row + i] [col + i] != '_' && data[row + i][col + i] != word.charAt(i)){
            return false;
          }
          data[row + i][col + i] = word.charAt(i);
        }
        return true;
      }
    }
}
