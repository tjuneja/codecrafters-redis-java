import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    //  Uncomment this block to pass the first stage
        ServerSocket serverSocket;
        Socket clientSocket = null;
        int port = 6379;
        try {
          serverSocket = new ServerSocket(port);
          // Since the tester restarts your program quite often, setting SO_REUSEADDR
          // ensures that we don't run into 'Address already in use' errors
          serverSocket.setReuseAddress(true);
          while(true) {
              clientSocket = serverSocket.accept();
              byte[] input = clientSocket.getInputStream().readAllBytes();
              String inputString = new String(input);
              System.out.println("Received \n" + inputString);
              clientSocket.getOutputStream().write("+PONG\r\n".getBytes());
          }
        } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
        } finally {
          try {
            if (clientSocket != null) {
              clientSocket.close();
            }
          } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
          }
        }
  }
}
