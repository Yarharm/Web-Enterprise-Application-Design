package helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

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

    public static String buildTargetFilePath(String fileName) throws IOException {
        Path rootPath = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();
        if(rootPath.toString().toLowerCase().contains("tomcat")) {
            rootPath = rootPath.getParent();
        }
        Optional<Path> hit = Files.walk(rootPath)
                .filter(file -> file.getFileName().toString().equals(fileName))
                .findAny();

        return hit.get().toString();
    }
}
