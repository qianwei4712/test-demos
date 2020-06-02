package ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author shiva   2020/5/11 17:06
 */
public class OcrTest {
    //����APPID/AK/SK
    public static final String APP_ID = "11419946";
    public static final String API_KEY = "ycO2SyNLTMGPAUMBppprBNWF";
    public static final String SECRET_KEY = "L7egE3GqsGDUTZA9f5iOhg8gPX7QOwYS";

    public static void main(String[] args) {
        // ��ʼ��һ��AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // ��ѡ�������������Ӳ���
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // ��ѡ�����ô����������ַ, http��socket��ѡһ�����߾�������
//        client.setHttpProxy("proxy_host", proxy_port);  // ����http����
//        client.setSocketProxy("proxy_host", proxy_port);  // ����socket����

        // ��ѡ������log4j��־�����ʽ���������ã���ʹ��Ĭ������
        // Ҳ����ֱ��ͨ��jvm�����������ô˻�������
        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // ���ýӿ�
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
