package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.game.structures.packs.Modpack;

import java.io.IOException;
import java.util.zip.ZipFile;

public interface ModpackParser {
    Modpack parse(ZipFile archive, String manifest) throws IOException;
}
