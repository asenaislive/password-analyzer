/*
 *   			MADE BY ASENAISLIVE
 *   
 *   
 *   LinkedIn: https://www.linkedin.com/in/akhil-mycherla-1757b3365/
 *   GitHub: https://github.com/asenaislive
 *   
 */

package main;

import gui.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PasswordChecker
{	
	//GLOBAL VARIABLES
	public String user_pass;
	public double entropy_bits;
	public int pass_len;
	public String strength;
	public boolean ctrltr = false, ctrspl=false, ctrnum=false;
	
	PasswordFile pf=new PasswordFile(this);
	
	//RESETS ALL VALUES NEEDED FOR NEXT USAGE
	public void reset()
    {
        user_pass = "";
        pass_len = 0;
        entropy_bits = 0;
        strength = "";
        ctrltr= false;
        ctrnum = false;
        ctrspl = false;
    }

	//NECESSARY CONDITIONS THAT THE GIVEN PASSWORD SHOULD PASS
	public String conditions(String user_pass)
	{
		calculateEntropy(user_pass);
		int i;
		pass_len=user_pass.length();
		char pass_letter;

		//EXITS IF LENGTH LESS THAN 12
		if(pass_len<12)
		{
			strength="The specified length has not been reached.";
			return strength;
		}
		

		for (i=0; i<this.pass_len; i++)
		{
			pass_letter=user_pass.charAt(i);
			
			//CONDITIONS FOR SMALL< CAPITAL AND SPECIAL LETTERS
				if(Character.isLetter(pass_letter))
					ctrltr = true;
				else if(Character.isDigit(pass_letter))
					ctrnum = true;
				else 
					ctrspl = true;
	
				/*
				EXITS AS SOON AS ALL THREE CONDITIONS ARE SATISFIED
				CONDITIONS: IT SHOULD HAVE ATLEAST
				1. ONE LETTER
				2. ONE DIGIT
				3. ONE SPECIAL CHARACTER
				*/
	
				if(ctrltr && ctrnum && ctrspl)
					return "";
		}	

		strength="The password doesn't meet the required conditions";
		return strength;
	}

	//CHECKS THE BITS AND RANKS PASSWORD ACCORDINGLY
	public void strengthcheck(double entropy_bits)
	{
		
		if(entropy_bits<28)
			strength="Too Weak";

		else if(entropy_bits>=28 && entropy_bits<=35)
			strength="Weak";

		else if(entropy_bits>35 && entropy_bits<=59)
			strength="Average";

		else if(entropy_bits>59 && entropy_bits<80)
			strength="Strong";

		else if(entropy_bits>=80 && entropy_bits<90)
			strength="Very Strong";

		else if(entropy_bits>=90)
			strength="Extremely Strong";
		
	}
	
    public void calculateEntropy(String user_pass)
    {
        int poolSize = 0;
        
        ctrltr = false;
        ctrnum = false;
        ctrspl = false;
        
        for (int i = 0; i < user_pass.length(); i++)
        {
            char pass_letter = user_pass.charAt(i);
            
          //CONDITIONS FOR LETTERS, NUMBERS AND SPECIAL CHARACTERS
			if(Character.isLetter(pass_letter))
				ctrltr = true;
			else if(Character.isDigit(pass_letter))
				ctrnum = true;
			else 
				ctrspl = true;
        }
        
        if (ctrltr) 
        	poolSize += 26;
        if (ctrnum)
        	poolSize += 10;
        if (ctrspl)
        	poolSize += 32;
        
        //ENTROPY ESTIMATE IS CALCULATED
        if (poolSize > 0 && user_pass.length() > 0)
            entropy_bits = user_pass.length() * (Math.log(poolSize) / Math.log(2));
        else
            entropy_bits = 0;
        
        // BONUS FOR LONGER PASSWORDS
        if (user_pass.length() >= 12)
        	entropy_bits += 8;
        if (user_pass.length() >= 16)
        	entropy_bits += 8;
        if (user_pass.length() >= 20)
        	entropy_bits += 8;
    }
    
    //JUST TO PRINT MY NAME - DON'T MIND THIS
    public void printBanner() throws Exception
    {
        InputStream is = PasswordChecker.class.getResourceAsStream("/name/name.txt");

        if (is == null)
        {
            System.out.println("Name not found.");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = reader.readLine()) != null)
        {
            System.out.println(line);
        }

        reader.close();
    }
    
    //ASKS IF YOU WANT CLI OR GUI
	public void callmethods()throws Exception
	{	
		Scanner cx=new Scanner(System.in);
		
		printBanner();
		Thread.sleep(3000);
		System.out.print("Which interface do you want to use? (G for GUI / C for CLI): ");
		char input= cx.next().charAt(0);
		cx.nextLine(); //CLEARS INPUT BUFFER
		input=Character.toUpperCase(input);
		
		if(input == 'G')	
		{
			//THESE ARE FOR GUI
			System.out.println("Starting GUI mode");
			new PasswordCheckerGUI();
			new CheckerPanel();
		}

		else if(input == 'C')
		{
			//THIS IS FOR CLI
			System.out.println("Starting CLI mode");
			new PasswordCheckerCLI(this, pf);
		}
 
		else
		{
			//CALLS GUI FOR INCORRECT INPUT
			System.out.println("Incorrect input. Starting CLI mode");
			new PasswordCheckerCLI(this, pf);
		}

		cx.close();
	}


	public static void main(String args[]) throws Exception
	{
		PasswordChecker pm=new PasswordChecker();
		pm.callmethods();
	}
}