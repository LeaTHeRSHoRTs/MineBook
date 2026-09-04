package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.Version;

public enum Loader {
        FABRIC("Fabric"),
        FORGE("Forge"),
        NEOFORGE("NeoForge"),
        QUILT("Quilt");

        public final String loader;
        Loader(String loaderName) {
            this.loader = loaderName;
        }
}