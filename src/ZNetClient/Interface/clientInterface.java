package ZNetClient.Interface;

/**
 * Created by zhuangh7 on 17-6-20.
 */
public interface clientInterface {
    /**
     *
     * @param msg the message you want to send
     * @return the message that origin server returned
     */
    String Zsend(String msg);

    /**
     * you should call this fun before Zsend()
     * @param PORT the origin server's port
     */
    void setPORT(int PORT);

    /**
     * you should call this fun before Zsend()
     * @param addr the origin server's address
     */
    void setADDR(String addr);
}
