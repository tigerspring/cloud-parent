package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

public class Client {

    public void recievedMsg() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        try (Socket socket = new Socket(InetAddress.getLocalHost(),Server.port)){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.lines().forEach(e-> System.out.println(e));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMsg() throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        try (Socket socket = new Socket(InetAddress.getLocalHost(),Server.port1)){
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("clent say hello!");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverPrintSartNIO() throws IOException {
        System.out.println(InetAddress.getLocalHost());
        try (Socket socket = new Socket(InetAddress.getLocalHost(),4002)){
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("clent say hello!");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.serverPrintSartNIO();
    }
}
