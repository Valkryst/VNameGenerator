package com.valkryst;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NameGeneratorBuilder {
    /**
     * Reads each line of the specified file and returns them as a list.
     * All empty lines are skipped.
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

        final List<String> lines = Files.readAllLines(path);
        lines.removeIf(String::isEmpty);

        return lines;
    }
}
