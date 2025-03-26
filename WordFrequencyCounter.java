import java.util.Scanner;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập đoạn văn:");
        String paragraph = scanner.nextLine();

        // Xử lý chuỗi: chuyển về chữ thường và tách từ
        String[] words = paragraph.toLowerCase().replaceAll("[^a-zA-Z\s]", "").split("\\s+");

        String[][] frequencyArray = new String[1000][2]; // mảng 2 chiều lưu từ và số lần xuất hiện
        int count = 0;

        for (String word : words) {
            if (word.isEmpty()) continue;
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (frequencyArray[i][0].equals(word)) {
                    int currentFreq = Integer.parseInt(frequencyArray[i][1]);
                    frequencyArray[i][1] = String.valueOf(currentFreq + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                frequencyArray[count][0] = word;
                frequencyArray[count][1] = "1";
                count++;
            }
        }

        System.out.println("\nTần suất từ trong đoạn văn:");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-15s : %s\n", frequencyArray[i][0], frequencyArray[i][1]);
        }

        scanner.close();
    }
}
