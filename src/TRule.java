/**
 * TRule Class
 * Definition of the Rule object for RBS
 *
 */
public class TRule 
{
    boolean          matched;
    int              weight;
    boolean 		cleared;
   
    String          antecedentLegs;
    String          antecedentSkin;
    String          antecedentSize;
    String          antecedentMeat;
    String          antecedentSwim;
    String          antecedentClassification;
    String          consequentType;
 
  public TRule() 
  {
	  matched = false;
	  weight = 0;
	  cleared = false;
  }
  
  public void SetRule(String[] importedRule)
  {  
	  
	   antecedentLegs = importedRule[0];
	   antecedentSkin = importedRule[1];
	   antecedentSize = importedRule[2];
	   antecedentMeat = importedRule[3];
	   antecedentSwim = importedRule[4];
	   antecedentClassification = importedRule[5];
	   consequentType = importedRule[6];
        
  }
  public void Clear()
  {
	   antecedentLegs = null;
	   antecedentSkin = null;
	   antecedentSize = null;
	   antecedentMeat = null;
	   antecedentSwim = null;
	   antecedentClassification = null;
	   consequentType = null;
	   cleared = true;
  }
}

