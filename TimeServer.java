
import java.io.*;

public class TimeServer {
    public static void main(String[] args) throws IOException {
        new TimeServerThread().start();
    }
}