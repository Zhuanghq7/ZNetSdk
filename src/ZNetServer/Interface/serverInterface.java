package ZNetServer.Interface;

/**
 * Created by zhuangh7 on 17-6-20.
 */
public interface serverInterface {
    void Zstart();//启动服务器线程
    void Zstop();
    boolean setPORT(int i);
    /**
     *
     * @param temp your specific callback
     */
    void setMessageReceiveCallBack(MessageReceiveCallBack temp);//接受到包反馈 如果需要返回包给客户端也写在这里
}
