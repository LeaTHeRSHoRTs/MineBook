package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.game.structures.packs.Modpack;

import java.io.IOException;
import java.util.zip.ZipFile;

public class PrismLauncherParser implements ModpackParser {
    @Override
    public Modpack parse(ZipFile archive, String manifest) throws IOException {
        System.out.println("Prism Launcher modpack found");
        return null;
    }
}
