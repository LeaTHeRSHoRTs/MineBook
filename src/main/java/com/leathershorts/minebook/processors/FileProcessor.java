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

        throw new InvalidObjectException(
            "No parser supports this modpack file."
        );

//        Gson gson = new Gson();
//        try (ZipFile file = new ZipFile(selectedFile)) {
//            file.stream().forEach(entry -> {
//                String path = entry.getName();
//
//                Modpack modpack;
//
//                switch (path) {
//                    case "manifest.json" -> {
//                        System.out.println("CurseForge modpack found.");
//                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
//                            CurseForgeManifest manifest = gson.fromJson(stream, CurseForgeManifest.class);
//                            modpack = new Modpack(
//                                manifest.name,
//                                manifest.name, //desc
//                                Version.parse(manifest.version),
//                                Loader.FORGE,
//                                List.of(),
//                                List.of(),
//                                List.of()
//                            );
//                        } catch (IOException e) {
//
//                        }
//                    }
//                    case "modrinth.index.json" -> {
//                        System.out.println("Modrinth modpack found.");
//                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
//                            ModrinthManifest manifest = gson.fromJson(stream, ModrinthManifest.class);
//
//                            String loader = "Vanilla";
//                            if (manifest.dependencies != null) {
//                                if (manifest.dependencies.containsKey("fabric-loader")) loader = "Fabric";
//                                else if (manifest.dependencies.containsKey("neoforge")) loader = "NeoForge";
//                                else if (manifest.dependencies.containsKey("forge")) loader = "Forge";
//                                else if (manifest.dependencies.containsKey("quilt-loader")) loader = "Quilt";
//                            }
//
//                            modpack = new Modpack(
//                                manifest.name,
//                                manifest.summary,
//                                Version.parse(manifest.versionId),
//                                Loader.valueOf(loader),
//                                List.of(),
//                                List.of(),
//                                List.of()
//                            );
//                        } catch (IOException e) {
//
//                        }
//                    }
//                    case "instance.cfg" -> {
//                        System.out.println("Prism Launcher instance found.");
//                        try (InputStreamReader stream = new InputStreamReader(file.getInputStream(entry))) {
//                            Properties props = new Properties();
//                            props.load(stream);
//
//                            String name = "";
//
//                            if (props.containsKey("name")) {
//                                name =
//                            }
//                        } catch (IOException e) {
//
//                        }
//                    }
//                }
//
//                System.out.println(path);
//            });
//        } catch (IOException e) {
//            System.out.println("Could not read modpack: " + e.getMessage());
//        }
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
