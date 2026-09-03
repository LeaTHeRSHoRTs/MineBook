package com.leathershorts.minebook;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileProcessor {
    public static void processModpack(File selectedFile) throws InvalidObjectException {
        if (selectedFile == null || !selectedFile.isFile()) {
            throw new InvalidObjectException("Cannot read a file if it is not a file.");
        }

        if (!selectedFile.getName().toLowerCase().matches(".*\\.(zip|mrpack)$")) {
            throw new InvalidObjectException("Cannot read files that do not have the extension .zip or .mrpack");
        }

        try (ZipFile file = new ZipFile(selectedFile)) {
            file.stream().forEach(entry -> {
                String path = entry.getName();

                switch (path) {
                    case "manifest.json" -> System.out.println("CurseForge modpack found.");
                    case "modrinth.index.json" -> System.out.println("Modrinth modpack found.");
                    case "instance.cfg" -> System.out.println("Prism Launcher instance found.");
                }

                System.out.println(path);
            });
        } catch (IOException e) {
            System.out.println("Could not read modpack: " + e.getMessage());
        }
    }

    private static void processModrinth(ZipEntry entry) {

    }

    private static void processCurseForge(ZipEntry entry) {

    }

    private static void processPrismLauncher(ZipEntry entry) {

    }
}
