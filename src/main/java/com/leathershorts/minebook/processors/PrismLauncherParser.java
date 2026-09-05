package com.leathershorts.minebook.processors;

import com.leathershorts.minebook.dataclasses.packs.Modpack;

import java.io.IOException;
import java.util.zip.ZipFile;

public class PrismLauncherParser implements ModpackParser {
    @Override
    public boolean supports(String fileName) {
        return false;
    }

    @Override
    public Modpack parse(ZipFile archive) throws IOException {
        return null;
    }
}
