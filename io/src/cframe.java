package io;

import java.awt.*;
import java.awt.event.*;

public class cframe extends Frame 
{

  public cframe(String Title)
    {
    mywdclass mywd = new mywdclass();

    setTitle(Title);
    addWindowListener( mywd);
    }
  public cframe()
    {
    mywdclass mywd = new mywdclass();

    addWindowListener( mywd);
    }

}

class mywdclass extends WindowAdapter
   {
   public void windowClosing( WindowEvent e)
      {
      System.exit(0);
      }
   }
