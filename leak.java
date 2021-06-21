import java.util.*;
import java.io.*;
class Queue
{
int q[], f=0, r=0, size;
void insert(int n)
{
Scanner s= new Scanner(System.in);
q=new int[10];
for (i=0;i<n;i++)
{
System.out.println("enter"+i+"element");
int ele = s.nextInt();

if (r+1>10)
{
 System.out.println("\n queue is full \n lost packet" + ele+ "\n");
 break;
 }
 else 
 {
 r++;
 q[i]=ele;
 }
}
}
void delete()
{
 Scanner s = new Scanner(System.in);
 Thread t= new Thread();
 if (r==0)
 {
 System.out.println("\n queue empty");
 }
 else
 {
 for (int i=0;i<r;i++)
 {
 try 
 {
 	t.sleep(1000);
 }
 catch(Exception e){}
 System.out.print("\n leaked packet"+q[i]);
 f++;
}
}
}
class Bucket extends Thread
{
public static void main(String args[]) throws Exception
{
 Queue q = new Queue();
 Scanner src = new Scanner(System.in);
 System.out.println("enter the packets to be sent");
 int size = src.nextInt();
 q.insert(size);
 q.delete();
}
}
