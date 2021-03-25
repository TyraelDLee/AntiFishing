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

public class Worker {
    public static String base = "abcdefghijklmnopqrstuvwxyz0123456789";
public static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@gmail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");

    private static void boom(String URL, String email, String passwd){
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

    public static String getEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int)(Math.random()*email_suffix.length)]);
        return sb.toString();
    }

    public static String getPass(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    public static void main(String[] args) {
        while (true){
            try {
                boom("https://redemco.com/wp-content/slimj/slim/UZIE.HOTTIEzip/UZIE/actions.php",
                        getEmail(5,50),getPass(5,50));
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
