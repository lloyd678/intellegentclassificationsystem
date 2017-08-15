import java.io.IOException;
import java.util.Scanner;

/**
 * Animal Classification Class
 * Main class of the program, calls the Question, Bayes
 * Classifier and Rule Based System Classes
 *
 */

public class AnimalClassification
{
	static Scanner in = new Scanner(System.in);
	static boolean reclassify = true;
	
	public static void main(String[] args) throws IOException
	{
		while(reclassify==true)
		{
			boolean verbose = Introduction.welcome(in);
			String[] user_input = AskQuestions.userInput(in);
			String[] animalCategory = findAnimalCategory(user_input, verbose);
			findAnimalType(animalCategory, verbose);
			reclassify = Introduction.Continue(in);
		}
	}

	private static String[] findAnimalCategory(String[] user_input, boolean verbose) throws IOException
	{
		BayesClassifier bc = new BayesClassifier();
		String[] animalCategory = bc.classifyAnimal(user_input, in, verbose);
		return animalCategory;
	}
	
	private static void findAnimalType(String[] animalCategory, boolean verbose) throws IOException
	{
		String legs = animalCategory[0];
		String skin = animalCategory[1];
		String size = animalCategory[2];
		String meat = animalCategory[3];
		String swim = animalCategory[4];
		String classification = animalCategory[5];
		
		TypePrediction tp = new TypePrediction();
		tp.Initialize(legs, skin, size, meat, swim, classification, in, verbose);
	}

}
