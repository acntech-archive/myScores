package myscores.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class EnvUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvUtil.class);

    private EnvUtil() {
    }

    public static Platform getPlatform() {
        String os = System.getProperty("os.name", "generic").toLowerCase();
        if (os.startsWith("windows")) {
            return Platform.WINDOWS;
        } else if (os.startsWith("linux")) {
            return Platform.LINUX;
        } else if (os.startsWith("mac") || os.startsWith("darwin")) {
            return Platform.MAC;
        } else {
            return Platform.GENERIC;
        }
    }

    public static String getHomeDir() {
        Platform platform = getPlatform();
        switch (platform) {
            case LINUX:
                return System.getenv("HOME");
            default:
                throw new EnvException("Only Linux supported, YOLO!");
        }
    }

    public static String getAppRoot() {
        return createDir(getHomeDir(), ".myscores");
    }

    public static String getDbDir() {
        return createDir(getAppRoot(), "myscores-neo4j-db");
    }

    private static String createDir(String root, String dir) {
        FileSystem fs = FileSystems.getDefault();
        if (!Files.isDirectory(fs.getPath(root))) {
            throw new EnvException("Given root is not a directory");
        }
        Path dirPath = fs.getPath(root, dir);
        if (!Files.isDirectory(dirPath)) {
            try {
                LOGGER.debug("Creating directory {} in root directory {}", dir, root);
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                throw new EnvException("Error while trying to create app directory", e);
            }
        }
        return dirPath.toString();
    }
}
