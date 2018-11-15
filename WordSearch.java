import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList; //random, scanner, arraylist
import java.io.File;
import java.io.FileNotFoundException; //file, filenotfoundexception

public class WordSearch{
    private char[][] data;
    private int seed;
    private Random randgen;
    private ArrayList<String>wordsToAdd;
    private ArrayList<String>wordsAdded;

    public WordSearch(int rows, int cols, String fileName){
      seed = (int)(Math.random()*100000);
      randgen = new Random(seed);
      data = new char[rows][cols];
      clear();
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String word = in.nextLine();
          wordsToAdd.add(word);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
    }
    public WordSearch(int rows,int cols,String fileName, int randSeed){
      seed = randSeed;
      data = new char[rows][cols];
      clear();
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String word = in.nextLine();
          wordsToAdd.add(word);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
    }

    public WordSearch(int rows,int cols,String fileName, int randSeed, boolean answer){
      seed = randSeed;
      data = new char[rows][cols];
      clear();
      seed = randSeed;
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String word = in.next();
          wordsToAdd.add(word);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
    }


    private void clear(){
      for(int i = 0; i < data.length; i ++){
        for(int x = 0; x < data[i].length; x++){
          data[i][x] = '_';
        }
      }
    }
    public boolean addWord( String word, int r, int c, int rowIncrement, int colIncrement){
      int row = r;
      int col = c;
      if (rowIncrement == 0 && colIncrement ==0){
	       return false;
	   }
     try{
       if(r < 0 || c < 0 || r >= data.length || c >= data[r].length){
         return false;
       }
       for( int i = 0; i < word.length(); i ++){
         if (data[row][col] != '_' && data[row][col]!= word.charAt(i)){
           return false;
         }
         row += rowIncrement;
         col += colIncrement;
       }
       row = r;
       col = c;
       for(int i = 0; i < word.length(); i++){
         data[row][col] = word.charAt(i);
         row += rowIncrement;
         col += colIncrement;
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

   public boolean addAllWords(){
     for(int i = 0; i < wordsToAdd.size(); i ++){
       int col = Math.abs(randgen.nextInt() % data.length );
       int row = Math.abs(randgen.nextInt() % data[0].length );
       int rowIncrement = randgen.nextInt() % 2;
       int colIncrement = randgen.nextInt() % 2;
       addWord(wordsToAdd.get(i), row, col, rowIncrement, colIncrement);
       i = i - 1;
     }
     return true;
   }


    public String toString(){
      String newstr = "";
      for(int i = 0; i < data.length; i++){
	newstr += "|";
        for(int x = 0; x < data[i].length; x++){
          newstr+= data[i][x] + " ";
        }
        newstr += "|/n";
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
