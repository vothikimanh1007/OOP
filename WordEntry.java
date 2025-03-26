import java.util.Scanner;

class WordEntry {
    private String word;
    private String[] meanings = new String[10];
    private int meaningCount = 0;

    public WordEntry(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String[] getMeanings() {
        return meanings;
    }

    public int getMeaningCount() {
        return meaningCount;
    }

    public void addMeaning(String meaning) {
        for (int i = 0; i < meaningCount; i++) {
            if (meanings[i].equals(meaning)) {
                System.out.println("Nghĩa này đã tồn tại.");
                return;
            }
        }
        if (meaningCount < 10) {
            meanings[meaningCount++] = meaning;
            System.out.println("Đã thêm nghĩa mới.");
        } else {
            System.out.println("Đã đạt số nghĩa tối đa cho từ này.");
        }
    }

    public void removeMeaning(int index) {
        if (index >= 0 && index < meaningCount) {
            for (int i = index; i < meaningCount - 1; i++) {
                meanings[i] = meanings[i + 1];
            }
            meanings[--meaningCount] = null;
            System.out.println("Đã xóa nghĩa.");
        } else {
            System.out.println("Chỉ số không hợp lệ.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(word + ": ");
        for (int i = 0; i < meaningCount; i++) {
            sb.append("[" + i + "] " + meanings[i] + "; ");
        }
        return sb.toString();
    }
}

public class DictionaryManagementSystem {
    private static WordEntry[] dictionary = new WordEntry[100];
    private static int wordCount = 0;

    public static void addWord(String word, String meaning) {
        for (int i = 0; i < wordCount; i++) {
            if (dictionary[i].getWord().equals(word)) {
                dictionary[i].addMeaning(meaning);
                return;
            }
        }
        if (wordCount < 100) {
            WordEntry newWord = new WordEntry(word);
            newWord.addMeaning(meaning);
            dictionary[wordCount++] = newWord;
        } else {
            System.out.println("Từ điển đã đầy.");
        }
    }

    public static void lookup(String word) {
        for (int i = 0; i < wordCount; i++) {
            if (dictionary[i].getWord().equals(word)) {
                System.out.println(dictionary[i]);
                return;
            }
        }
        System.out.println("Không tìm thấy từ này.");
    }

    public static void removeMeaning(String word, int index) {
        for (int i = 0; i < wordCount; i++) {
            if (dictionary[i].getWord().equals(word)) {
                dictionary[i].removeMeaning(index);
                return;
            }
        }
        System.out.println("Không tìm thấy từ này.");
    }

    public static void showTotalWords() {
        System.out.println("Tổng số từ trong từ điển: " + wordCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm từ và nghĩa");
            System.out.println("2. Xóa nghĩa của từ");
            System.out.println("3. Tra cứu từ");
            System.out.println("4. Xem tổng số từ");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Nhập từ: ");
                String word = scanner.nextLine();
                System.out.print("Nhập nghĩa: ");
                String meaning = scanner.nextLine();
                addWord(word, meaning);
            } else if (choice == 2) {
                System.out.print("Nhập từ cần xóa nghĩa: ");
                String word = scanner.nextLine();
                lookup(word);
                System.out.print("Nhập chỉ số nghĩa cần xóa: ");
                int index = scanner.nextInt();
                scanner.nextLine();
                removeMeaning(word, index);
            } else if (choice == 3) {
                System.out.print("Nhập từ cần tra: ");
                String word = scanner.nextLine();
                lookup(word);
            } else if (choice == 4) {
                showTotalWords();
            } else if (choice == 5) {
                System.out.println("Tạm biệt!");
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
        scanner.close();
    }
}