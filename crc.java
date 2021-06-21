import java.util.*;

class crc
{
	
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);

		int k,g;
		System.out.println("Enter  degree of data :");
		k=s.nextInt();
		System.out.println("Enter  degree of generator :");
		g=s.nextInt();
		
		int data[] = new int [200];	
		int gen[] = new int [200];
		System.out.println("Enter  data: ");//113659.20
		data=input(data,k);
		System.out.println("Enter generator: ");
		gen=input(gen,g);

		int codeword[] = new int[200];
		
		for(int i=k;i<k+g-1;i++)
			data[i]=0;

		codeword=div(data,gen,k,g);
		System.out.println("Checksum is :");
		for(int i=k;i<k+g-1;i++)
		{
			data[i]=codeword[i];
			System.out.println(data[i]);
		}

		System.out.println("Codeword is :");
		for(int i=0;i<k+g-1;i++)
		{
			System.out.println(data[i]);
		}

		int codewordR[]=new int[200];
		int dataR[]=new int[200];
		System.out.println("RE-ENTER CODEWORD for reciever :");

		dataR=input(codewordR,k+g-1);

		codewordR=div(dataR,gen,k,g);
		int flag=0;

		for(int i=k;i<k+g-1;i++)
		{	if(codewordR[i]!=0)
			{	flag=1;
			
			}
			else
				flag=0;
		}
		if(flag==1)
			System.out.println("ERROR");
		
		else
			System.out.println("NO ERROR");
	}

	public static int[] input(int arr[],int l)
	{	Scanner s =new Scanner(System.in);
		for(int i=0;i<l;i++)
			arr[i]=s.nextInt();
		return arr;
	}

	public static int[] div(int data[],int gen[],int n,int g)
	{
		int k,msb;
		int r[]=new int[200];		

		for(int i=0;i<g;i++)
		{
			r[i]=data[i];
		}

		for(int i=0;i<n;i++)
		{
			k=0;
			
			msb=r[i];
			for(int j=i;j<g+i;j++)
			{	
				if(msb==0)
					r[j]=r[j]^0;
				else
					r[j]=r[j]^gen[k];

				k++;
			}
			r[g+i]=data[g+i];		
		}
		return r;
	}
}
