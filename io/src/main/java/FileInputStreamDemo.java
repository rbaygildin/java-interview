import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        URL url = FileInputStreamDemo.class.getClassLoader().getResource("in.txt");
        try(FileInputStream s = new FileInputStream(url.getFile())){
            int c;
            while((c = s.read()) != -1){
                System.out.print((char)c);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
