import java.net.*; 
import java.io.*; 
class Client1{ 
public static void main(String args[])throws Exception{ 
Socket s=new Socket("10.10.32.191",3333); 
DataInputStream din=new DataInputStream(s.getInputStream()); 
DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 String str="",str2=""; 
while(!str.equals("stop")){ 
str=br.readLine(); 
dout.writeUTF(str); 
dout.flush(); 
str2=din.readUTF(); 
System.out.println("My Friend-:"+str2); 
} 
dout.close(); 
s.close(); 
}
} 