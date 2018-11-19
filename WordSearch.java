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
    private char[][] solution;

    public WordSearch(int rows, int cols, String fileName){
      seed = (int)(Math.random()*100000);
      randgen = new Random(seed);
      data = new char[rows][cols];
      solution = new char[rows][cols];
      clear();
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      if(rows < 0 || cols < 0){
        System.out.println("Enter nonnegative arguments");
        System.exit(1);
      }
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String word = in.nextLine();
          wordsToAdd.add(word);
          System.out.println(word);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
      System.out.println(seed);
    }
    public WordSearch(int rows,int cols,String fileName, int randSeed){
      seed = randSeed;
      data = new char[rows][cols];
      solution = new char[rows][cols];
      randgen = new Random (randSeed);
      clear();
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      if(rows < 0 || cols < 0){
        System.out.println("Enter nonnegative arguments");
        System.exit(1);
      }
      if(randSeed < 0 || randSeed > 10000){
        System.out.println("Enter a seed in the range of 0-10000");
        System.exit(1);
      }
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String line = in.nextLine();
          wordsToAdd.add(line);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
      System.out.println(seed);
    }

    public WordSearch(int rows,int cols,String fileName, int randSeed, boolean answer){
      if(randSeed < 0 || randSeed > 10000){
        System.out.println("Enter a seed in the range of 0-10000");
        System.exit(1);
      }
      if(rows < 0 || cols < 0){
        System.out.println("Enter nonnegative arguments");
        System.exit(1);
      }
      seed = randSeed;
      randgen = new Random (randSeed);
      data = new char[rows][cols];
      solution = new char[rows][cols];
      clear();
      seed = randSeed;
      wordsAdded = new ArrayList<>();
      wordsToAdd = new ArrayList<>();
      try{
        File fi = new File(fileName);
        Scanner in = new Scanner(fi);
        while(in.hasNext()){
          String line = in.nextLine();
          wordsToAdd.add(line);
        }
      } catch (FileNotFoundException e){
        System.out.println("File not found" + fileName);
        System.exit(1);
      }
      addAllWords();
      if(answer == true){
        String s = key();
        System.out.println(s);
      }
    }
    public void fillIn(){
      for(int x = 0; x < data.length ; x++){
        for(int y = 0; y < data[x].length; y ++){
          if(data[x][y] == '_'){
            int s = 'A' + Math.abs(randgen.nextInt() % 26);
            data[x][y] = (char) s;
          }
        }
      }
      System.out.println(seed);
    }

    private String key(){
      String newstr = "";
      for(int i = 0; i < solution.length; i++){
	       newstr += "|";
         for(int x = 0; x < data[i].length; x++){
          if(solution[i][x] == '_'){
            solution[i][x] = ' ';
          }
          newstr+= solution[i][x] + " ";
        }
        newstr += "| \n";
        }
      return newstr;
    }


    private void clear(){
      for(int i = 0; i < data.length; i ++){
        for(int x = 0; x < data[i].length; x++){
          data[i][x] = '_';
        }
      }
      for(int i = 0; i < solution.length; i ++){
        for(int x = 0; x < solution[i].length; x++){
          solution[i][x] = '_';
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
         solution[row][col] = word.charAt(i);
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
     fillIn();
     return true;
   }


    public String toString(){
      String newstr = "";
      for(int i = 0; i < data.length; i++){
	       newstr += "|";
         for(int x = 0; x < data[i].length; x++){
          newstr+= data[i][x] + " ";
        }
        newstr += "| \n";
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
    public static void main(String[] args){
      if(args.length < 3){
        System.out.println("Need 3 or more arguments");
      }
      if(args.length == 3){
        WordSearch s = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        System.out.println(s);
      }
      if(args.length == 4){
        WordSearch s = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
        System.out.println(s);
      }
      if(args.length == 5){
        WordSearch s = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), Boolean.parseBoolean(args[4]));
        System.out.println(s);
      }
    }
}
