package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.game.Version;
import com.leathershorts.minebook.game.structures.packs.Loader;
import com.leathershorts.minebook.game.structures.packs.Modpack;
import com.leathershorts.minebook.manifests.CurseForgeManifest;
import com.leathershorts.minebook.manifests.PrismLauncherManifest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.leathershorts.minebook.Main.GSON;

public class ModrinthParser implements ModpackParser {
    @Override
    public Modpack parse(ZipFile file, String manifestPath) throws IOException {
        System.out.println("Modrinth Modpack found");

        ZipEntry entry = file.getEntry(manifestPath);

        if (entry == null) {
            throw new IOException("Could not find Prism Launcher manifest: " + manifestPath);
        }

        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry), StandardCharsets.UTF_8)) {
            Properties props = new Properties();
            props.load(stream);

            String name = props.getProperty("name");
            String version = props.getProperty("IntendedVersion");
            String notes = props.getProperty("notes");
            String description = (notes != null && !notes.isBlank()) ? notes : "Prism Launcher Instance: " + name;
            String iconKey = props.getProperty("iconKey");

            Loader inferredLoader = Loader.VANILLA;
            if (iconKey != null) {
                String lowerIcon = iconKey.toLowerCase();
                if (lowerIcon.contains("fabric")) {
                    inferredLoader = Loader.FABRIC;
                } else if (lowerIcon.contains("neoforge")) {
                    inferredLoader = Loader.NEOFORGE;
                } else if (lowerIcon.contains("forge")) {
                    inferredLoader = Loader.FORGE;
                } else if (lowerIcon.contains("quilt")) {
                    inferredLoader = Loader.QUILT;
                }
            }

            if (name == null) {
                throw new IOException("Could not parse Prism Launcher configuration: missing 'name' key.");
            }

            return new Modpack(
                name,
                description,
                Version.parse(version),
                inferredLoader,
                List.of(),
                List.of(),
                List.of()
            );
        }

        return null;
    }
}
