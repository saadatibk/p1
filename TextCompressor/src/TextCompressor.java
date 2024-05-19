import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextCompressor {

    private static final Map<String, Integer> wordToNumber = new HashMap<>();
    private static final Map<Integer, String> numberToWord = new HashMap<>();

    public static String readTextFile(String filename) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString().trim();
    }

    public static void writeTextFile(String text, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(text);
        }
    }

    public static String compress(String data) {
        int wordCount = 0;
        StringBuilder compressedData = new StringBuilder();

        String[] words = data.split("\\s+");
        for (String word : words) {
            if (!wordToNumber.containsKey(word)) {
                wordToNumber.put(word, ++wordCount);
                numberToWord.put(wordCount, word);
            }
            compressedData.append(wordToNumber.get(word)).append(" ");
        }

        return compressedData.toString().trim();
    }

    public static String decompress(String compressedData) {
        StringBuilder decompressedText = new StringBuilder();

        String[] numbers = compressedData.split("\\s+");
        for (String number : numbers) {
            int wordNumber = Integer.parseInt(number);
            decompressedText.append(numberToWord.get(wordNumber)).append(" ");
        }

        return decompressedText.toString().trim();
    }

    public static void main(String[] args) {
        String inputFilePath = "TextCompressor/mytext.txt";
        String compressedFilePath = "TextCompressor/compressed.sc";


        try {
            String originalData = readTextFile(inputFilePath);

            String compressedData = compress(originalData);

            writeTextFile(compressedData, compressedFilePath);

            String readCompressedData = readTextFile(compressedFilePath);

            String decompressedData = decompress(readCompressedData);
            
            if (originalData.equals(decompressedData)) {
                System.out.println("Compression and decompression successful. Output matches input.");
            } else {
                System.out.println("Compression and decompression failed. Output does not match input.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


