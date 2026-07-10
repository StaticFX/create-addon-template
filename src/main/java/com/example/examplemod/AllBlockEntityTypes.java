package com.example.examplemod;

import com.example.examplemod.content.kinetics.ExampleKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

/**
 * Block entity type registration using Create's Registrate.
 */
public class AllBlockEntityTypes {

    /**
     * Block entity for {@link AllBlocks#EXAMPLE_KINETIC_BLOCK}. We reuse Create's
     * {@link KineticBlockEntityRenderer}, which rotates the block's model in sync
     * with the kinetic network.
     */
    public static final BlockEntityEntry<ExampleKineticBlockEntity> EXAMPLE_KINETIC = ExampleMod.REGISTRATE
            .blockEntity("example_kinetic", ExampleKineticBlockEntity::new)
            .validBlock(AllBlocks.EXAMPLE_KINETIC_BLOCK)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
