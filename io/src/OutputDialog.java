package io;

public class OutputDialog
{
  public static void output( String prompt)
    {
    int inValue;
    MessageBoxClass inBox = new MessageBoxClass( prompt);
    }
  public static void output( String messages[])
    {
    int inValue;
    MultiMessageBoxClass inBox = new MultiMessageBoxClass( messages);
    }
}    
