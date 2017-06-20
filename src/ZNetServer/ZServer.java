package ZNetServer;

import ZNetServer.Interface.MessageReceiveCallBack;
import ZNetServer.Interface.serverInterface;

import java.net.*;
import java.util.concurrent.TimeoutException;

/**
 * ZServer is a eazy UDP Message transport server
 * TO USE:
 * new ZServer
 * (set PORT)
 * ZServer.setMessageReceiveCallBack()
 * Zserver.Zstart();
 * Zserver.Zstop();
 * Created by zhuangh7 on 17-6-20.
 */
public class ZServer extends Thread implements serverInterface {
    private int PORT = 1111;
    private boolean isRun = false;
    private boolean callBackSet = false;
    private MessageReceiveCallBack messageReceiveCallBack;

    @Override
    public void Zstart() {
        if (callBackSet) {
            isRun = true;
            this.start();
            System.out.println("start");
        }
    }

    @Override
    public void run() {
        //super.run();

        try {
//            InetAddress address = InetAddress.getByName("127.0.0.1");
            //创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket(PORT);
            byte[] buf = new byte[1024];  //定义byte数组
            DatagramPacket packet = new DatagramPacket(buf, buf.length);  //创建DatagramPacket对象
            while (isRun) {
                socket.receive(packet);  //通过套接字接收数据
                String getMsg = "";

                getMsg = new String(buf, 0, packet.getLength());


                System.out.println("客户端发送的数据为：" + getMsg);
                String feedback;
                feedback = messageReceiveCallBack.messageReceiveCallBack(getMsg); //调用数据处理函数 如果是注册 直接返回true 如果是登录 需要查表判断
                //从服务器返回给客户端数据

                SocketAddress sendAddress = packet.getSocketAddress();
                byte[] backbuf = feedback.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(backbuf, backbuf.length, sendAddress); //封装返回给客户端的数据
                socket.send(sendPacket);  //通过套接字反馈服务器数据
            }
            socket.close();  //关闭套接字

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public synchronized void start() {
        if (isRun) {
            if (callBackSet) {
                super.start();
            } else {
                throw new Error("plz set callBack by <setMessageReceiveCallBack(MessageReceiveCallBack)> at first");
            }

        }//防止意外调用(然而并不能阻止编译失败..要不抛个异常吧
        else {
            throw new Error("plz call Zstart() instand of start()");
        }
    }

    @Override
    public void setMessageReceiveCallBack(MessageReceiveCallBack temp) {
        messageReceiveCallBack = temp;
        callBackSet = true;
    }

    @Override
    public boolean setPORT(int i) {
        if (isRun) {
            return false;
        } else {
            PORT = i;
            return true;
        }
    }

    @Override
    public void Zstop() {
        isRun = false;
    }
}
