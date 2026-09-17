package com.leathershorts.minebook;

import com.leathershorts.minebook.game.structures.packs.Modpack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.leathershorts.minebook.Main.GSON;

public final class ModpackRepository {
    private ModpackRepository() {}

    public static List<Modpack> fetch() {
        return List.of();
    }

    public static void save(Modpack modpack) {
        String os = System.getProperty("os.name").toLowerCase();
        Path dataDir;

        if (os.contains("win")) {
            dataDir = Path.of(System.getenv("APPDATA"), "MineBook");
        } else if (os.contains("mac")) {
            dataDir = Path.of(
                System.getProperty("user.home"),
                "Library",
                "Application Support",
                "MineBook"
            );
        } else {
            dataDir = Path.of(
                System.getProperty("user.home"),
                ".local",
                "share",
                "MineBook"
            );
        }

        try {
            String gson = GSON.toJson(modpack);
            Files.writeString(dataDir, gson);
        } catch (IOException e) {
            System.out.printf("Exception occurred when trying to write modpack to file %s: %s", dataDir, e);
        }
    }

    public static void delete(Modpack modpack) {

    }

    public static void update(Modpack modpack) {

    }
}
