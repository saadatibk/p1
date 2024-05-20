import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextCompressor {

    private static final Map<String, Integer> wordToNumber = new HashMap<>();
    private static final Map<Integer, String> numberToWord = new HashMap<>();
    private static int wordCount = 0;

    public static String readTextFile(String filename) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    public static void writeCompressedDataToFile(byte[] compressedData, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(compressedData);
        }
    }

    public static byte[] readCompressedDataFromFile(String filename) throws IOException {
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            return data;
        }
    }

    public static byte[] compress(String originalData) {
        StringBuilder compressedData = new StringBuilder();
        String[] words = originalData.split("\\s+");

        for (String word : words) {
            if (!wordToNumber.containsKey(word)) {
                wordToNumber.put(word, ++wordCount);
                numberToWord.put(wordCount, word);
            }
            compressedData.append(wordToNumber.get(word)).append(" ");
        }

        return compressedData.toString().trim().getBytes();
    }

    public static String decompress(byte[] compressedData) {
        StringBuilder decompressedText = new StringBuilder();
        String compressedString = new String(compressedData);
        String[] numbers = compressedString.split("\\s+");

        for (String number : numbers) {
            int wordNumber = Integer.parseInt(number);
            String word = numberToWord.get(wordNumber);
            decompressedText.append(word).append(" ");
        }

        return decompressedText.toString().trim();
    }

    public static void main(String[] args) {
        String inputFilePath = "TextCompressor/mytext.txt";
        String compressedFilePath = "TextCompressor/compressed.sc";

        try {
            String originalData = readTextFile(inputFilePath);
            byte[] compressedData = compress(originalData);
            writeCompressedDataToFile(compressedData, compressedFilePath);

            byte[] readCompressedData = readCompressedDataFromFile(compressedFilePath);
            String decompressedData = decompress(readCompressedData);

            if (originalData.equals(decompressedData)) {
                System.out.println("Compression and decompression successful. Output matches input.");
            } else {
                System.out.println("Compression and decompression failed. Output does not match input.");
            }
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Number format error: " + e.getMessage());
        }
    }
}




