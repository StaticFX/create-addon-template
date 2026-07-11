package com.example.examplemod;

import com.example.examplemod.content.kinetics.ExampleGeneratorBlock;
import com.example.examplemod.content.kinetics.ExampleKineticBlock;
import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ModelFile;

/**
 * Block registration.
 */
public class AllBlocks {

    /**
     * A kinetic block that draws stress from the network. Its blockstate is generated as
     * an axis-aligned column and it reuses vanilla textures; swap them for your own.
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
            // here is where you can adjust how much stress your block scales with (for example rpm x 128 for this block is the amount of su)
            .onRegister(b -> BlockStressValues.IMPACTS.register(b, () -> 128))
            .item()
            .build()
            .register();

    /**
     * A kinetic generator, the counterpart to EXAMPLE_KINETIC_BLOCK; its capacity is
     * registered in ExampleMod. The transform call attaches the EXAMPLE_SOURCE display
     * source to this block.
     */
    public static final BlockEntry<ExampleGeneratorBlock> EXAMPLE_GENERATOR_BLOCK = ExampleMod.REGISTRATE
            .block("example_generator_block", ExampleGeneratorBlock::new)
            .initialProperties(() -> Blocks.POLISHED_ANDESITE)
            .properties(p -> p.noOcclusion())
            .blockstate((ctx, prov) -> {
                ModelFile model = prov.models().cubeColumn(
                        ctx.getName(),
                        ResourceLocation.withDefaultNamespace("block/polished_andesite"),
                        ResourceLocation.withDefaultNamespace("block/chiseled_polished_blackstone"));
                BlockStateGen.axisBlock(ctx, prov, state -> model);
            })
            .transform(DisplaySource.displaySource(AllDisplaySources.EXAMPLE_SOURCE))
            .item()
            .build()
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
