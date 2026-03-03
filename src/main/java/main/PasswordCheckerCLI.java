package main;

import java.util.Scanner;

//CLI INTERFACE
public class PasswordCheckerCLI extends PasswordChecker
{
	PasswordFile pf;
	PasswordChecker pc;
	
	public PasswordCheckerCLI(PasswordChecker pc, PasswordFile pf)
	{
        this.pf = pf;
        this.pc = pc;
        CLIChecker();
    }

	public void CLIChecker()
	{
		Scanner cx=new Scanner(System.in);
		
		do
		{
			System.out.println("CONDITIONS: THE PASSWORD SHOULD HAVE ATLEAST\r\n"
					+ "				1. ONE LETTER\r\n"
					+ "				2. ONE DIGIT\r\n"
					+ "				3. ONE SPECIAL CHARACTER");
			
			System.out.print("Enter your password (at least 12 - 15 characters long. Type quit to exit): ");
			user_pass=cx.nextLine();
			
			if(user_pass.equalsIgnoreCase("quit"))
			{
				System.out.println("Exiting..");
				System.exit(0);
				cx.close();
			}
			
			pass_len = user_pass.length();
			
			entropy_bits = pass_len * (Math.log(94)/Math.log(2));
			
			//STORES DATA FROM FILE TO HASHSET
			pf.declareDictionary();
			
			//CHECKS IF THE GIVEN PASSWORD HAS A MATCHING COMBINATION
			String result1=pf.checkPasswordFile(user_pass);
			
			//CHECKS IF THE PASSWORD PASSES BASIC CONDITIONS
			String result2=conditions(user_pass);
			
			if(result1=="" && result2=="")
			{
				//CHECKS IF THE PASSWORD HAS ANY REPETITIONS AND DECREASES PASSWORD STRENGTH
				pf.characterRepeatChecker(user_pass);
				strengthcheck(entropy_bits);
				System.out.println("Password strength: "+strength);
				System.out.println();
				System.out.println();
				System.out.println();
			}
			
			else
			{
				System.out.println();
				System.out.println();
				System.out.println(result1);
				System.out.println(result2);
				System.out.println();
				System.out.println();
				System.out.println();
			}
			pc.reset();
		}while(true);
	}
}
