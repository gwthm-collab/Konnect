import java.net.*;
import java.io.*;

public class ClientSide{
    public static void main(String[] a){
        String serName = a[0];
        int port = Integer.parseInt(a[1]);
        try{
            System.out.println("Connecting to "+serName+" on port "+port); 			//Connect to Server
            Socket client = new Socket(serName, port);
            System.out.println("Connected to " + client.getRemoteSocketAddress());
            OutputStream ToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(ToServer);
			//Print the message from client to server
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream FromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(FromServer);
            
            System.out.println("Server replies " + in.readUTF());
            client.close();
            }
            catch(IOException e) {
                e.printStackTrace();
        }
    }