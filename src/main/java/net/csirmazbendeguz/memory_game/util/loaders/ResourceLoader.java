package net.csirmazbendeguz.memory_game.util.loaders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceLoader {

    /**
     * Load the card image names.
     *
     * @return The card image names.
     */
    public List<String> getCardImageNames() {
        URI uri = getCardsFolderURI();
        Path path = getCardsFolderPath(uri);

        try (Stream<Path> paths = Files.walk(path, 1)) {
            return paths.filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException("Failed to load the card image names.", exception);
        }
    }

    private URI getCardsFolderURI() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("images/cards");

        if (url == null) {
            throw new RuntimeException("Can't find the cards folder.");
        }

        try {
            return url.toURI();
        } catch (URISyntaxException exception) {
            throw new RuntimeException("Can't convert the cards folder URL to a URI.", exception);
        }
    }

    private Path getCardsFolderPath(URI uri) {
        if (!uri.getScheme().equals("jar")) {
            return Paths.get(uri);
        }

        FileSystem fileSystem;

        try {
            fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        } catch (FileSystemAlreadyExistsException exception) {
            fileSystem = FileSystems.getFileSystem(uri);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to load the card image names.", exception);
        }

        return fileSystem.getPath("images/cards");
    }

}
