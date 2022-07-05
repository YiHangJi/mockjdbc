package no.maddin.mockjdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Han at 2022/7/5.
 * email:   lynn.jiang@ximalaya.com
 */
public class LogUtil {

    public static void append(String s) {
        try {
            Path path = Paths.get("/tmp/mock.log");
            if (!Files.exists(path)) {
                Files.createFile(path);
            }

            String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

            Files.write(
                    path,
                    (time + ": " + s + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
