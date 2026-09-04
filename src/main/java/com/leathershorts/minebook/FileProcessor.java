package com.leathershorts.minebook;

import com.google.gson.Gson;
import com.leathershorts.minebook.dataclasses.Version;
import com.leathershorts.minebook.dataclasses.packs.Loader;
import com.leathershorts.minebook.dataclasses.packs.Modpack;
import com.leathershorts.minebook.manifests.CurseForgeManifest;
import com.leathershorts.minebook.manifests.ModrinthManifest;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Properties;
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

        Gson gson = new Gson();

        try (ZipFile file = new ZipFile(selectedFile)) {
            file.stream().forEach(entry -> {
                String path = entry.getName();

                Modpack modpack;

                switch (path) {
                    case "manifest.json" -> {
                        System.out.println("CurseForge modpack found.");
                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
                            CurseForgeManifest manifest = gson.fromJson(stream, CurseForgeManifest.class);
                            modpack = new Modpack(
                                manifest.name,
                                manifest.name, //desc
                                Version.parse(manifest.version),
                                Loader.FORGE,
                                List.of(),
                                List.of(),
                                List.of()
                            );
                        } catch (IOException e) {

                        }
                    }
                    case "modrinth.index.json" -> {
                        System.out.println("Modrinth modpack found.");
                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
                            ModrinthManifest manifest = gson.fromJson(stream, ModrinthManifest.class);

                            String loader = "Vanilla";
                            if (manifest.dependencies != null) {
                                if (manifest.dependencies.containsKey("fabric-loader")) loader = "Fabric";
                                else if (manifest.dependencies.containsKey("neoforge")) loader = "NeoForge";
                                else if (manifest.dependencies.containsKey("forge")) loader = "Forge";
                                else if (manifest.dependencies.containsKey("quilt-loader")) loader = "Quilt";
                            }

                            modpack = new Modpack(
                                manifest.name,
                                manifest.summary,
                                Version.parse(manifest.versionId),
                                Loader.valueOf(loader),
                                List.of(),
                                List.of(),
                                List.of()
                            );
                        } catch (IOException e) {

                        }
                    }
                    case "instance.cfg" -> {
                        System.out.println("Prism Launcher instance found.");
                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
                            Properties props = new Properties();
                            props.load(stream);

                            String name = "";

                            if (props.containsKey("name")) {
                                name =
                            }
                        } catch (IOException e) {

                        }
                    }
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
