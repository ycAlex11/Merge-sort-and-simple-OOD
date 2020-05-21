package io;

import java.awt.*;
import java.awt.event.*;

public class MultiMessageBoxClass extends Dialog implements ActionListener
{
  private TextArea textArea;
  private boolean errormessage=false;
  private int     height = 100;
    	
  public MultiMessageBoxClass(String [] message)
    {
    super( new Frame(), "", true);
  		
    setupWindow( message);
    this.setVisible( true);
    }

  public MultiMessageBoxClass( String message [], cframe parent)
    {
    super( parent, "", true);
  		
    setupWindow( message);
    this.setVisible( true);
    }

  private int findMaxLength( String messages[])
    {
    int maxLength;

    maxLength = messages[0].length();
    for( int i=1; i<messages.length; i++)
       if ( maxLength < messages[i].length() )
           maxLength = messages[i].length();

    return maxLength;
    }

  private void setupWindow( String messages[])
    {
    int width, height;
    Panel  closepanel, messagepanel;
    Button closebutton;

    height = messages.length * 15;
    width  = findMaxLength( messages);
    this.setResizable( false);
    setLocation( new Point( 150, 150));
    if ( errormessage )
       setBackground( new Color( (float)0.9, (float)0.0, (float)0.0));
    else
       setBackground( new Color( (float)0.6, (float)0.6, (float)0.6));
    setFont( new Font( "Serif", Font.BOLD, 14));
    textArea = new TextArea( height, width);
    for(int i=0; i<messages.length; i++)
       textArea.append( messages[i].trim() + "\n");
    textArea.setEditable( false);
    messagepanel = new Panel();
    messagepanel.add( textArea);
    closebutton = new Button("OK");
    closepanel = new Panel();
    closepanel.add( closebutton);
    add("Center", messagepanel);
    add("South", closepanel);
    closebutton.addActionListener( this);
    this.setResizable( true);
    this.setTitle("Output Dialog Box");
    this.setSize( width * 10 + 60, height + 100);
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
