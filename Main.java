

/*Miscellaneous: Display data looks best when there are 5 columns in the csv file!
								 with each data entry being no bigger then 8 characters wide.
								 Program still functions correctly with more/less columns.
*/

/*bugs:					Since a double is converted to a string, loss of displayed precision of .01 can
								sometimes occur.
								Since I use ConsoleInput.readInt you must enter an integer (valid or invalid)
							  to get out of the input loop, otherwise a loop of trying to enter an integer 
								will continue.
*/

import io.*;
import java.util.*;
import java.io.*;

public class AssignmentMain
{
	private static int rowNumber = 0; //Keeps track of used elements of the array
	private static int columnNum = 0; //Keeps track of number of columns of the array

	//Main simply calls the UI

	public static void main(String[] args)
	{
			uiDisplayMainMenu();
	}

	//Asks user to input an integer to choose between 1 of 8 options.
	//Loops untill user inputs 8 (quit)

	private static void uiDisplayMainMenu()
	{
		int usrSelection = 0;			//Variable to hold user input
		boolean quitLoop = false;		//Used to continually loop ui untill quit
		String[] dataArray = new String[1000];	//Creates new array of strings holds can hold 1000 elements

		while(!quitLoop)
		{
			System.out.printf("*******************************************************\n");
			System.out.printf("(1) - Load CSV file\n");
			System.out.printf("(2) - Display data\n");
			System.out.printf("(3) - Add row\n");
			System.out.printf("(4) - Delete row\n");
			System.out.printf("(5) - Calculate statistics\n");
			System.out.printf("(6) - Sort data by first column\n");
			System.out.printf("(7) - Save CSV file\n");
			System.out.printf("(8) - Quit\n");
			System.out.printf("*******************************************************\n");
			System.out.printf("Choice: ");

			usrSelection = ConsoleInput.readInt();

			System.out.printf("*******************************************************\n");
	
			//Calls relevant method depending on user input
			//If user inputs an invalid number, default is output and interface loops.

			switch (usrSelection)
			{
				case 1:
					dataArray = loadCSV();
					break;
	
				case 2:
					displayData(dataArray);
					break;
	
				case 3:
					dataArray = addRow(dataArray);
					break;
		
				case 4:
					dataArray = deleteRow(dataArray);
					break;
				
				case 5:
					calcStats(dataArray);
					break;
	
				case 6:
					dataArray = sortData(dataArray);
					break;
	
				case 7:
					saveCSV(dataArray);
					break;
	
				case 8:
					System.out.printf("Exiting system, goodbye!\n");
					System.out.printf("*******************************************************\n");
					quitLoop = true;
					break;

				default:
					System.out.printf("Invalid input, please re-enter a number from the list\n");
					break;
			}
		}
	}

	//Loads a valid csv file into a string of arrays and finds number of rows/columns.
	private static String[] loadCSV()
	{
		String fileLocation = "";							//Variable to hold user input file location
		String line = "";											//Stores each line of the file
		int lineNum = 0, tokenNum = 0;				//Sets initial row/column number to 0
		int columnEachRow = 0;								//Used to check each row has mathcing column numbers

		String[] dataArray = new String[1000];	//Creates an array of strings with that can hold 1000 elements

		StringTokenizer token = null;					

		/*Bunch of boolean variables to only set to false
			when an error check find erroneous data.		*/

		boolean isDataDouble = true;
		boolean correctDataType = true;
		boolean doesColumnNoMatch = true;

		/*Asks for user input file location
			if erroneous try catch block will output error.*/

		System.out.printf("Enter .csv file path: ");
		fileLocation = ConsoleInput.readLine();
		System.out.printf("*******************************************************\n");

		try
		{
		 /* Creates a bufferedReader to read in the file given then performs error checks
				to ensure data is valid. first Row may contain anything since they are headers
				all other rows must contain doubles and must match first rows header count.		 */

			BufferedReader br = new BufferedReader(new FileReader(fileLocation));

			/*Marks bufferedreader location so it can return to beginning after checking if file 
				has a line. Can read 5000 characters ahead still allowing reset. 
			 Arbitrarily large not sure how long the columns used will be.	*/

			br.mark(5000);		


			if(br.readLine() == null)		//Checks if the file has no data
			{
				System.out.printf("File is empty, can not load. \n");
			}
			else
			{
				br.reset(); 	//Resets bufferedreader to start of file again

				/*Reads in each line untill a null line is found, Assigns each line to 
					dataArray, and uses lineNum to count the number of rows total.		*/

				while ((line = br.readLine()) != null)
				{
					dataArray[lineNum] = line;
					lineNum++;
				}

				//Tokenizes the dataArray[0] with delimeter , and " " if any exist.

				token = new StringTokenizer(dataArray[0], ", ");

				//Checks how many headers by counting first line columns.

				while(token.hasMoreTokens())								
				{
					token.nextToken();
					tokenNum++;
				}

				/* Performs error checks.

					Checks if all columns are of same number, by
					comparing with the column number of the header
					row, dataArray[0].
					Each iteration of the for loop, sets column number
					back to zero too allow each row to be checked.
					Sets doesColumnNoMatch to false if one row is found
					to have incorrect number of columns.

					Checks if all data after headers are doubles.
					Starts from i = 1 to skip the String/header row.
					Calls method isNumeric to perform check for each
					value.

					Sets vairable correctDataType to false if there is 
					even a single non double value thus not allowing 
					the data to be loaded.*/	

				for(int i = 1; i < lineNum; i++)            
				{
				 	token = new StringTokenizer(dataArray[i], ", ");
					columnEachRow = 0;

					while(token.hasMoreTokens())
					{
						isDataDouble = isNumeric(token.nextToken());
						columnEachRow++;

						if(!isDataDouble)
						{
							correctDataType = false;
						}
					}

					if(columnEachRow != tokenNum)
					{
						doesColumnNoMatch = false;
					}
						
				}

				if(!doesColumnNoMatch)
				{
					System.out.printf("Number of columns in data does not match the\nnumber of columns in header, ");
				}


				/*If data is valid then outputs a confirmation message
					and leaves dataArray alone.*/

				if(correctDataType && doesColumnNoMatch)
				{
					System.out.println("Data is loaded!");
					rowNumber = lineNum; //sets global variable to number of rows in data
					columnNum = tokenNum; //sets global variable to number of columns in data
				}

				/* If data is invalid, clears all values inside dataArray
					 and simply turns it back into an empty of array of strings
					Outputting an error.*/

				else
				{
					System.out.println("can not load.");
					dataArray = new String[1000];
				}

				br.close(); //Stops the bufferedReader!
			}
		}

		/*Outputs errors if file is not found or if the file types data
			cannot be read.*/

		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch(IOException e)
		{
			System.out.println("Could not Parse file type.");
		}

		//Returns current data Array, empty if non valid, full of values if valid!
		return dataArray;
	}

	//Displays currently loaded data if it exists does not alter data in any way.
	private static void displayData(String[] inData)
	{
		if(inData[0] != null)			//checks if data is loaded
		{
			UseData data = new UseData();	//Creates new instance of UseData class

			//Calls displayTheData method in UseData class sending inData and rowNumber

			data.displayTheData(inData, rowNumber);	
		}
		else	//No data is loaded
		{
			System.out.printf("Can not display, no data has been loaded!\n");
		}
	}

	/*Adds a row of valid data to the array and increments the number of rows if successful.*/
	private static String[] addRow(String[] inData)
	{
		if(inData[0] != null)	//Checks if data is loaded
		{
			ManipulateData data = new ManipulateData(); //Creates new instance of ManipulateData class

			//calls addTheRow in ManipulateData class
			inData = data.addTheRow(inData, rowNumber, columnNum);
			
			//Checks if new row has been added and increments total rowNumber
			if(inData[rowNumber] != null)
			{
				rowNumber++;
			}
				
		}
		else //no data loaded
		{
			System.out.printf("Can not add new row, no data has been loaded!\n");
		}
		return inData;
	}

	/*Deletes a row of data from the array and removes a row number*/
	private static String[] deleteRow(String[] inData)
	{
		if(inData[0] != null) //Checks if there is loaded data
		{
			ManipulateData data = new ManipulateData();

			inData = data.deleteTheRow(inData, rowNumber);

			//Minuses the total number of rows since one has been deleted
			if(inData[rowNumber - 1] == null) 
			{
				rowNumber--;		
			}
		}
		else	//No loaded data!
		{
			System.out.printf("Can not delete, no data has been loaded!\n");
		}

		return inData;
	}

	/*Calls method in UseData class to calculate and print statistics if data is loaded
		does not actually alter the data*/
	private static void calcStats(String[] inData)
	{
		if(inData[1] != null)				//checks if array has any data other than headers to work with
		{
			UseData data = new UseData();		//Creates new variable of UseData class

			/*Calls calcTheStats method in UseData class sending the data array and number or rows
			  and columns.*/

			data.calcTheStats(inData, rowNumber, columnNum);	
		}
		else //No data in array.
		{
			System.out.printf("Can't do any calculations, no data is present!\n");
		}
	}

	/* Calls sort method in sort class sending the loaded array and number of rows
		 array is then sorted and returned back. Uses MergeSort.*/
	private static String[] sortData(String[] inData)
	{
		if(inData[1] != null)				//checks if array has any data other than headers to work with
		{
			MergeSort sortData = new MergeSort(); //Creates new instance of MergeSort class

			//Calls sort method in MergeSort class sending the array and the first valued row number 1,
			// and the total number of data rows, rowNumber - 1.
			inData = sortData.sort(inData, 1, rowNumber - 1);

			System.out.println("Data has been sorted!");
		}
		else //No data is loaded
		{
			System.out.printf("Can't sort, no data is present!\n");
		}

		return inData;	
	}


	/*If data is loaded saves array to filename of user choice*/
	private static void saveCSV(String[] inData)
	{
		String newFileName;
		
		if(inData[0] != null)	//Check if file is loaded
		{
			System.out.printf("Enter save file name: ");
			newFileName = ConsoleInput.readLine();	//gets user file name
			System.out.printf("*******************************************************\n");
	
			try
			{		
				BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName, false)); //false forces overwrite file
			
				//prints each line to file row by row.

				for(int i = 0; i < rowNumber; i++)
				{
					bw.write(inData[i]);
					bw.newLine();
				}

				System.out.printf("Data saved to %s!\n", newFileName);

				bw.close();
			}
			catch(IOException e)	//Incorrect data type in file, should never occur.
			{
				System.out.println("Could not parse");
			}
		}
		else	//No data loaded
		{
			System.out.printf("Can not save, no data has been loaded!\n");
		}
	}

	//Method used to check if a string is a double.
	public static boolean isNumeric(String str)  
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
