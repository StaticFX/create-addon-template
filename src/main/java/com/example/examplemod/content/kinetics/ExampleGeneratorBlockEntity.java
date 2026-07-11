package com.example.examplemod.content.kinetics;

import java.util.List;

import com.example.examplemod.Lang;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block entity for the example generator. Extending GeneratingKineticBlockEntity makes
 * it a source of rotation rather than a consumer. Two things define it: getGeneratedSpeed
 * returns the RPM it produces (the sign sets the direction), and its capacity is
 * registered in ExampleMod. Here the speed is a constant; making it configurable is a
 * common next step.
 */
public class ExampleGeneratorBlockEntity extends GeneratingKineticBlockEntity {

    /** RPM this generator adds to the network. */
    public static final int GENERATED_RPM = 16;

    public ExampleGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getGeneratedSpeed() {
        return GENERATED_RPM;
    }

    /**
     * Adds custom lines to the goggle overlay. Calling super keeps the stress readout;
     * returning true signals that the overlay has content to show.
     */
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        tooltip.add(Component.literal("    ")
                .append(Lang.translate(Lang.GENERATOR_GENERATING).withStyle(ChatFormatting.GRAY)));
        tooltip.add(Component.literal("        ")
                .append(Lang.translate(Lang.RPM, GENERATED_RPM).withStyle(ChatFormatting.AQUA)));
        return true;
    }
}
