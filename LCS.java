import java.util.*;
import java.lang.Math.*;   // math operation package
import java.io.*;	//file package
import javax.swing.JOptionPane;	 //GUI header file
public class LCS
{
	public static void main(String[] args) throws IOException
	{
		Scanner input=new Scanner(System.in);
		String path= JOptionPane.showInputDialog("(LCS)Enter path:");// dialog box to get the path
		LCS_operations L= new LCS_operations();	// initializing object to call another class file functions
		File folder= new File(path);
		File[] listOfFiles= folder.listFiles();		// get file names from the path
		ArrayList<String> files= new ArrayList<>();
		for(int i=0;i<listOfFiles.length;i++)		// filters other files to get only txt files
		{
			if(listOfFiles[i].isFile())
			{
				String s=listOfFiles[i].getName();
				if(s.contains(".txt")&&!s.contains("output.txt"))
				{
					files.add(s);
				}
			}
		}
		ArrayList<ArrayList<String>> percent= new ArrayList<>();	// arraylist of arraylist to store matrix
		ArrayList<String> firstrow= new ArrayList<>();
		firstrow.add("  ****LCS****  ");
		for(int i=0;i<files.size();i++)     	// adds arraylist of file names to another array list
		{
			firstrow.add(files.get(i)+'\t');
		}
		percent.add(firstrow);
		for(int i=0;i<files.size();i++)		// to open and get the content of first file for the matrix
		{
			ArrayList<String> temp= new ArrayList<>();
			temp.add(files.get(i)+"\t");
			for(int j=0;j<files.size();j++)			// to open and get the content of second file for the matrix
			{
				String content1="";
				String content2="";
				if(i==j)		// if same files then prints s/f
				{
					temp.add("s/f\t\t");
				}
				else
				{
					try{content1= new Scanner(new File(files.get(i))).useDelimiter("\\Z").next();}	
																// gets the content of the file1 to content1
					catch(IOException e1)
					{
						System.out.println("couldn't read the file");
					}

					try{content2= new Scanner(new File(files.get(j))).useDelimiter("\\Z").next();}	
																// gets the content of the file1 to content2
					catch(IOException e2)
					{
						System.out.println("couldn't read the file");
					}

					content1=L.contentset(content1);	// calls function contentset using L object for content1
					content2=L.contentset(content2);	// calls function contentset using L object for content2
					int num=L.numerator(content1,content2);	// calling numerator function
					int x=content1.length();				
					int y=content2.length();
					/*calculation of formula*/

					double p1=(double)(num*200)/(x+y);
					int p2 = (int)(p1*100);
					double percentage= (p2/100.0);
					System.out.println();
					temp.add(String.valueOf(percentage)+"\t\t");
				}
			}
			percent.add(temp);
		}

		/*writes the output in output.txt*/

		FileWriter writer=new FileWriter("output.txt");
		for(ArrayList<String> arr: percent)
		{
			
			ArrayList<String> arr1 = (ArrayList<String>) arr.clone();
			for(String str: arr1)
			{
				writer.write(str);
			}
			writer.write("\r\n");
		}
		writer.close();

		/*processbuilder to open the output.txt*/

		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "output.txt");
		pb.start();
	}
}