import java.io.*;

public class EncodeTextFile {
    public static void main(String[] args) {
        try {
            // Đọc nội dung của tệp văn bản đầu vào
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            String encodedText = "";

            // Mã hóa nội dung của tệp văn bản
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c >= 'a' && c <= 'z') {
                        c = (char) (c + 3);
                        if (c > 'z') {
                            c = (char) (c - 26);
                        }
                    } else if (c >= 'A' && c <= 'Z') {
                        c = (char) (c + 3);
                        if (c > 'Z') {
                            c = (char) (c - 26);
                        }
                    }
                    encodedText += c;
                }
                encodedText += "\n";
                line = reader.readLine();
            }
            reader.close();

            // Lưu nội dung được mã hóa vào tệp văn bản đầu ra
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(encodedText);
            writer.close();

            System.out.println("Đã mã hóa thành công tệp văn bản!");
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}