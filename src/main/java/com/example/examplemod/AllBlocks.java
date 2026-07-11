package com.example.examplemod;

import com.example.examplemod.content.kinetics.ExampleGeneratorBlock;
import com.example.examplemod.content.kinetics.ExampleKineticBlock;
import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.core.Direction;
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
                ModelFile model = encasedShaftModel(prov, ctx.getName(),
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
                ModelFile model = encasedShaftModel(prov, ctx.getName(),
                        ResourceLocation.withDefaultNamespace("block/polished_andesite"),
                        ResourceLocation.withDefaultNamespace("block/chiseled_polished_blackstone"));
                BlockStateGen.axisBlock(ctx, prov, state -> model);
            })
            .transform(DisplaySource.displaySource(AllDisplaySources.EXAMPLE_SOURCE))
            .onRegister(b -> BlockStressValues.CAPACITIES.register(b, () -> 128))
            .item()
            .build()
            .register();

    /**
     * Builds an "encased shaft" style model: a casing box inset by 2px on the rotation
     * axis so the shaft rendered by ExampleShaftRenderer visibly pokes out of both ends.
     * The box is authored along the Y axis (caps on top and bottom); BlockStateGen.axisBlock
     * rotates it to match the block's AXIS, using the same rotations as Create's shaft, so
     * the static casing and the spinning shaft always line up. Side faces use the casing
     * texture, the two end caps use the cap texture.
     */
    private static ModelFile encasedShaftModel(RegistrateBlockstateProvider prov, String name,
                                               ResourceLocation casing, ResourceLocation cap) {
        return prov.models()
                .withExistingParent(name, ResourceLocation.withDefaultNamespace("block/block"))
                .texture("particle", casing)
                .texture("casing", casing)
                .texture("cap", cap)
                .element()
                    // Inset 3px on the top and bottom (the Y axis) so the shaft pokes out.
                    .from(0, 3, 0).to(16, 13, 16)
                    .face(Direction.NORTH).texture("#casing").end()
                    .face(Direction.SOUTH).texture("#casing").end()
                    .face(Direction.WEST).texture("#casing").end()
                    .face(Direction.EAST).texture("#casing").end()
                    .face(Direction.UP).texture("#cap").end()
                    .face(Direction.DOWN).texture("#cap").end()
                    .end();
    }

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
