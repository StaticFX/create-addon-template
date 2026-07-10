package com.example.examplemod.content.kinetics;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block entity for {@link ExampleKineticBlock}.
 * <p>
 * Extending {@link KineticBlockEntity} makes this block part of Create's kinetic
 * network: it will spin when connected to a running shaft/cogwheel and it draws
 * Stress Units (SU) from the network.
 * <p>
 * The amount of SU it consumes ("stress impact") is registered in
 * {@code ExampleMod#registerStressValues} via {@code BlockStressValues.IMPACTS}.
 * That is the idiomatic way to give a block a stress impact — it also makes the
 * value show up in the Engineer's Goggles tooltip and in Ponder.
 */
public class ExampleKineticBlockEntity extends KineticBlockEntity {

    public ExampleKineticBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
