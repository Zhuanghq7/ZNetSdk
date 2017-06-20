import ZNetClient.ZClient;
import ZNetServer.Interface.MessageReceiveCallBack;
import ZNetServer.ZServer;
import org.junit.Test;

/**
 * Created by zhuangh7 on 17-6-20.
 */
public class Test01 {
    ZServer server = new ZServer();
    ZClient client = new ZClient("127.0.0.1", 1234);

    @Test
    public void mainTest() {
        server.setPORT(1234);

//        server.setMessageReceiveCallBack(new MessageReceiveCallBack() {
//            @Override
//            public String messageReceiveCallBack(String temp) {
//                System.out.println("receive");
//                return "test";
//            }
//        });
        server.setMessageReceiveCallBack((String temp) -> {
            System.out.println("receiveCallBack");
            return "test";
        });

        server.Zstart();
        System.out.println(client.Zsend("123"));

        server.Zstop();
    }
}
