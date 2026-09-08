package com.leathershorts.minebook.game.structures.packs;

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