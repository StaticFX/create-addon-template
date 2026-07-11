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
 * to addStoryBoard, and the storyboard code in ExamplePonderScenes. The schematic and
 * scene here are left as a stub to fill in.
 */
public class ExamplePonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return ExampleMod.ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        // The id here is also the schematic file name, loaded from
        // assets/examplemod/ponder/example_kinetic_block.nbt.
        helper.forComponents(AllBlocks.EXAMPLE_KINETIC_BLOCK.getId())
                .addStoryBoard("example_kinetic_block", ExamplePonderScenes::kineticBlock);
    }
}
