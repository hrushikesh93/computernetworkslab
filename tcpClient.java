import java.net.*;
import java.io.*;

class tcpClient
{
	public static void main(String args[]) throws IOException
	{

		Socket sock=new Socket("localhost",5050);


		System.out.println("Enter filename :");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String fname=br.readLine();
	

		OutputStream ostream=sock.getOutputStream();
		PrintWriter pw = new PrintWriter(ostream,true);
		pw.println(fname);


		InputStream istream=sock.getInputStream();
		BufferedReader sockread=new BufferedReader(new InputStreamReader(istream));
		String msg;
	

		while((msg=sockread.readLine())!=null)
		{
			System.out.println(msg);
		}
		
	}
}
