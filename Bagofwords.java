import java.util.*; 
import java.lang.Math.*; // math operation package
import java.io.*; //file package
import javax.swing.JOptionPane; //GUI header file
public class Bagofwords
{
	public static void main(String[] args) throws IOException
	{
		Scanner input=new Scanner(System.in);
		String path= JOptionPane.showInputDialog("(BOW)Enter path:");// dialog box to get the path
		BOW B= new BOW();// initializing object to call another class file functions
		File folder= new File(path);
		File[] listOfFiles= folder.listFiles();	// get file names from the path
		ArrayList<String> files= new ArrayList<>();
		for(int i=0;i<listOfFiles.length;i++)	// filters other files to get only txt files
		{
			if(listOfFiles[i].isFile())
			{
				String s=listOfFiles[i].getName();
				if(s.contains(".txt") && !s.contains("output.txt"))
				{
					files.add(s);
				}
			}
		}
		ArrayList<ArrayList<String>> percent= new ArrayList<>();	// arraylist of arraylist to store matrix
		ArrayList<String> firstrow= new ArrayList<>();
		firstrow.add("  ****BOW****  ");	
		for(int i=0;i<files.size();i++)			// adds arraylist of file names to another array list
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
				if(i==j)			// if same files then prints s/f
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
														// gets the content of the file2 to content2
					catch(IOException e2)
					{
						System.out.println("couldn't read the file");
					}

					content1=B.contentset(content1);		// calls contentset using object for content1
					content2=B.contentset(content2);		// calls contentset using object for content2
					String[] words1= content1.split(" ");	// split words with space using split function for content1
					String[] words2= content2.split(" ");	// split words with space using split function for content2
					Map <String,Integer> dict1 = new HashMap<>();		// initialize hashmap for word1 
					Map <String,Integer> dict2 = new HashMap<>();		// initialize hashmap for word2
					dict1 =B.dictionary(dict1,words1);		// call dictionary function using object B to count frequency for word1
					dict2 =B.dictionary(dict2,words2);		// call dictionary function using object B to count frequency for word2
					double x=B.denominator(dict1);			// calculate attribute of denominator by calling denominator function by sending dictionary of dict1
					double y=B.denominator(dict2);			// calculate attribute of denominator by calling denominator function by sending dictionary of dict2					
					int n= B.numerator(dict1,dict2);

					/*calculation of the formula*/

					double p1= ((n)/Math.sqrt(x*y))*100;
					int p2 = (int)(p1*100);
					double p3= (p2/100.0);

					temp.add(String.valueOf(p3)+"\t\t");

				}
			}
			percent.add(temp);
		}

		/* writes the output in a output.txt file */

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

		/* process builder to open the ouput.txt */

		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "output.txt");
		pb.start();
	}
}