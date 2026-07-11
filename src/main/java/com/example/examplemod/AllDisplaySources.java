package com.example.examplemod;

import com.example.examplemod.content.display.ExampleDisplaySource;
import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.tterrag.registrate.util.entry.RegistryEntry;

/**
 * Display source registration. Attach an entry to a block in AllBlocks with
 * transform(DisplaySource.displaySource(entry)).
 */
public class AllDisplaySources {

    public static final RegistryEntry<DisplaySource, ExampleDisplaySource> EXAMPLE_SOURCE = ExampleMod.REGISTRATE
            .displaySource("example_source", ExampleDisplaySource::new)
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
