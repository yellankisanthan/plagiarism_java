import java.util.*;
import java.lang.Math.*;
import java.io.*;
public class LCS_operations
{
	public String contentset(String content) 
	{										//contentset which takes string content as argument
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
		String conspace="";							//initialize empty string to store the modified content
		int flag=1;
		int count=0;
		for(int i=0;i<conset.length();i++) 			// for loop is to remove the consecutive spaces
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
		return conspace;				// returns the modified content
	}
	public int numerator(String content1,String content2)
	{														// numerator function which takes content1 and content2 as parameters
		int cmax=0;
		int c=0;
		for(int i=0;i<content1.length()-1;i++)				// for loop is to count the longest common the substring length
		{
			int j=0;
			while(j<content2.length()-1)
			{
				int temp=i;
				c=0;
				if(content1.charAt(temp)==content2.charAt(j))
				{
					while(content1.charAt(temp)==content2.charAt(j) && temp<content1.length()-1 && j<content2.length()-1)
					{
						c++;
						j++;
						temp++;
					}
					if(c>cmax)
					{
						cmax=c;
					}
				}
				else
				{
					j++;
				}
			}
		}
		return cmax+1;					//returns the length of largest common substring of content1 and content2
	}
}