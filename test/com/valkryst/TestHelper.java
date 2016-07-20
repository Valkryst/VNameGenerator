package com.valkryst;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestHelper {
    /**
     * Determines the path to the specified resource file.
     *
     * @param path
     *         The resource file to look for.
     *
     * @return
     *         The path to the resource file.
     */
    public static Path resource(final String path)  {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final URL resourceUrl = classloader.getResource(path);

        try {
            final Path resourcePath = Paths.get(resourceUrl.toURI());
            return resourcePath;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
