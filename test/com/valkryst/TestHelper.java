package com.valkryst;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TestHelper {
    public static Path resource(String path)  {
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
