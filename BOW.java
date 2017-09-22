import java.util.*;
import java.lang.Math.*;
import java.io.*;
public class BOW
{
	public String contentset(String content) 
	{											//contentset which takes string content as argument
		String conset="";
		int j;
		for(int i=0;i<content.length();i++)		// to filter the special characters 
		{
			j=(int)content.charAt(i);
			if(j>64 && j<92)					// using askii values filtered the special characters
			{
				conset+=(char)(j+32);
			}
			else if((j>96 && j<123)||(j>47 && j<58))
			{
				conset+=(char)(j);
			}
			else if(content.charAt(i)==' ' || content.charAt(i)=='\n')
			{
				conset+=' ';
			}
		}
		String conspace="";								//initialize empty string to store the modified content
		int flag=1;
		int count=0;
		for(int i=0;i<conset.length();i++)				// this for loop is to remove the consecutive spaces
		{
			if(conset.charAt(i)!=' ')
			{
				conspace+=conset.charAt(i);
				count=0;
			}
			else if(conset.charAt(i)==' ')
			{
				count++;
			}
			if(count==flag)
			{
				conspace+=' ';
			}

		}
		return conspace;					// returns the modified content 
	}

	public Map<String,Integer> dictionary(Map<String,Integer>dict,String[] words)
	{											// dictionary function takes empty dictionary and string array of words
		for(String s : words)					// for loop to take everyword from the string array
		{
			if(s!=" ")							// if to avoid any spaces
			{
				if(dict.containsKey(s))			// if the word present in dict then its (value+1)
				{
					dict.put(s,1+dict.get(s));
				}
				else							// if the word not present in dict then add the word and set the value to 1
				{	
					dict.put(s,1);
				}
			}
		}
		return dict;						// returns the dictionary which has words and its frequency.
	} 
	public double denominator(Map<String,Integer> dict)
	{												// denominator function takes dict as parameter
		double temp=0.0;
		for(String s : dict.keySet())
		{
			temp+=dict.get(s)*dict.get(s);			// calculates the mod of the frequency of all the words and store in temp
		}
		return temp;								// returns the temp
	}
	public int numerator(Map<String,Integer> dict1,Map<String,Integer> dict2)
	{													// numerator function takes two dictionaries of two files
		int n=0;
		for(String s1 : dict1.keySet())				// for loop for each key in dict1
		{
			for(String s2 : dict2.keySet())			// for loop for each key in dict2
			{
				if(s1.equals(s2))					// if words are same
				{
					n+=dict1.get(s1)*dict2.get(s2);	// multipy its frequencies and stores it in n
				}
			}
		}
		return n;							//returns the n value i.e numerator.
	}

}