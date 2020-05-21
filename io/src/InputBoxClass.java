package io;

import java.awt.*;
import java.awt.event.*;

public class InputBoxClass extends Dialog 
                                       implements ActionListener, TextListener
  {
  private  static final int MINWIDTH  =400;
  private  static final int FIELDWIDTH= 20;
  private  static final int STRING    =  1;
  private  static final int REAL      =  2;
  private  static final int INTEGER   =  3;
  private static final  int startx    =  200;
  private static final  int starty    =  250;

  	
  private  Label      messageLabel;
  private  TextField  inputField;
  private  Button     closebutton;
  private  String     inputString;
  private  double     inputDouble;
  private  int        inputInteger;
  private  int        inputType;
  private  boolean    gotString       = false;
  private  boolean    gotDouble       = false;
  private  boolean    okayForByeBye   = false;

    	
  public InputBoxClass()
    { 
    super( new Frame(), "", true);

    setupWindow();
    this.setPrompt( " ");

    }

  public InputBoxClass(String prompt)
    {
    super( new Frame(), "", true);
  		
    setupWindow();
    this.setPrompt( prompt);
    }

  public InputBoxClass( String prompt, cframe parent)
    {
    super( parent, "", true);
  		
    setupWindow();
    this.setPrompt( prompt);
    }

  private void setPrompt( String prompt)
    {
    int width;
  		
    width = prompt.length() * 12 + 40;
    if ( width < 250 )
        width = 250;
    	  	
    this.setSize( width, 150);
      		
    messageLabel.setText( prompt);
    }

  private void setupWindow()
    {

    Panel  closepanel, messagepanel, inputBoxPanel;

    inputString = "";
    setLocation( new Point( startx, starty));
    setBackground( new Color( (float)0.6, (float)0.6, (float)0.6));
    setFont( new Font( "Serif", Font.BOLD, 14));
    messageLabel = new Label();
    messageLabel.setAlignment(Label.CENTER);
    messagepanel = new Panel();
    messagepanel.add( messageLabel);
    inputField = new TextField( FIELDWIDTH);
    inputBoxPanel = new Panel();
    inputBoxPanel.add( inputField);

    closebutton = new Button("OK");
    closepanel = new Panel();
    closepanel.add( closebutton);
    add("North", messagepanel);
    add("South", closepanel);
    add("Center", inputBoxPanel);
    closebutton.addActionListener( this);
    inputField.addTextListener( this);
    this.setTitle("Input Dialog Box");

    }

  private void setTheFocus()
    {
    closebutton.transferFocus();
    inputField.requestFocus();
    }
  	
  public void actionPerformed ( ActionEvent evt)
    {
    int index;
    String eventstring;
    eventstring = evt.getActionCommand();
    if ( eventstring.equals("OK"))
       {
       if ( okayForByeBye)
          this.setVisible( false);
       else
          numericErrorMessage();
       }
    }
  
  public void textValueChanged( TextEvent evt)
    {
    inputString = inputField.getText().trim();
    gotString = ( inputString.length() > 0 );
    if ( gotString )
       {  			
       switch ( inputType)
         {
         case STRING:  okayForByeBye = gotString; 
                       break;
         case REAL  :  parseForReal();
                       okayForByeBye = gotDouble;
                       break;
         case INTEGER: parseForReal();
                       inputInteger = (int)Math.round(inputDouble);
                       okayForByeBye = gotDouble;
                       break;
         }
       }
    }
  
  public String getString()
    {
    inputType = STRING;
    this.setResizable( false);
    setLocation( new Point( startx, starty));
    setTheFocus();
    this.setVisible( true);

    return new String(inputString);
    }
  
  public double getDouble()
    {
  		
    inputType = REAL;
    this.setResizable( false);
    setLocation( new Point( startx, starty));
    setTheFocus();
    this.setVisible( true);
  	
    return inputDouble;
    }
  	
  
  public int getInt()
    {
  		
    inputType = INTEGER;
    this.setResizable( false);
    setLocation( new Point( startx, starty));
    setTheFocus();
    this.setVisible( true);
  	
    return inputInteger;
    }

  private void parseForReal()
    {
    Double middleMan;
					
    try
       {
        gotDouble = true;
        middleMan = new Double( inputString);
        inputDouble = middleMan.doubleValue();
        }
    catch ( NumberFormatException e )
        {
        gotDouble = false;
        }
    }

private void numericErrorMessage()
    {
    MessageBoxClass errorMessage;
    String errorStr = "Invalid format for number";
	
    if ( !gotDouble)
        {
        errorMessage = new MessageBoxClass(errorStr, true);
        inputField.setText("");
        inputString = "";
        }
    }  	
}
