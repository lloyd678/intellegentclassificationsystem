import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * File Management Class
 * Reads and Writes to the training data files
 *
 */
public class FileManagement {
	
	public static void generateSaveArray(String input0,String input1,String input2,String input3,
										  String input4, String input5, String input6) throws IOException
	{
		String[] saveArray = new String[7];
        saveArray[0] = input0;
		saveArray[1] = input1;
		saveArray[2] = input2;
		saveArray[3] = input3;
		saveArray[4] = input4;
		saveArray[5] = input5;
		saveArray[6] = input6;
		savePrediction(saveArray);
		
	}
	
	private static void savePrediction(String[] savearray) throws IOException
	{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter( "AIData/classificationdata.txt", true));
		  String writestring = Arrays.toString(savearray).replace("[", "").replace("]", "").replace(" ","");
		  outputWriter.write(writestring);
		  outputWriter.newLine();
		  outputWriter.flush();  
		  outputWriter.close();  
	}
	
	public static int getFileLines(String filename) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("AIData/" + filename));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
		return lines;
	}
	
	public static String[][] readAnimalTrainingData() throws IOException
	{
		int lines = getFileLines("animaltrainingdata.txt");
		String[][] trainingData = new String[lines][6];
		int index = 0;
		File file = new File("AIData/animaltrainingdata.txt");
	    Scanner input = new Scanner(file);
		
		while (input.hasNextLine() && index < trainingData.length) {
		    trainingData[index] = input.nextLine().split(","); //split returns an array
		    index++;
		}
		input.close();
		return trainingData;
	}

	public static String[][] readClassificationData() throws IOException
	{
		int lines = getFileLines("classificationdata.txt");
		String[][] trainingData = new String[lines][7];
		int index = 0;
		File file = new File("AIData/classificationdata.txt");
	    Scanner input = new Scanner(file);
		while (input.hasNextLine() && index < trainingData.length) {
		    trainingData[index] = input.nextLine().split(","); //split returns an array
		    index++;
		}
		input.close();
		return trainingData;
	}
}
