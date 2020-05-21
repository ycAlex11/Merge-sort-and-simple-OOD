package io;

public class InputDialog
{
  public static double inputDouble( String prompt)
    {
    double inValue;
    InputBoxClass inBox = new InputBoxClass( prompt);

    inValue = inBox.getDouble();

    return inValue;
    }

  public static int inputInt( String prompt)
    {
    int inValue;
    InputBoxClass inBox = new InputBoxClass( prompt);

    inValue = inBox.getInt();

    return inValue;
    }

  public static String inputString( String prompt)
    {
    String inValue;
    InputBoxClass inBox = new InputBoxClass( prompt);

    inValue = new String(inBox.getString());

    return inValue;
    }

  public static char inputChar( String prompt)
    {
    String inValue;
    InputBoxClass inBox = new InputBoxClass( prompt);

    inValue = new String(inBox.getString());

    return inValue.charAt(0);
    }
}    

