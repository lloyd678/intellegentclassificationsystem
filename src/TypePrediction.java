import java.io.IOException;
import java.util.Scanner;
/**
 * Type Prediction Class
 * Expert Rule Based System, takes input
 * from Bayes Classifier and further categorises
 *
 */
public class TypePrediction
{
     int             PreviousRuleFired;
     String          Prediction = "";
     String        	 RandomPrediction;
     int             N;
     int             NSuccess;
     int             NRandomSuccess;
     
     public void Initialize(String legs, String skin, String size, String meat, String swim, String classification, Scanner in, boolean verbose) throws IOException
     {
    	 final int NUM_RULES  = FileManagement.getFileLines("classificationdata.txt");
    	 
    	 if (verbose==true)
    	 {
    		 System.out.println("VB: READING NUMBER OF LINES\r");
    	 }
    	 
    	 TRule   Rules[] = new TRule[NUM_RULES];
    	 for (int i = 0; i<NUM_RULES; i++)
    	 {
    		 TRule Rule = new TRule();
        	 Rules[i]=Rule;
    	 }
    	 
    	String[][] importedRules = FileManagement.readClassificationData();
    	 if (verbose==true)
    	 {
    		 System.out.println("VB: READING FILE, CREATING RULES\r");
    	 }
    	for( int i = 0; i <= importedRules.length - 1; i++)
    	{
    			Rules[i].SetRule(importedRules[i]);
    	}
    	
    	
         workingMemory.setLegs(legs);
         workingMemory.setSkin(skin);
         workingMemory.setSize(size);
         workingMemory.setMeat(meat);
         workingMemory.setSwim(swim);
         workingMemory.setClassification(classification);
         PreviousRuleFired = -1;

         N = 0;
         NSuccess = 0;
         ProcessAnimal(in,NUM_RULES,Rules, verbose);
         NRandomSuccess = 0;
           
     }
     
     private void WeightTrainingData(TRule[] Rules, int NUM_RULES, boolean verbose) 
     {
    	 for( int i = 0; i <= NUM_RULES -1 ; i++)
     		{
    		    for( int j = 0; j <= NUM_RULES - 1; j++)
    		    {
    		    	if  (Rules[i].cleared == false && i != j)
    		    	{
    		    		if(
						  Rules[i].antecedentLegs.equals(Rules[j].antecedentLegs) &&
						  Rules[i].antecedentSkin.equals(Rules[j].antecedentSkin) &&
						  Rules[i].antecedentSize.equals(Rules[j].antecedentSize) &&
						  Rules[i].antecedentMeat.equals(Rules[j].antecedentMeat) &&
						  Rules[i].antecedentSwim.equals(Rules[j].antecedentSwim) &&
						  Rules[i].antecedentClassification.equals(Rules[j].antecedentClassification) &&
						  Rules[i].consequentType.equals(Rules[j].consequentType)
						 )
			  			{
			  				Rules[j].Clear();
			  				Rules[i].weight = Rules[i].weight + 1;
			  				 if (verbose==true)
			  		    	 {
			  		    		 System.out.println("VB: WEIGHT OF RULE" + i +"INCREASED\r");
			  		    	 }
				
			  			}
    		    	}
    		    }
     	}
	}

	public String ProcessAnimal(Scanner in, int NUM_RULES, TRule[] Rules, boolean verbose) throws IOException
     {
         int     i;
         int     RuleToFire = -1;

         // total and adjust weights...
         N++;
         WeightTrainingData(Rules, NUM_RULES, verbose);
 
         // make prediction using forward chaining ...
         for(i=0; i<NUM_RULES; i++)
         {
        	 if (Rules[i].cleared == false)
        	 {
                 if(Rules[i].antecedentLegs.equals(workingMemory.getLegs()) &&
                    Rules[i].antecedentSkin.equals(workingMemory.getSkin()) &&
                 	Rules[i].antecedentSize.equals(workingMemory.getSize()) &&
                 	Rules[i].antecedentMeat.equals(workingMemory.getMeat()) &&
                 	Rules[i].antecedentSwim.equals(workingMemory.getSwim()) &&
                 	Rules[i].antecedentClassification.equals(workingMemory.getClassification())
                 	)
                 	{
                         Rules[i].matched = true;
		                 if (verbose==true)
		            	 {
		            		 System.out.println("VB: RULE " + i +" MATCHED");
		            	 }
                 	}
                 else
                 {
                         Rules[i].matched = false;
                 }
        	 }
         }
         // pick the matched rule with the highest weight...
         RuleToFire = -1;
         for(i=0; i<NUM_RULES; i++)
         {
             if(Rules[i].matched)
             {
                     if(RuleToFire == -1)
                             RuleToFire = i;
                     else if(Rules[i].weight > Rules[RuleToFire].weight)
                             RuleToFire = i;
             }
         }
         
         // fire the rule
         if(RuleToFire != -1) 
         {
             workingMemory.setType(Rules[RuleToFire].consequentType);
             PreviousRuleFired = RuleToFire;
             Prediction = workingMemory.getType();
         } 
         else 
         {
             workingMemory.setType("Unknown");
             PreviousRuleFired = -1;
         }
         if (verbose==true)
    	 {
    		 System.out.println("VB: FIRING RULE: " + RuleToFire + " WITH WEIGHT: " + Rules[RuleToFire].weight );
    	 }
         
         String legs =  workingMemory.getLegs();
         String skin =  workingMemory.getSkin();
         String size =  workingMemory.getSize();
         String meat =  workingMemory.getMeat();
         String swim =  workingMemory.getSwim();
         String classification =  workingMemory.getClassification();
         String type =  workingMemory.getType();
         if (type == "Unknown")
         {
        	 System.out.println("Unable to predict animal species");
        	 return type;
         }

         
         System.out.println("I predict that this " + classification + " is a type of " + workingMemory.getType() +".");
         System.out.println("Was this prediciton correct?");
         String correct = in.next(); 
		 if(correct.equalsIgnoreCase("Yes"))
		 {
			 System.out.println("Correct. Results Saved");
			 FileManagement.generateSaveArray(legs, skin, size, meat, swim, classification, type); 
		 }
		 else
		 {
			 System.out.println("Incorrect. Discarding Results");
		 }
    
         return type;
                
     }

}
