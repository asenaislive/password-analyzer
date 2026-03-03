package gui;

import main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class CheckerPanel extends JPanel implements ActionListener
{
    //REQUIRED GLOBAL VARAIBLES
    final int finaltile = 64;
    public final int panelrow = 12;
    public final int panelcolumn = 16;
    public final int screenwidth = panelcolumn * finaltile;
    public final int screenheight = panelrow * finaltile;

    private JLabel titleLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton analyzeButton;
    private JLabel resultLabel;
    private JLabel resultLabel2;
    private JProgressBar strengthBar;
    private JCheckBox showPassword;
    
    PasswordChecker pc;
    PasswordFile pf;
    
    public CheckerPanel()
    {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(new GridBagLayout());
        
        pc = new PasswordChecker();
        pf = new PasswordFile(pc);
        
        //DICTIONARY FOR PASSWORD CHECKING
        pf.declareDictionary();

        //TO START BUILDING THE UI
        buildUI();
    }
    
    //DRAWS SHAPES AND COLOURS OVER THE PANEL
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int i;
        
        // Two colors for gradient
        GradientPaint gp = new GradientPaint(0, 0, new Color(22, 78, 78), getWidth(), getHeight(), new Color(22, 160, 133));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        //DRAWS OVALS
        for (i = 0; i < 40; i++)
        {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int w = 20 + (int)(Math.random() * 60);
            int h = 20 + (int)(Math.random() * 60);

            Color randomColor = new Color(
                    100 + (int)(Math.random() * 100),
                    50 + (int)(Math.random() * 100),
                    150 + (int)(Math.random() * 100),
                    60 + (int)(Math.random() * 60)
            );

            g2d.setColor(randomColor);
            g2d.fillOval(x, y, w, h);
        }
        
        //DRAWS RECTANGLES
        for (i = 0; i < 40; i++)
        {
            int x = (int)(Math.random() * getWidth());
            int y = (int)(Math.random() * getHeight());
            int w = 20 + (int)(Math.random() * 60);
            int h = 20 + (int)(Math.random() * 60);

            Color randomColor = new Color(
                    100 + (int)(Math.random() * 100),
                    50 + (int)(Math.random() * 100),
                    150 + (int)(Math.random() * 100),
                    60 + (int)(Math.random() * 60)
            );

            g2d.setColor(randomColor);
            g2d.fillRect(x, y, w, h);
        }
        
        //DRAWS POLYGONS
        for (i = 0; i < 40; i++)
        {

            int x1 = (int)(Math.random() * getWidth());
            int y1 = (int)(Math.random() * getHeight());

            int x2 = x1 + (int)(Math.random() * 100 - 50);
            int y2 = y1 + (int)(Math.random() * 100 - 50);

            int x3 = x1 + (int)(Math.random() * 100 - 50);
            int y3 = y1 + (int)(Math.random() * 100 - 50);

            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};

            Color randomColor = new Color(
                    150 + (int)(Math.random() * 100),
                    80 + (int)(Math.random() * 100),
                    200,
                    40
            );

            g2d.setColor(randomColor);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        
        g2d.dispose();
    }

    //THIS IS WHERE THE UI IS BUILT
    private void buildUI()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(8, 0, 8, 0);

        //THE TITLE BAR
        titleLabel = new JLabel("Password Strength Analyzer");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(new Color(90, 150, 255));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        this.add(titleLabel, gbc);

        //A LABEL. YOU KNOW WHAT IT DOES
        passwordLabel = new JLabel("Enter your password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        passwordLabel.setForeground(new Color(148, 163, 184));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 6, 0);
        this.add(passwordLabel, gbc);

        //PASSWORD FIELD TO ACCEPT INPUT
        passwordField = new JPasswordField(22);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(350, 45));
        passwordField.setBackground(new Color(30, 41, 59));
        passwordField.setForeground(new Color(248, 250, 252));
        passwordField.setCaretColor(new Color(59, 130, 246));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 65, 85), 1),
            BorderFactory.createEmptyBorder(8, 14, 8, 14)
        ));
        
        //COLOR EFFECT FOR BORDER
        passwordField.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(90, 150, 255), 2),
        	    BorderFactory.createEmptyBorder(8, 14, 8, 14)
        	));
        
        passwordField.addActionListener(this);  // Enter key works
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        this.add(passwordField, gbc);

        //SHOW PASSWORD CHECKBOX
        showPassword = new JCheckBox("Show Password");
        showPassword.setForeground(new Color(180, 180, 180));
        showPassword.setBackground(new Color(30, 30, 40));
        showPassword.setOpaque(false);
        showPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected())
            {
                passwordField.setEchoChar((char) 0); //SHOWS PASSWORD
            } 
            else
            {
                passwordField.setEchoChar('•'); // HIDES PASSWORD
            }
        });
        
        gbc.gridy = 3; // right below password field
        gbc.insets = new Insets(2, 0, 10, 0);
        this.add(showPassword, gbc);
        
        
        // Analyze Button
        analyzeButton = new JButton("Analyze Strength");
        analyzeButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        analyzeButton.setPreferredSize(new Dimension(220, 45));
        analyzeButton.setForeground(Color.WHITE);
        analyzeButton.setBackground(new Color(37, 99, 235));
        analyzeButton.setBorder(BorderFactory.createEmptyBorder());
        analyzeButton.setFocusPainted(false);
        analyzeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        analyzeButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent e)
            {
                analyzeButton.setBackground(new Color(59, 130, 246));
            }
            public void mouseExited(java.awt.event.MouseEvent e)
            {
                analyzeButton.setBackground(new Color(37, 99, 235));
            }
        });

        analyzeButton.addActionListener(this);
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        this.add(analyzeButton, gbc);

        //STRENGTH BAR
        strengthBar = new JProgressBar(0, 100);
        strengthBar.setPreferredSize(new Dimension(350, 12));
        strengthBar.setBackground(new Color(30, 41, 59));
        strengthBar.setForeground(new Color(59, 130, 246));
        strengthBar.setBorderPainted(false);
        strengthBar.setValue(0);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 15, 0);
        this.add(strengthBar, gbc);

        //RESULT LABEL THAT DISPLAYS RESULT
        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel.setForeground(new Color(148, 163, 184));
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 8, 0);
        this.add(resultLabel, gbc);
        
        resultLabel2 = new JLabel(" ");
        resultLabel2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        resultLabel2.setForeground(new Color(148, 163, 184));
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 8, 0);
        this.add(resultLabel2, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Get password
        char[] passChars = passwordField.getPassword();
        String password = new String(passChars);
        password=password.toLowerCase();
        java.util.Arrays.fill(passChars, '\0');

        // Empty check
        if (password.isEmpty())
        {
            resultLabel.setText("Please enter a password");
            resultLabel.setForeground(new Color(245, 158, 11));
            strengthBar.setValue(0);
            return;
        }
        
        // RESET STATE 
        pc.reset();
        
        //RUN CHECKS TO DETERMINE RESULT
        String result1=pf.checkPasswordFile(password);
        String result2=pc.conditions(password);
        Color color = Color.RED;
        
        if(result1=="" && result2=="")
		{
        	pf.characterRepeatChecker(password);
            pc.strengthcheck(pc.entropy_bits);
            
            // UI DESIGN
            int barValue;
            
            switch (pc.strength) {
                case "Too Weak":
                    barValue = 10;
                    color = new Color(220, 38, 38);
                    break;
                case "Weak":
                    barValue = 25;
                    color = new Color(239, 68, 68);
                    break;
                case "Average":
                    barValue = 50;
                    color = new Color(245, 158, 11);
                    break;
                case "Strong":
                    barValue = 70;
                    color = new Color(34, 197, 94);
                    break;
                case "Very Strong":
                    barValue = 85;
                    color = new Color(59, 130, 246);
                    break;
                case "Extremely Strong":
                    barValue = 100;
                    color = new Color(168, 85, 247);
                    break;
                default:
                    barValue = 0;
                    color = new Color(182, 28, 28);
                    break;
            }
            
            resultLabel.setText(pc.strength);
            resultLabel.setForeground(color);
            resultLabel2.setText(null);
            strengthBar.setValue(barValue);
            strengthBar.setForeground(color);
		}
        else
        {
        	resultLabel.setText(result1);
        	resultLabel2.setText(result2);
        	
        	strengthBar.setValue(0);
        	resultLabel.setForeground(new Color(255, 24, 63));
        	resultLabel2.setForeground(new Color(255, 24, 63));
        }
    }
}