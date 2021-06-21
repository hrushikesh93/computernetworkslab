import java.math.*;
import java.io.*;
import java.security.*;
class ED_helper
{
	private int bitlen,r;
	private BigInteger p,q,n,phi,e,d;
	ED_helper(int bit)
	{
		bitlen=bit;
		SecureRandom r=new SecureRandom();
		p=new BigInteger(bitlen/2,100,r);
		q=new BigInteger(bitlen/2,100,r);
		System.out.println("P value :"+p+"\n\n\n "+"Q value="+q);
		n=p.multiply(q);
		phi=p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e=new BigInteger(bitlen/2,100,r);
		d=e.modInverse(phi);
		System.out.println("\n\n\nValue of D :"+d+"\n\n\nValue of E : "+e);	
	}
	public BigInteger encrypt(BigInteger Msg)
	{
		return(Msg.modPow(e,n));
	}
	public BigInteger decrypt(BigInteger Msg)
	{
		return(Msg.modPow(d,n));	
	}
}

class ED
{
	public static void main(String args[]) throws IOException
	{
		ED_helper rsa=new ED_helper(1200);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String text1,text2;
		BigInteger Pt,Ct;
		System.out.println("\n\nEnter Plaintext :");
		text1=br.readLine();
		Pt=new BigInteger(text1.getBytes());
		Ct=rsa.encrypt(Pt);
		System.out.println("\n\nCiperText is : "+Ct);
		Pt=rsa.decrypt(Ct);
		text2=new String(Pt.toByteArray());
		System.out.println("\n\n\nData after decrypt :"+text2);
	}
}
