import java.util.*;
import java.lang.Math.*;
import java.io.*;
public class fp_operations
{
	public String contentset(String content) 			//contentset which takes string content as argument
	{
		String conset="";			
		int j;
		for(int i=0;i<content.length();i++)				// to filter the special characters and spaces
		{
			j=(int)content.charAt(i);
			if(j>64 && j<92)							// using askii values filtered the special characters and spaces
			{
				conset+=(char)(j+32);
			}
			else if((j>96 && j<123)||(j>47 && j<58))
			{
				conset+=(char)(j);
			}
		}
		return conset;							// returns the content which is filtered
	}
	public ArrayList<Long> hashfunction(ArrayList<Long> hash,String content)
	{											// hashfunction takes empty hash and string content as parameters
		long value=0;

		for(int i=0;i<content.length()-4;i++)
		{
			int k=4;							// k gram value is 5 where it start from (5-1) to 0
			for(int j=i;j<i+5;j++)
			{
				value+=(content.charAt(j)*(Math.pow(10,k)));
				k--;
			}
			hash.add(value);					// adds the hash values of the content to hash ArrayList
		}
		return hash;							// returns the hash arraylist
	}
	public int numerator(ArrayList<Long> hash1,ArrayList<Long> hash2)
	{											// numerator function to calculate the numerator which takes two arraylists of hashvalues
		int count=0;
		for(int i=0;i<hash1.size();i++)
		{
			for(int j=0;j<hash2.size();j++)
			{

				if((hash1.get(i)).equals(hash2.get(j)))	// count the same hashvalues in the two arraylists
				{
					count++;
				}
			}
		}
		return count;					// return the count
	}
}