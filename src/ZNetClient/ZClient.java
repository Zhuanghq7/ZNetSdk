package ZNetClient;

import ZNetClient.Interface.clientInterface;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * ZClient is the client of ZServer
 * TO USE:
 * new ZClient()
 * setPORT and setADDR
 * Created by zhuangh7 on 17-6-20.
 */
public class ZClient implements clientInterface {
    private int PORT =1111;//默认端口号
    private String ADDR;
    private int retry = 3;

    public ZClient(String addr,int port){
        ADDR = addr;
        PORT = port;
    }

    public ZClient(){

    }

    @Override
    public String Zsend(String msg) {
        byte[] buf = msg.getBytes();
        int i = retry;
        while(--i>=0) {
            try {
                System.out.println("向服务器发送数据:" + msg);
                InetAddress address = InetAddress.getByName(ADDR);  //服务器地址
                //创建发送方的数据报信息
                DatagramPacket dataGramPacket = new DatagramPacket(buf, buf.length, address, PORT);

                DatagramSocket socket = new DatagramSocket();  //创建套接字
                socket.setSoTimeout(5000);//最高延迟10000
                socket.send(dataGramPacket);  //通过套接字发送数据

                //接收服务器反馈数据  懒得改了2333反馈就别超过1024啦
                byte[] backbuf = new byte[1024];
                DatagramPacket backPacket = new DatagramPacket(backbuf, backbuf.length);
                socket.receive(backPacket);  //接收返回数据
                String backMsg = new String(backbuf, 0, backPacket.getLength());
                System.out.println("服务器返回的数据为:" + backMsg);
                socket.close();
                return backMsg;
            } catch (Exception e) {
                //e.printStackTrace();
                //throw new Error("send process error");
                System.out.println("剩余重试"+i);
            }
        }
        return null;
    }

    @Override
    public void setPORT(int PORT) {
        this.PORT = PORT;
    }

    @Override
    public void setADDR(String addr) {
        this.ADDR = addr;
    }
}
