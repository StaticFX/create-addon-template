package com.example.examplemod.content.display;

import java.util.List;

import com.simibubi.create.api.behaviour.display.DisplaySource;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create.content.redstone.displayLink.target.DisplayTargetStats;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Display source that reports the speed of a kinetic block. It is registered in
 * AllDisplaySources and attached to a block in AllBlocks. provideText returns the
 * lines to show, or EMPTY when there is nothing to report.
 */
public class ExampleDisplaySource extends DisplaySource {

    @Override
    public List<MutableComponent> provideText(DisplayLinkContext context, DisplayTargetStats stats) {
        BlockEntity source = context.getSourceBlockEntity();
        if (source instanceof KineticBlockEntity kinetic) {
            int rpm = Math.round(kinetic.getSpeed());
            return List.of(Component.literal(rpm + " RPM"));
        }
        return EMPTY;
    }
}
