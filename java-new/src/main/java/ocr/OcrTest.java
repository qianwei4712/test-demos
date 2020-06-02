package ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author shiva   2020/5/11 17:06
 */
public class OcrTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "11419946";
    public static final String API_KEY = "ycO2SyNLTMGPAUMBppprBNWF";
    public static final String SECRET_KEY = "L7egE3GqsGDUTZA9f5iOhg8gPX7QOwYS";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\qianw\\Desktop\\t\\1.jpg";
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "ENG");
        options.put("detect_direction", "true");
//        options.put("vertexes_location", "true");

        JSONObject res = client.basicAccurateGeneral(path, options);
//        JSONObject res = client.general(path, options);
        System.out.println(res.toString(2));

    }
}
