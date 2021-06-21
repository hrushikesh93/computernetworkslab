import java.security.SecureRandom;
import java.util.*; 
import java.io.*;
class Bucket
{
	public static void main(String args[])
	{
		Random r=new Random();
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the input rate: ");
		int n=s.nextInt();
		System.out.println("Enter the Bucket Size: ");
		int bucket_size=s.nextInt();
		System.out.println("Enter the Output Rate: ");
		int out_rate=s.nextInt();
		System.out.println("Enter packet data: ");
		int[] input_arr=new int[n];
		for(int i=0;i<n;i++)
		{
			input_arr[i]=r.nextInt(127);
		}
		int bc=0;
		for(int i=0;i<n;i++)
		{
			if(bc+input_arr[i]<=bucket_size)
			{	
				bc=bc+input_arr[i];
				System.out.print(i+"\tPacket: "+input_arr[i]+"\tBucket Content: "+bc+"\tStatus: Accepted\tRemaining: ");		
				bc=bc-out_rate;
				if(bc<=0)
				{
					bc=0;
				}
				System.out.println(bc); 
			}
			else
			{
				System.out.print(i+"\tPacket: "+input_arr[i]+"\tBucket Content: "+bc+"\tStatus: Rejected\tRemaining: ");	
				bc=bc-out_rate;
				if(bc<=0)
				{
					bc=0;
				}
				System.out.println(bc);
			}	
		}	
	}
}
