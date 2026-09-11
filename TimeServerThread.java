import java.io.*;
import java.net.*;
import java.util.*;

public class TimeServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
   

    public TimeServerThread() throws IOException {
	this("TimeServerThread");
    }

    public TimeServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    public void run() {

        while (true) {
            try {
                byte[] buf = new byte[256];
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                // figure out response
                String dString = new Date().toString();
                buf = dString.getBytes();

		// send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        socket.close();
    }

    
}