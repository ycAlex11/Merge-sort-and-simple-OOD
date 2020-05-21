package io;

import java.awt.*;
import java.awt.event.*;

public class MessageBoxClass extends Dialog implements ActionListener
{
  private Label   messagelabel; 
  private boolean errormessage=false;
  private int     height = 100;
    	
  public MessageBoxClass()
    {
    super( new Frame(), "", true);

    setupWindow();
    }

  public MessageBoxClass(String message)
    {
    super( new Frame(), "", true);
  		
    setupWindow();
    this.setMessage( message);
    }

  public MessageBoxClass(String message, boolean iserrormessage)
    {
    super( new Frame(), "", true);
  		
    errormessage = iserrormessage;
    setupWindow();
    this.setMessage( message);
    }

  public MessageBoxClass( String message, cframe parent)
    {
    super( parent, "", true);
  		
    setupWindow();
    this.setMessage( message);
    }

  public void setMessage( String message)
    {
    int width;
 		
    width = message.length() * 12 + 40;
    if ( width < 250 )
        width = 250;
    this.setSize( new Dimension(width, height));
  		
    messagelabel.setText( message);

    this.setVisible( true);
    }

  private void setupWindow()
    {

    Panel  closepanel, messagepanel;
    Button closebutton;


    this.setResizable( false);
    setLocation( new Point( 150, 150));
    if ( errormessage )
       setBackground( new Color( (float)0.9, (float)0.0, (float)0.0));
    else
       setBackground( new Color( (float)0.6, (float)0.6, (float)0.6));
    setFont( new Font( "Serif", Font.BOLD, 14));
    messagelabel = new Label();
    messagelabel.setAlignment(Label.CENTER);
    messagepanel = new Panel();
    messagepanel.add( messagelabel);
    closebutton = new Button("OK");
    closepanel = new Panel();
    closepanel.add( closebutton);
    add("Center", messagepanel);
    add("South", closepanel);
    closebutton.addActionListener( this);
    this.setResizable( true);
    this.setTitle("Output Dialog Box");

    }
  	
  public void actionPerformed ( ActionEvent evt)
    {
    int index;
    String eventstring;
    eventstring = evt.getActionCommand();
    if ( eventstring.equals("OK"))
        {
        this.setVisible( false);
        }
    }
}
