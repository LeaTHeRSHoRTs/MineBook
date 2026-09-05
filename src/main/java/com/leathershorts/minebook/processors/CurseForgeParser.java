package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.dataclasses.Version;
import com.leathershorts.minebook.dataclasses.packs.Loader;
import com.leathershorts.minebook.dataclasses.packs.Modpack;
import com.leathershorts.minebook.manifests.CurseForgeManifest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.leathershorts.minebook.Main.GSON;

public class CurseForgeParser implements ModpackParser {
    @Override
    public Modpack parse(ZipFile file, String manifestPath) throws IOException {
        System.out.println("CurseForge modpack found.");

        ZipEntry entry = file.getEntry(manifestPath);

        if (entry == null) {
            throw new IOException("Could not find CurseForge manifest: " + manifestPath);
        }

        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry), StandardCharsets.UTF_8)) {
            CurseForgeManifest manifest = GSON.fromJson(stream, CurseForgeManifest.class);

            if (manifest == null) {
                throw new IOException("Could not parse CurseForge manifest.");
            }

            return new Modpack(
                manifest.name,
                "CurseForge Mod " + manifest.name,
                Version.parse(manifest.version),
                Loader.FORGE,
                List.of(),
                List.of(),
                List.of()
            );
        }
    }
}
