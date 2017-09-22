import java.util.*;
import java.lang.Math.*;// math operation package
import java.io.*;//file package
import javax.swing.JOptionPane;//GUI header file
public class fingerprint
{
	public static void main(String[] args) throws IOException
	{
		Scanner input=new Scanner(System.in);
		String path= JOptionPane.showInputDialog("(FP)Enter path:");// dialog box to get the path
		fp_operations fp= new fp_operations();// initializing object to call another class file functions
		File folder= new File(path);
		File[] listOfFiles= folder.listFiles();// get file names from the path
		ArrayList<String> files= new ArrayList<>();
		for(int i=0;i<listOfFiles.length;i++)	// filters other files to get only txt files
		{
			if(listOfFiles[i].isFile())
			{
				String s=listOfFiles[i].getName();
				if(s.contains(".txt")&& !s.contains("output.txt"))
				{
					files.add(s);
				}
			}
		}
		ArrayList<ArrayList<String>> percent= new ArrayList<>();// arraylist of arraylist to store matrix
		ArrayList<String> firstrow= new ArrayList<>();
		firstrow.add("  ****LCS****  ");
		for(int i=0;i<files.size();i++)// adds arraylist of file names to another array list
		{
			firstrow.add(files.get(i)+'\t');
		}
		percent.add(firstrow);
		for(int i=0;i<files.size();i++)// to open and get the content of first file for the matrix
		{
			ArrayList<String> temp= new ArrayList<>();
			temp.add(files.get(i)+"\t");
			for(int j=0;j<files.size();j++)// to open and get the content of second file for the matrix
			{
				String content1="";
				String content2="";
				if(i==j)// if same files then prints s/f
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

					content1=fp.contentset(content1);// calls contentset using object for content1
					content2=fp.contentset(content2);// calls contentset using object for content2
					ArrayList<Long> hash1=new ArrayList<>();// initialize arraylist for hashvalues for content1
					ArrayList<Long> hash2=new ArrayList<>();// initialize arraylist for hashvalues for content2
					hash1=fp.hashfunction(hash1,content1);// calls hashfunction to calculate hashvalues for content1
					hash2=fp.hashfunction(hash2,content2);// calls hashfunction to calculate hashvalues for content2
					int num=fp.numerator(hash1,hash2);// calls numerator to calculate n in the formula
					int den=(hash1.size()+hash2.size());// denominator value
					
					/*calculation of the formula*/

					float p1= ((num*2)/(den))*100;
					int p2= (int)(p1*100);
					float percentage=(float)(p2/100.0);
					temp.add(String.valueOf(percentage)+"\t\t");
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