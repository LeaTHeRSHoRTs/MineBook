package com.leathershorts.minebook.processors;

import com.google.gson.Gson;
import com.leathershorts.minebook.game.structures.packs.Modpack;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Map;
import java.util.function.Supplier;
import java.util.zip.ZipFile;

public final class FileProcessor {
    private static final Map<String, Supplier<ModpackParser>> PARSERS = Map.of(
        "manifest.json", CurseForgeParser::new,
        "modrinth.index.json", ModrinthParser::new,
        "instance.cfg", PrismLauncherParser::new
    );

    private FileProcessor() {}

    public static Modpack processModpack(File selectedFile) throws IOException {
        validateFile(selectedFile);

        try (ZipFile archive = new ZipFile(selectedFile)) {
            for (Map.Entry<String, Supplier<ModpackParser>> entry : PARSERS.entrySet()) {
                String manifestPath = entry.getKey();

                if (archive.getEntry(manifestPath) != null) {
                    ModpackParser parser = entry.getValue().get();
                    return parser.parse(archive, manifestPath);
                }
            }
        }

        throw new InvalidObjectException("No parser supports this modpack file.");
    }

    private static void validateFile(File file) throws InvalidObjectException {
        if (file == null || !file.isFile()) {
            throw new InvalidObjectException("Cannot read a file if it is not a file.");
        }

        if (!file.getName().toLowerCase().matches(".*\\.(zip|mrpack)$")) {
            throw new InvalidObjectException("Cannot read files that do not have the extension .zip or .mrpack");
        }

    }

    public static Gson makeModpackJson(Modpack pack) {
        return null;
    }
}
