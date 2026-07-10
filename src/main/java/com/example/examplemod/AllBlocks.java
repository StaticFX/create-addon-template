package com.example.examplemod;

import com.example.examplemod.content.kinetics.ExampleKineticBlock;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ModelFile;

/**
 * Block registration using Create's Registrate.
 */
public class AllBlocks {

    /**
     * A kinetic block that draws Stress Units from the network. See
     * {@link ExampleKineticBlock} and {@link AllBlockEntityTypes#EXAMPLE_KINETIC}.
     * <p>
     * The blockstate is generated as an axis-oriented column so it looks like an
     * axle; the {@code KineticBlockEntityRenderer} spins the whole model when the
     * network is running. Textures reuse vanilla stripped-log art so the example
     * renders with no extra asset files — swap them for your own.
     */
    public static final BlockEntry<ExampleKineticBlock> EXAMPLE_KINETIC_BLOCK = ExampleMod.REGISTRATE
            .block("example_kinetic_block", ExampleKineticBlock::new)
            .initialProperties(() -> Blocks.ANDESITE)
            .properties(p -> p.noOcclusion())
            .blockstate((ctx, prov) -> {
                ModelFile model = prov.models().cubeColumn(
                        ctx.getName(),
                        ResourceLocation.withDefaultNamespace("block/stripped_spruce_log"),
                        ResourceLocation.withDefaultNamespace("block/spruce_planks"));
                BlockStateGen.axisBlock(ctx, prov, state -> model);
            })
            .item()
            .build()
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
