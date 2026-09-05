package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.dataclasses.packs.Modpack;

import java.io.IOException;
import java.util.zip.ZipFile;

public class ModrinthParser implements ModpackParser {
    @Override
    public Modpack parse(ZipFile archive, String manifestPath) throws IOException {
        return null;
    }
}
