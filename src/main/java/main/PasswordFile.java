package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PasswordFile
{
	public Set<String> dictionary;
	PasswordChecker pc;
	
	public PasswordFile(PasswordChecker pc)
	{
		this.pc=pc;
	}
	
			
	public void declareDictionary()
	{
		dictionary = new HashSet<String>();
		loadDictionary();
	}

	//LOADS DATA FROM FILE TO HASHSET FOR SEARCHING
	public void loadDictionary()
	{
		Scanner textFile=new Scanner(getClass().getResourceAsStream("/toppasswords/PCL_Top100000.txt"));
		
		while (textFile.hasNextLine())
         dictionary.add(textFile.nextLine().trim().toLowerCase());
		
		textFile.close();
	}

	//CHECKS FOR THE PASSWORD IN FILE
	public String checkPasswordFile(String user_pass)
	{	
			if(dictionary.contains(user_pass))
			{
				pc.strength="Too common. Consider using a stronger password";
				return pc.strength;
			}
			return "";
	}
	
	public void characterRepeatChecker(String user_pass)
	{
		 Map<Character, Integer> charCountMap = new HashMap<>();

	        //CONVERT THE STRING TO A CHAR ARRAY AND MOVE THROUGH THE INPUT
	        for (char c : user_pass.toCharArray())
	            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
	        
	        Set<Map.Entry<Character, Integer>> repeatSet = charCountMap.entrySet();
	        
	        for(Map.Entry<Character, Integer> repeat: repeatSet)
	        {
	        	if(repeat.getValue() == 2)
	        	{
	        		pc.entropy_bits *= 0.95;
	        		//System.out.println("At 2.");
	        	}
	        	
	        	else if(repeat.getValue() == 3)
	        	{
	        		pc.entropy_bits *= 0.90;
	        		//System.out.println("At 3.");
	        	}
	        	
	        	else if(repeat.getValue() == 4)
	        	{	
	        		pc.entropy_bits *= 0.85;
	        		//System.out.println("At 4.");
	        	}
	        	
	        	else if(repeat.getValue() > 4)
	        	{
	        		pc.entropy_bits *= 0.75;
	        		//System.out.println("At >4.");
	        	}
	        }
	}	        
}