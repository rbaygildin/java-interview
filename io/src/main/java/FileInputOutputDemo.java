import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileInputOutputDemo {
    public static void main(String[] args) {
        URL url = FileInputStreamDemo.class.getClassLoader().getResource("in.txt");
        try (
                FileInputStream in = new FileInputStream(url.getFile());
                FileOutputStream out = new FileOutputStream("out.txt")
        ) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
