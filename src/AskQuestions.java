import java.util.Scanner;
/**
 * Questions Class
 * Prints out questions and takes the users input
 *
 */
public class AskQuestions
{
	public static String[] userInput(Scanner in) 
	{
		// Find number of legs
			System.out.println("How many legs does the animal have?\rEnter 0 , 2 or 4");
			String legs = in.next();
		// Find skin type
			System.out.println("What kind of skin does the animal have?\rEnter skin, fur, scales or feathers");
			String skin_input = in.next();
			String skin = skin_input.toLowerCase();
		// Find size
			System.out.println("What size is the animal?\rEnter small (smaller than a domestic pet)\rmedium (domestic pet - human sized) \ror large (larger than a human)");
			String size_input = in.next();
			String size = size_input.toLowerCase();
		// Find eats meat
			System.out.println("Is the animal a meat/fish eater?\rEnter yes or no");
			String meatEater_input = in.next();
			String meatEater = meatEater_input.toLowerCase();
		// Find if can swim
			System.out.println("Can the animal swim?\rEnter yes or no");
			String swim_input = in.next();
			String swim = swim_input.toLowerCase();
		// Generate and return array
			String[] inputAnimal = {legs,skin,size,meatEater,swim};
			return inputAnimal; 

	}

}
