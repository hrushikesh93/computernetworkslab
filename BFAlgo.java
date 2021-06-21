import java.util.*;
import java.io.*;
import java.math.*;

class BFAlgo
{
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);


		System.out.println("Enter the number of nodes: ");
		int n=s.nextInt();
		

		System.out.println("Enter Source node number :");
		int source=s.nextInt();

		System.out.println("Enter Destination node number :");
		int dest=s.nextInt();


		int[] dist=new int[n];
		for(int i=0;i<n;i++)

			dist[i]=999;


		int[][] adj_mat=new int[n][n];
		System.out.println("Enter adj matrix :");
		

		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				
				adj_mat[i][j]=s.nextInt();
		
			}
		}


		System.out.println("\n\nEntered ADJ MATRIX IS:");
		disp(adj_mat);
		dist[source]=0;


		for(int k=0;k<n-1;k++)
		{
			for(int i=0;i<n;i++)
			{

				for(int j=0;j<n;j++)
				{

					if(adj_mat[j][i]!=999)
					{

						if(dist[i]>dist[j]+adj_mat[j][i])
							dist[i]= dist[j]+adj_mat[j][i];
					}
				}
			
		
			}
		}		


		for(int i=0;i<n;i++)
			System.out.println(source+"-->"+i+" = "+dist[i]);
	}

	
	public static void disp(int arr[][])
	{
		for(int[] x : arr)
		{	for(int y : x)				
				System.out.print(y);
			System.out.println();
		}
	}


}
