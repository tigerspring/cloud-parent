package socket;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    public static Integer port = 4000;
    public static Integer port1 = 4001;

    public void serverPrintSart() throws IOException {
        ServerSocket socketServer = new ServerSocket(port);

        while (true){
            Socket socket = socketServer.accept();
            new Thread(()->{
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println("Hello World 1");
                    printWriter.println("Hello World 2");
                    printWriter.println("Hello World 3");
                    printWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void serverReadSart() throws IOException {
        ServerSocket socketServer = new ServerSocket(port1);
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        while (true){
            Socket socket = socketServer.accept();
            executorService.submit(()->{
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " read msg: " + bufferedReader.readLine());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            /*new Thread(()->{
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " read msg: " + bufferedReader.readLine());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();*/
        }
    }




    public static void main(String args[]) throws IOException {
        Server server = new Server();

//        server.serverPrintSart();
    }
}
