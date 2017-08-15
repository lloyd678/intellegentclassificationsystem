import java.util.Scanner;

/**
 * Introduction Class
 * Prints out information at the start of the program and takes run options
 * This will also ask the user if they wish to classify another animal at the end
 *
 */
public class Introduction {

	public static boolean welcome(Scanner in) 
	{
			System.out.println("Animal Classification System.\rLloyd Wilson 12002554\r\r\r");
			System.out.println("This system will determine the type and speicies of an animal\r"
								+ "It can handle reptile, mammal, bird and fish \r\r\r");
			boolean verbose = BeginInput(in);
			return verbose;		
	}
	
	public static boolean BeginInput(Scanner in)
	{
		System.out.println("Type 'start' to Start,\r"
				+ "type 'verbose' to run in verbose mode\r"
				+ "or type 'help' for further info");
		String option = in.next();
		switch(option.toUpperCase())
   	 	{
   	 	case "HELP":
			System.out.println("Select an animal from the animal kingdom.\r"
							   + "This may be a reptile, bird, fish or mammal\r"
							   + "Answer the questions asked based on this animal"
							   + "and the system will determine its class and species.\r "
							   + "Verbose mode will output data from the algorithm whilst running/");
			BeginInput(in);
   	 	case "START":
   	 		return false;
   	 	case "VERBOSE":
	 		return true;
		}
		return false;
	}
	
	public static boolean Continue(Scanner in) 
	{
			System.out.println("Classify another animal?\r");
			System.out.println("Type Yes or No");
			String option = in.next();
			switch(option.toUpperCase())
	   	 	{
	   	 	case "YES":
	   	 		return true;
	   	 	case "NO":
		 		return false;
			}
			return false;	
	}
}
