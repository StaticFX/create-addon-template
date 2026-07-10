package com.example.examplemod.content.kinetics;

import com.example.examplemod.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.foundation.block.IBE;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A simple kinetic block that consumes Stress Units.
 * <p>
 * It extends {@link RotatedPillarKineticBlock}, so it behaves like an encased
 * shaft: it has an {@code AXIS} property and connects to rotation on both ends of
 * that axis. Implementing {@link IBE} links the block to its
 * {@link ExampleKineticBlockEntity} (Create wires up {@code newBlockEntity} and
 * the ticker for us).
 */
public class ExampleKineticBlock extends RotatedPillarKineticBlock implements IBE<ExampleKineticBlockEntity> {

    public ExampleKineticBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        // Accept a shaft on either end of our rotation axis.
        return face.getAxis() == state.getValue(AXIS);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public Class<ExampleKineticBlockEntity> getBlockEntityClass() {
        return ExampleKineticBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends ExampleKineticBlockEntity> getBlockEntityType() {
        return AllBlockEntityTypes.EXAMPLE_KINETIC.get();
    }
}
