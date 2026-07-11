package com.example.examplemod.content.ponder;

import com.example.examplemod.AllBlocks;
import com.example.examplemod.ExampleMod;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

/**
 * Ponder plugin for the addon, registered client-side in ExampleMod. registerScenes
 * associates a storyboard with one or more items. Each scene has two parts: a schematic
 * saved as an nbt file under assets/examplemod/ponder, whose name matches the id passed
 * to addStoryBoard, and the storyboard code in ExamplePonderScenes.
 */
public class ExamplePonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return ExampleMod.ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(AllBlocks.EXAMPLE_KINETIC_BLOCK.getId())
                .addStoryBoard("example_ponder", ExamplePonderScenes::examplePonder);
    }
}
