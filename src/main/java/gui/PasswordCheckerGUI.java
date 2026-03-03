package gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class PasswordCheckerGUI extends JFrame
{
	public PasswordCheckerGUI()
	{
		PCPanel();
	}
	
	//A BASIC FRAME TO CONTAIN EVERYTHING
	public void PCPanel()
	{
		CheckerPanel cp=new CheckerPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setResizable(false);
    	setTitle("Asena's Password Strength Analyzer");
    	
    	ImageIcon icon = new ImageIcon(getClass().getResource("/name/lock_icon.png"));
    	setIconImage(icon.getImage());
    	
    	add(cp);
    	pack(); //THE FRAME GETS ADJUSTED TO SIZE OF THE PANEL
    	setLocationRelativeTo(null);
    	setFocusable(true);
    	setVisible(true);
    	
    	//TO APPEAR ON TOP
		toFront();
		requestFocus();
		setAlwaysOnTop(true);
		setAlwaysOnTop(false);
	}
}
