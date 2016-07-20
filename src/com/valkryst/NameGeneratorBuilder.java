package com.valkryst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NameGeneratorBuilder {
    /**
     * Reads each line of the specified file and returns them
     * as a list. All empty lines are skipped.
     *
     * @param path
     *         The file to read from.
     *
     * @return
     *         The lines that have been read.
     *
     * @throws IOException
     *          If the specified file doesn't exist.
     *          If an I/O error occurs.
     */
    protected List<String> readLines(final Path path) throws IOException {
        if (! Files.exists(path)) {
            throw new IOException("The file at \"" + path.toString() +"\" does not exist.");
        }

        // Attempt to read the lines using NIO.
        // If this fails, then fallback to
        // the BufferedReader method.
        try {
            return Files.readAllLines(path);
        } catch (final IOException e) {
            // Open Resources:
            final FileReader fileReader = new FileReader(path.toFile());
            final BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read Lines From File:
            final List<String> readLines = new ArrayList<>();
            String currentLine;

            while((currentLine = bufferedReader.readLine()) != null) {
                // Don't read empty lines:
                if(! currentLine.isEmpty()) {
                    readLines.add(currentLine);
                }
            }

            // Close Resources:
            bufferedReader.close();
            fileReader.close();

            // Return Read Lines:
            return readLines;
        }
    }
}
