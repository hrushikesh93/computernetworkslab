import java.net.*;
import java.io.*;

class tcpServer
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket sersock=new ServerSocket(5050);
		System.out.println("Server Started");
		
		Socket sock=sersock.accept();
		System.out.println("Server ready");
		
		InputStream istream=sock.getInputStream();

		BufferedReader br=new BufferedReader(new InputStreamReader(istream));

		System.out.println("enter file name:");
		String fname=br.readLine();

		BufferedReader ContentReader=new BufferedReader(new FileReader(fname));

		OutputStream ostream=sock.getOutputStream();

		PrintWriter pr=new PrintWriter(ostream,true);

		String msg;
		System.out.println("Sending contents of "+fname);
		
		while((msg=ContentReader.readLine()) != null)
		{
			pr.println(msg);
		}
		
		sock.close();
	}

}
