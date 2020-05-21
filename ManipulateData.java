
/* Performs any actions that directly manipulate the data other then sort.
	 These include Adding and deleting rows.*/

import io.*;
import java.util.*;
import java.io.*;

public class ManipulateData
{
	//Add a row to data if valid (all data double, columsn of added <= columns of data)
	public static String[] addTheRow(String[] inData, int inRowNumber, int inColumnNum)
	{
		String userInput;	//Holds the user innputted row to add
		String addData = "";
		StringTokenizer token = null;
		int tokenNum = 0;

		//Control flag to set to flase if data is not double.
		boolean isDataDouble = true;

		//Gets user input string for array
		System.out.printf("Enter data in csv format for new row: ");
		userInput = ConsoleInput.readLine();

		//Tokenizes user string with delimeter , and " "
		token = new StringTokenizer(userInput, ", ");

		System.out.printf("*******************************************************\n");

		//Loops while there are more tokens in the string and while
		//All tokens are doubles, if non double value is found
		//Sets isDataDouble to flase thereby ending loop.
		//Also counts number of columns in the row.
		while(token.hasMoreTokens() && isDataDouble)
		{
			isDataDouble = isNumeric(token.nextToken());
			tokenNum++;
		}
		
		//If all data in the row is double enters this block	
		if(isDataDouble)
		{
			//If the number of columns of the input match the number of
			//Columns in the data row is added.
			if(tokenNum == inColumnNum)
			{
				inData[inRowNumber] = userInput;
				System.out.printf("Row added!\n");
			}

			//If the number of columns in the input is less than the 
			//Number of columns in data, then append ,0 for the remaining
			//columns of the input row, then add row to data.
			if((tokenNum < inColumnNum) && (tokenNum != 0))
			{
				for(int i = 0; i <= inColumnNum - tokenNum - 1; i++)
				{
					addData = addData + ",0";
				}
				inData[inRowNumber] = userInput + addData;

				System.out.printf("Row added!\n");
			}

			//If the number of columns of the user input is greater then the number of columns in data
			//Outputs error and no new row is added
			if(tokenNum > inColumnNum)
			{
				System.out.printf("Input has too many columns, did not add row!\n");
			}

			//If no data is entered as the user input, no new row is added, outputs error.
			if(tokenNum == 0)
			{
				System.out.printf("Can not enter nothing into a row, no new row added.\n");
			}
		}
		else //If any token in data is not a double outputs error, no new row added.
		{
			System.out.println("can not add new row.");
		}	

		return inData; //Exports the array back whether unchanged or modified.
	}

	//Deletes a row of data if that row exists and is not the header row.
	public static String[] deleteTheRow(String[] inData, int inRowNumber)
	{
		int deleteRowNum;

			//Gets user input row number for deletion.
			System.out.printf("Which row would you like to delete?: ");
			deleteRowNum = ConsoleInput.readInt();

		//Checks if user input rownumber is valid for deletion 
		if((deleteRowNum > 0) && (deleteRowNum < inRowNumber)) 
		{
			//Moves all elements up starting from the row after 
			//User input, A[1] = A[2], A[2] = A[3] etc. Thus deleting 
			//the row.
			for (int i = deleteRowNum; i < inRowNumber; i++)
			{
				inData[i] = inData[i + 1];
			}

			//Sets last used row to null as all rows have moved up one element.
			//Due to one row being deleted.
			inData[inRowNumber - 1] = null;	

			System.out.printf("*******************************************************\n");
			System.out.printf("Row %d has been deleted!\n", deleteRowNum);
		}
		else //User chose an out of bounds row
		{
			System.out.printf("*******************************************************\n");
			System.out.printf("Can not delete, not a valid row!\n");
		}

		//Exports the array back whether unchanged or modified.
		return inData;
	}

	//Method used to check if a string is a double.
	private static boolean isNumeric(String str)  
	{  
  	try  
  	{  
   		double d = Double.parseDouble(str);  
  	}  
  	catch(NumberFormatException e)  //Output error&&false when data is not a double
  	{  
			System.out.printf("Non numeric data found, ");
    	return false;  
 	  }  
  	return true;  //Otherwise data is double and return true
	}
}
