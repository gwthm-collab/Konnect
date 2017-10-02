import java.net.*;
import java.io.*;

public class ServerSide extends Thread{
    private ServerSocket ssoc;
    
    public ServerSide(int port) throws IOException{
        ssoc = new ServerSocket(port);
        ssoc.setSoTimeout(5000);
    }

    public void run(){
        while(true){
            try{
                System.out.println("Waiting for Client on port "+ ssoc.getLocalPort()+"... ");
                Socket server = ssoc.accept();
                System.out.println("Connected to "+server.getRemoteSocketAddress());
                DataInputStream input = new DataInputStream(server.getInputStream());
                System.out.println(input.readUTF());

                DataOutputStream op = new DataOutputStream(server.getOutputStream());
                op.writeUTF("Successful message from "+ server.getLocalSocketAddress());
                server.close();
            }
            catch(SocketTimeoutException e){
                System.out.println("Socket Timed out");
                break;
            }
            catch(IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] a){
        int port = Integer.parseInt(a[0]);
        try{
            Thread t = new ServerSide(port);
            t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}