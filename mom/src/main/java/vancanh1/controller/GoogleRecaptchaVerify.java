package vancanh1.controller;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * Created by vhphat on 11/29/2016.
 */
public class GoogleRecaptchaVerify {
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET_KEY = "6LcYRA0UAAAAANHy2jVfPk8ezDrmSxGDPfoU4a2O";

    public static boolean verify( String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }
        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);
            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();

            // Mã trả lời trả về từ Server.
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode=" + responseCode);

            // Lấy InputStream từ Connection để đọc dữ liệu gửi về từ Server.
            InputStream is = conn.getInputStream();
            String response = getStringFromInputStream(is);
            return response.contains("\"success\": true");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
