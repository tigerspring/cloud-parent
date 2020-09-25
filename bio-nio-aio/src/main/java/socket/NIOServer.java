package socket;

import sun.nio.ByteBuffered;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NIOServer {

    public void serverStart(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(4,4,60L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());

        threadPool.execute(()->{
            try(
                    Selector selector = Selector.open();
                    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                    ){
                serverSocketChannel.bind( new InetSocketAddress(InetAddress.getLocalHost(),4002) );
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                while(true){
                    selector.select();
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeySet.iterator();
                    while (it.hasNext()){
                        SelectionKey selectionKey = it.next();
                        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        Socket socket = socketChannel.socket();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println( bufferedReader.readLine());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        NIOServer nioServer = new NIOServer();
        nioServer.serverStart();
    }

}
