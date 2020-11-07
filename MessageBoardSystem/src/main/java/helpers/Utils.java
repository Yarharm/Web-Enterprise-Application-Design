package helpers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;

public class Utils {
    public static String inputStreamToImage(InputStream stream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        String base64Image = "";
        int bytesRead = -1;

        try {
            while ((bytesRead = stream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            outputStream.close();
            stream.close();
        } catch (Exception ignore) {}

        return base64Image;
    }
}
