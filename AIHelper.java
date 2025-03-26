import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIHelper {
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "YOUR_OPENAI_API_KEY"; // Thay bằng API key của bạn

    public static String getDefinition(String word) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setDoOutput(true);

            // Prompt mẫu để gợi nghĩa từ
            String prompt = "Hãy định nghĩa từ khóa: " + word;

            // Dữ liệu JSON gửi lên API
            String jsonInputString = "{"
                    + "\"model\": \"text-davinci-003\","
                    + "\"prompt\": \"" + prompt + "\","
                    + "\"max_tokens\": 50"
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Đọc dữ liệu trả về
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            br.close();

            // Trích xuất nội dung từ JSON trả về (đơn giản hóa)
            String result = response.toString();
            int start = result.indexOf("\"text\":\"") + 8;
            int end = result.indexOf("\"", start);
            if (start > 7 && end > start) {
                return result.substring(start, end).replace("\\n", " ").trim();
            } else {
                return "[Không thể phân tích kết quả từ API]";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "[Lỗi khi gọi API AI: " + e.getMessage() + "]";
        }
    }
}
