/**
 * Working Memory
 * Stores the users input as a rule using get and set methods
 *
 */
public class workingMemory
{	
    public static String attrLegs;
    public static String attrSkin;
    public static String attrSize;
    public static String attrMeat;
    public static String attrSwim;
    public static String attrClass;
    public static String attrType;
    
    public static void setLegs (String legs)
    {
    	attrLegs = legs;
    }
    
    public static void setSkin (String skin)
    {
    	attrSkin = skin;
    }
    
    public static void setSize (String size)
    {
    	attrSize = size;
    }
    
    public static void setMeat (String meat)
    {
    	attrMeat = meat;
    }
    
    public static void setSwim (String swim)
    {
    	attrSwim = swim;
    }
    
    public static void setClassification (String classification)
    {
    	attrClass = classification;
    }
    
    
    public static void setType (String type)
    {
    	attrType = type;
    }
    
    public static String getLegs ()
    {
    	return attrLegs;
    }
    
    public static String getSkin ()
    {
    	return attrSkin;
    }
    
    public static String getSize()
    {
    	return attrSize;
    }
    public static String getMeat ()
    {
    	return attrMeat;
    }
    
    public static String getSwim ()
    {
    	return attrSwim;
    }
    
    public static String getClassification()
    {
    	return attrClass;
    }

    public static String getType()
    {
    	return attrType;
    }

}
