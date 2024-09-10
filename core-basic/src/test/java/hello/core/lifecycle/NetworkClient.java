package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("url = " + null);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message: " + message);
    }

    public void disconnect() {
        System.out.println("disconnect = " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("NetworkClient afterPropertiesSet ==>  ");
        connect();
        call("초기화 연결");
    }

    @Override
    public void destroy() throws Exception {
        System.out.print("NetworkClient destroy =>  ");
        disconnect();
    }

}
