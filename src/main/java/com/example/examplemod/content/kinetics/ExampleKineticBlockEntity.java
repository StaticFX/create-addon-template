package com.example.examplemod.content.kinetics;

import java.util.List;

import com.example.examplemod.Lang;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Block entity for the example kinetic block. Extending KineticBlockEntity ties it
 * into the kinetic network as a consumer. Its stress impact is registered in
 * ExampleMod (using STRESS_IMPACT below) rather than here, which keeps that value
 * configurable and lets it surface in tooltips.
 */
public class ExampleKineticBlockEntity extends KineticBlockEntity {

    /** Stress Units this block draws per RPM. Registered as its impact in ExampleMod. */
    public static final float STRESS_IMPACT = 8.0f;

    public ExampleKineticBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * Adds custom lines to the Engineer's Goggles overlay. Calling super keeps the base
     * kinetic stress readout, then we append the SU this block is drawing at its current
     * speed. Returning true signals that the overlay has content to show.
     */
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        int draw = (int) (Math.abs(getSpeed()) * STRESS_IMPACT);
        tooltip.add(Component.literal("    ")
                .append(Lang.translate(Lang.KINETIC_CONSUMING).withStyle(ChatFormatting.GRAY)));
        tooltip.add(Component.literal("        ")
                .append(Lang.translate(Lang.SU, draw).withStyle(ChatFormatting.AQUA)));
        return true;
    }
}
