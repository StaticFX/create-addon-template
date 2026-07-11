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
 * it a source of rotation rather than a consumer. Three things define it: getGeneratedSpeed
 * returns the RPM it produces (the axis sets the direction), initialize seeds that rotation
 * into the network on load, and its capacity is registered in AllBlocks. Here the speed is a
 * constant; making it configurable is a common next step.
 */
public class ExampleGeneratorBlockEntity extends GeneratingKineticBlockEntity {

    /** RPM this generator adds to the network. */
    public static final int GENERATED_RPM = 16;

    public ExampleGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * Pushes the generated rotation into the network when the block entity loads or is
     * placed. Without this a generator's getGeneratedSpeed is never applied, so it stays at
     * 0 RPM and nothing downstream turns. The guard mirrors Create's own generators: only
     * (re)assert our speed if we aren't already being driven faster by another source.
     */
    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed()) {
            updateGeneratedRotation();
        }
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
