import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileInputStreamByteArrayDemo {
    public static void main(String[] args) {
        String name = FileInputStreamDemo.class.getClassLoader().getResource("in.txt").getFile();
        try (FileInputStream in = new FileInputStream(name)){
            byte[] a = new byte[2048];
            while(in.read(a) != -1){
                System.out.println(new String(a).trim());
                Arrays.fill(a, (byte) 0);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
