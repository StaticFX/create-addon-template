package com.example.examplemod.content.kinetics;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Renderer for the example kinetic blocks. The base KineticBlockEntityRenderer spins
 * whatever getRenderedBlockState returns around the rotation axis; by default that is the
 * block's own model, which a solid casing hides. Returning Create's shaft state instead
 * makes the rotating part a shaft that pokes out of the (inset) casing model, so the
 * rotation is visible — the same trick Create uses for its encased components. One generic
 * renderer serves both the consumer and the generator.
 */
public class ExampleShaftRenderer<T extends KineticBlockEntity> extends KineticBlockEntityRenderer<T> {

    public ExampleShaftRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected BlockState getRenderedBlockState(T be) {
        return shaft(getRotationAxisOf(be));
    }
}
