package com.example.examplemod.content.kinetics;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block entity for the example kinetic block. Extending KineticBlockEntity ties it
 * into the kinetic network as a consumer. Its stress impact is registered in
 * ExampleMod rather than here, which keeps that value in one place and configurable.
 */
public class ExampleKineticBlockEntity extends KineticBlockEntity {

    public ExampleKineticBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
