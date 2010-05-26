package at.tugraz.iicm.matrixexplorer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;


public class DataSetParser {

	private String[][] matrix;
	private String[] names;
	private int numberOfLines;
	private int numberOfColumns;
	
	public   DataSetParser() {
		parseDataSet("C:/Users/Marco/workspace/MatrixExplorer/onehr.data");
		parseNames("C:/Users/Marco/workspace/MatrixExplorer/eighthr.names");
	}
	
	/**
	 * This method return the names of the columns to be displayed
	 * @return names names of the columns
	 */
	public String[] getNames() {
		return names;
	}
	
	/**
	 * This method returns the parsed and initialized Matrix
	 * @return matrix the matrix to be displayed
	 */
	public String[][] getMatrix(){
		return matrix;
	}
	
	/**
	 * This function reads and parses the names of the columns
	 * of the data set
	 * @param filePath the path of the file with the columns names
	 */
	public void parseNames(String filePath){
		 try {
			FileInputStream fileStream = new FileInputStream(filePath);
			    
			    DataInputStream in = new DataInputStream(fileStream);
			    BufferedReader bufferedFileReader = new BufferedReader(new InputStreamReader(in));
			    String currentLine="";
			  
			    Vector<String> entireLine = new Vector<String>();
			    
			    while ((currentLine = bufferedFileReader.readLine()) != null)   {
			     
			      entireLine.add(currentLine);
			    }
			    
			    names = new String[entireLine.size()-2];
			    for(int i=2; i<entireLine.size();i++ )
			    	names[i-2]= entireLine.elementAt(i).split("\\:")[0];
			    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This function reads the data set from a file parses
	 * it and saves it in a matrix.
	 * @param filePath
	 */
	public void parseDataSet(String filePath){
		
		try{
		    
		    FileInputStream fileStream = new FileInputStream(filePath);
		    
		    DataInputStream in = new DataInputStream(fileStream);
		    BufferedReader bufferedFileReader = new BufferedReader(new InputStreamReader(in));
		    String currentLine="";
		  
		    Vector<String> entireLine = new Vector<String>();
		    
		    while ((currentLine = bufferedFileReader.readLine()) != null)   {
		     
		      entireLine.add(currentLine);
		    }
		    numberOfColumns = entireLine.elementAt(0).split("\\,").length;
		    numberOfLines=entireLine.size();
		    matrix = new String[numberOfLines][numberOfColumns];			    
		    in.close();
		    for(int i=0; i< entireLine.size();i++){
				String [] parsedLine=entireLine.elementAt(i).split("\\,");
				
				
				for(int j=0;j< parsedLine.length;j++)
					matrix[i][j]=parsedLine[j];
				
			 }
		    
		    

		    }catch (Exception e){
		    	System.out.println("The input cannot be read because it is not in the expected format!!");
		    }
	}
	
	/**
	 * This is is simple method to swap two rows with each other
	 * @param index1 the first row to be swapped
	 * @param index2 the second row to be swapped
	 */
	public void swapRows(int index1,int index2 ){
		String [] copyline = matrix[index1];
	    matrix[index1]= matrix[index2];
	    matrix[index2] = copyline;
	}
	
	/**
	 * This is a simple method to swap to columns with each other
	 * @param index1 the index of the first column to be swapped
	 * @param index2 the index of the second column to be swapped
	 */
	public void swapColumns(int index1, int index2){
		for(int i =0; i< numberOfLines; i++){
			String temp = matrix[i][index1];
			matrix[i][index1]= matrix[i][index2];
			matrix[i][index2]= temp;
		}
			
	}

	/**
	 * function to facilitate testing on the early development stage
	 * probably going to be deprecated after introduction of the GUI
	 */
	public void printMatrix() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
			for (int j = 0; j < numberOfColumns; j++)
				System.out.print(matrix[i][j]+"\t");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataSetParser parser = new DataSetParser();
		parser.swapColumns(2, 3);
		parser.swapRows(0, 1);
		parser.printMatrix();
		
		
	}

}
