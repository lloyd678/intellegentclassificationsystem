import java.io.IOException;
import java.util.Scanner;

/**
 * Bayes Classifier
 * Uses Naive Bayes to take the training data and predict the category
 * of the users input using probability
 *
 */
public class BayesClassifier 
{	
	private static double m;
	private static double p;
	private static int num_attr = 5;
	private static int train_size = 32;
	private static int num_category = 4;

	public BayesClassifier()
	{
		 m = 3.0; //aribtary value
		 p = 0.25; //1/num of poss outcomes
	}
	
	public static double CalculateProbability(String inputAnimal[], String category) throws IOException 
	{
		String[][] animaltrainingdata = FileManagement.readAnimalTrainingData();
	    int count[] = new int[num_attr];
	    for (int i=0; i<num_attr; i++)
	    	count[i] = 0;
	    
	    double p_category = 0.0;
	    int num_category = 0;
	    
	    for (int j=0; j<train_size; j++)	
			if (category.equals(animaltrainingdata[j][num_attr]))
			    	num_category ++;
		
	    p_category = (double)num_category/(double)train_size;

	    
		for (int i=0; i<num_attr; i++)
		{
			for (int j=0; j<train_size; j++)	
			    if ((inputAnimal[i].equals(animaltrainingdata[j][i])) && (category.equals(animaltrainingdata[j][num_attr])))
			    	count[i] ++;
			
		    p_category *=  ((double)count[i] + m * p)/((double)num_category + m);
		    
		}
		System.out.println("P category: " + p_category);
		return p_category;
	 
		
	}
	 
	 public String [] classifyAnimal(String[] userinput, Scanner in, boolean verbose) throws IOException 
	 {
		 String category[] = {"reptile", "mammal", "bird", "fish"};
		 String [] classifyAnimal = guessAnimal(userinput,category,in,num_category, verbose);
		 return classifyAnimal;   
	  }

	private static String [] guessAnimal(String[] inputAnimal, String[] category, Scanner in, int num_category, boolean verbose) throws IOException  
	{
		   double result[] = new double[num_category];
		   String[] successArray = new String[6];
		   for (int i=0; i<num_category; i++)
			 {
			   result[i] = BayesClassifier.CalculateProbability(inputAnimal, category[i]);
			   if (verbose==true)
		    	 {
		    		 System.out.println("PROBABILITY OF " + category[i]+ " IS " + result[i]);
		    	 }
			 
			 }
		   double max = -1000.0;
		   int max_position = -1;
		   for (int i=0; i<num_category; i++)
		   if (result[i]> max)
			 { 
				 max_position = i;
				 max = result[i];
				 
			 }
		    
		    System.out.println("I think this animal is some sort of " + category[max_position]);
		    System.out.println("Is this Animal a " + category[max_position] + "?");
			System.out.println("Enter yes or no");
		    String correct = in.next(); 
		    switch(correct.toUpperCase())
	   	 	{
	   	 	case "YES":
			   	 	successArray[0] = inputAnimal[0];
					successArray[1] = inputAnimal[1];
					successArray[2] = inputAnimal[2];
					successArray[3] = inputAnimal[3];
					successArray[4] = inputAnimal[4];
					successArray[5] = category[max_position];
					System.out.println("The Animal is a " + category[max_position] + "." );
					return successArray;
	   	 	case "NO":
	   	 		System.out.println("I was wrong!, Can I guess again?");
				System.out.println("Enter yes or no");
		   	 	String repeat = in.next(); 
		   	 	switch(repeat.toUpperCase())
		   	 	{
		   	 	case "YES":
		   	 		int new_num_cat = num_category - 1;
		   	 		String[] new_category_list = new String[new_num_cat];
		   	 		int x = 0; 
			   	 	for (int i = 0; i < num_category; i++) {
			   	 		if (category[i] != category[max_position])
			   	 		{	
			   	 			new_category_list[x] = category[i];
			   	 			x = x + 1;
			   	 		}
			   		}
			   	 	if (new_num_cat > 0)
			   	 	{
			   	 		String[] retrysuccessArray = guessAnimal(inputAnimal, new_category_list,in, new_num_cat, verbose);
			   	 		return retrysuccessArray;
			   	 	}
			   	 	else
			   	 	{
			   	 		System.out.println("Animal Not Classified");
			   	 		break;
			   	 	}
		   	 	case "NO":
		   	 		break;
		   	 		
		   	 	}
	   	 	}
		 return successArray;
	}
}
