package com.example.examplemod;

import com.example.examplemod.content.kinetics.ExampleGeneratorBlockEntity;
import com.example.examplemod.content.kinetics.ExampleKineticBlockEntity;
import com.example.examplemod.content.kinetics.ExampleShaftRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

/**
 * Block entity type registration.
 */
public class AllBlockEntityTypes {

    /**
     * Block entity for EXAMPLE_KINETIC_BLOCK, rendered with ExampleShaftRenderer so a
     * shaft visibly spins through the casing.
     */
    public static final BlockEntityEntry<ExampleKineticBlockEntity> EXAMPLE_KINETIC = ExampleMod.REGISTRATE
            .blockEntity("example_kinetic", ExampleKineticBlockEntity::new)
            .validBlock(AllBlocks.EXAMPLE_KINETIC_BLOCK)
            .renderer(() -> ExampleShaftRenderer::new)
            .register();

    /**
     * Block entity for EXAMPLE_GENERATOR_BLOCK, also rendered with ExampleShaftRenderer.
     */
    public static final BlockEntityEntry<ExampleGeneratorBlockEntity> EXAMPLE_GENERATOR = ExampleMod.REGISTRATE
            .blockEntity("example_generator", ExampleGeneratorBlockEntity::new)
            .validBlock(AllBlocks.EXAMPLE_GENERATOR_BLOCK)
            .renderer(() -> ExampleShaftRenderer::new)
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
