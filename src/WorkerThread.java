import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WorkerThread implements Runnable {
    private String URL;
    private String email;
    private String passwd;

    WorkerThread(String URL, String email, String passwd){
        this.URL = URL;
        this.email = email;
        this.passwd = passwd;
    }

    @Override
    public void run(){
        while (true){
            try {
                CloseableHttpClient httpclient = HttpClients.custom().build();
                final ClassicHttpRequest login = ClassicRequestBuilder.post()
                        .setUri(new URI(URL))
                        .addParameter("email", email)
                        .addParameter("passwd", passwd)
                        .addParameter("signin", "Continue")
                        .build();
                final CloseableHttpResponse response2 = httpclient.execute(login);
                final HttpEntity entity = response2.getEntity();
                System.out.println("Login form get: " + response2.getCode() + " " + response2.getReasonPhrase());
                EntityUtils.consume(entity);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}