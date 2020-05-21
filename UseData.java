
   /* Performs any actions that require the data to be used but not manipulated
	 These include displaying the data and calculating statistics to be shown*/

import io.*;
import java.util.*;
import java.io.*;

public class UseData
{
	/* Gets the already validated array and outputs
		 it in a fairly neat table.
	   Data should be valid at this point*/
	public static void displayTheData(String[] inData, int inRowNumber)
	{
		StringTokenizer token = null; //Initializes new StringTokenizer

		if(inData[0] != null)			//checks if data is loaded
		{
			token = new StringTokenizer(inData[0], ", "); //Tokenizes header row with delimeter , and " ".
			System.out.printf("  # |");	//Prints top left of table purely for formatting.

			while(token.hasMoreTokens())				//prints headers in there own box for display
			{
				System.out.printf(String.format("%-8s |", token.nextToken()));
			}
			
			// Prints a line to seperate headers from data

			System.out.printf("\n");	
			System.out.printf("-------------------------------------------------------\n");
	
			/*For loop that iterates from the first row of values dataArray[1] untill
				the last row of the Array.*/

			for(int i = 1; i < inRowNumber; i++)		//starts printing data value under headers
			{
				token = new StringTokenizer(inData[i], ", "); //Tokenizes each line of the array
	
				System.out.printf("%3d |", i);		//Prints the row number before each row.
			
				/*Each value in a row is printed in a left alligned 8 character wide space
					untill end of tokens in a line.*/

				while(token.hasMoreTokens())
				{
					System.out.printf(String.format("%-8s |", token.nextToken()));
				}
					
				// More formatting for the table!

				System.out.printf("\n");
			}
			System.out.printf("-------------------------------------------------------\n");
		}
		else	//No data is loaded
		{
			System.out.printf("Can not display, no data has been loaded!\n");
		}
	}

	//Calculates statistics of data and outputs findings, does not export anything.
	public static void calcTheStats(String[] inData, int inRowNumber, int inColumnNum)
	{
		double sum, average, min, max, temp;		//Creates all variables needed to hold temporary statistics.
		StringTokenizer token = null;
		String[] calcArray = new String[1000];		//Holds calculated data

		// Tokenizes header rows and prints the top left of the table to display.

		token = new StringTokenizer(inData[0], ", ");
		System.out.printf("         |");

		//prints headers in their own box for display in a 8 character wide left alligned row.

		while(token.hasMoreTokens())				
		{
			System.out.printf(String.format("%-8s |", token.nextToken()));
		}

		//More outputs for table display.
		System.out.printf("\n");
		System.out.printf("-------------------------------------------------------\n");

		for(int j = 0; j < inColumnNum; j++)		//Loops depending on number of columns
		{
			sum = 0;							//initializes variables for each column
			temp = 0;

			//Initializes to NaN to allow any number to be first value of temp
			min = Double.NaN;			
			max = Double.NaN;

			for( int i = 1; i < inRowNumber; i++)	//loops untill reaches end of rows
			{
				token = new StringTokenizer(inData[i], ",");

				for(int x = j; x > 0; x--) //Skips columns once read. e.g Once column 2 has been
				{													 //read, skips all values in column 1,2 and reads
					token.nextToken();			 // column 3
				}

				//Turns current token into a double from a String.

				temp = Double.parseDouble(token.nextToken());
				
				if(Double.isNaN(min))			//sets first minimum value
				{	
					min = temp;
				}

				if(Double.isNaN(max))			//sets first max value
				{
					max = temp;
				}

				if(temp > max)		//Checks all value in column to find maximum value in column
				{
					max = temp;
				}

				if(temp < min)		//Checks all value in column to find minimum value in column
				{
					min = temp;
				}

				sum = sum + temp;	//sums all values in each column
			}

			average = sum / (inRowNumber - 1);		//Calculates average; minus 1 to  not include header row

			//Start remaking a string array with all sum, avg etc on one row each

			if(calcArray[0] == null)			//initialise beggining of each element and gives row header
			{
				calcArray[0] = "Sum,";			
				calcArray[1] = "Average,";
				calcArray[2] = "Minimum,";
				calcArray[3] = "Maximum,";
			}

			//creates a string with each type (sum/avg...) data on one row
			//Adds value(more string) to each element every iteration of each column
			//of data

			calcArray[0] = calcArray[0] + String.format("%.2f", sum);
			calcArray[1] = calcArray[1] + String.format("%.2f", average);
			calcArray[2] = calcArray[2] + String.format("%.2f", min);
			calcArray[3] = calcArray[3] + String.format("%.2f", max);


			if(!(j == inColumnNum - 1)) 		//Adds ',' character to string to act
																			// as delimeter does not add on last value/column
			{
				for(int i = 0; i < 4; i++)
				{
					calcArray[i] = calcArray[i] + ",";
				}
			}
		}

		for(int i = 0; i < 4; i++)		//starts printing each row formatted
		{
			token = new StringTokenizer(calcArray[i], ", ");
	
			while(token.hasMoreTokens())
			{
				System.out.printf(String.format("%-8s |", token.nextToken()));
			}
					
			System.out.printf("\n");
		}
		System.out.printf("-------------------------------------------------------\n");	
	}
}
