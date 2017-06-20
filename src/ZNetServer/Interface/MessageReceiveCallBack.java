package ZNetServer.Interface;

/**
 * Created by zhuangh7 on 17-6-20.
 */
public interface MessageReceiveCallBack {
    /**
     *
     * @param temp the message
     * @return the String back to client
     */
    String messageReceiveCallBack(String temp);
}
