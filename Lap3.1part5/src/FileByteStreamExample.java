
import java.io.*;

public class FileByteStreamExample {

    public static void main(String[] args) throws IOException {

        // Ghi dữ liệu vào tệp với byte streams
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream("data.txt"));
        outStream.writeUTF("Hello");
        outStream.writeInt(123);
        outStream.writeDouble(3.14159);
        outStream.close();

        // Đọc dữ liệu từ tệp với byte streams
        DataInputStream inStream = new DataInputStream(new FileInputStream("data.txt"));
        String str = inStream.readUTF();
        int num = inStream.readInt();
        double d = inStream.readDouble();
        inStream.close();

        // In dữ liệu ra màn hình
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("d = " + d);
    }
}

