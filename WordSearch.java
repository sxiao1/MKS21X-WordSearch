public class WordSearch{
    private char[][]data;
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int i = 0; i < rows; i ++){
        for(int x = 0; x < cols; x++){
          data[i][x] = '_';
        }
      }
    }

    private void clear(){
      for(int i = 0; i < data.length; i ++){
        for(int x = 0; x < data[i].length; x++){
          data[i][x] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String newstr = "";
      for(int i = 0; i < data.length; i++){
        if(i > 0){
          newstr += "\n";
        }
        for(int x = 0; x < data[i].length; x++){
          newstr+= data[i][x] + " ";
        }
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
