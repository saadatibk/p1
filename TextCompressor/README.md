## TextCompressor

TextCompressor is a simple Java program that compresses and decompresses text files using a basic word-based compression technique.

## Features

- Compresses text files by replacing repeated words with numerical representations.
- Decompresses compressed text files to their original form.

## Usage

To use TextCompressor, follow these steps:

1. Clone this repository or download the TextCompressor.java file.
2. Compile the Java file using a Java compiler: javac TextCompressor.java
3. Run the compiled Java program with appropriate command-line arguments: java TextCompressor <inputFilePath> <compressedFilePath>

Replace `<inputFilePath>` with the path to the text file you want to compress, and `<compressedFilePath>` with the desired path for the compressed output file.

## Example

Suppose you have a text file named `mytext.txt` containing the following text:

There is a sign that Americans’ shopping spree might be finally coming to an end: Retail spending stayed the same in April as compared to the previous month, falling short of analyst projections for growth.
However, those numbers don’t capture spending on services — for example, health care, transportation, and insurance — which has increased markedly this year. And both Preston Caldwell, a senior US economist at Morningstar, and Scott Hoyt, a Moody’s Analytics economist, said those numbers could easily bounce back next month, even if they’re expecting spending to cool by the end of the year...

To compress this text file, you can run: 
java TextCompressor mytext.txt compressed.sc

This will compress the text file `mytext.txt` and save the compressed output to a file named `compressed.sc`.

To decompress the compressed file, you can run: 
java TextCompressor compressed.sc decompressed.txt

This will decompress the compressed file `compressed.sc`.

## License