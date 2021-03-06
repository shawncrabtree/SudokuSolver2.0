import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SudokuBoard {
	
	public Tile[][] board;
	
	public SudokuBoard(){
		
	}
	
	public SudokuBoard(String fileName){
		
		try{
			String line;
			FileReader fileReader = new FileReader("src/"+fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			board = new Tile[9][9];
			int i = 0;
            while((line = bufferedReader.readLine()) != null) {
            	char[] chars = line.toCharArray();
            	for(int j = 0; j < line.length(); j++){
            		board[i][j] = new Tile(chars[j], i, j);
            	}
            	i++;
            }   
            bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	
	public void print(){
		System.out.println();
		for(Tile[] row : board){
			for(Tile t : row){
				t.print();
				if(t.column == 2 || t.column == 5){
					System.out.printf("|");
				}
			}
			System.out.println();
			if(row[0].row == 2 || row[0].row == 5){
				System.out.println("-----------------------------");
			}
		}
		System.out.println();
	}
	
	
	public Set<Integer> getRowValues(Tile[] row){
		return new HashSet<Integer>(getRowList(row));
	}
	
	public ArrayList<Integer> getRowList(Tile[] row){
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(Tile tile : row){
			values.add(tile.getValue());
		}
		return values;
	}
	
	public Set<Integer> getColumnValues(int column){
		return new HashSet<Integer>(getColumnList(column));
	}
	
	public ArrayList<Integer> getColumnList(int column){
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(Tile[] row : board){
			values.add(row[column].getValue());
		}
		return values;
	}

	public Set<Integer> getBoxValues(int boxNumber){
		return new HashSet<Integer>(getBoxList(boxNumber));
	}
	
	public ArrayList<Integer> getBoxList(int boxNumber){
		Integer startCol = (boxNumber % 3) * 3;
		Integer startRow = (boxNumber / 3) * 3;
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for(int row = startRow; row < startRow+3; row++){
			for(int col = startCol; col < startCol+3; col++){
				values.add(board[row][col].getValue());
			}
		}
		
		return values;
	}
	
	public Set<Integer> getBoxValues(Tile tile){
		return getBoxValues(tile.boxNumber); 
	}
	
	public Boolean isCompleted(){
		return Validator.isCompleted(this);
	}

	
	
	
	

}
