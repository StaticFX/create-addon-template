package com.example.examplemod.content.ponder;

import com.example.examplemod.AllBlocks;
import com.example.examplemod.ExampleMod;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

/**
 * Ponder integration for the addon.
 * <p>
 * Ponder is Create's in-game "animated manual": pressing <kbd>W</kbd> on an item in
 * an inventory plays a scripted scene that explains how the item works. A
 * {@link PonderPlugin} is how an addon contributes its own scenes; Create ships one
 * ({@code CreatePonderPlugin}) exactly like this.
 * <p>
 * <b>Registration.</b> The plugin is registered client-side in
 * {@code ExampleMod#onClientSetup} via {@code PonderIndex.addPlugin(...)} — Ponder
 * is a client-only system, so it must not be touched from common/server setup.
 * <p>
 * <b>What a scene needs.</b> Each scene is a pair of:
 * <ul>
 *   <li>a <i>schematic</i> — a small structure saved as an {@code .nbt} file under
 *       {@code src/main/resources/assets/examplemod/ponder/<name>.nbt}. Build it in a
 *       creative world and export it with the Ponder schematic tools (or copy an
 *       existing one), naming it to match the id passed to {@code addStoryBoard}.</li>
 *   <li>a <i>storyboard</i> — the code that animates that structure, written as a
 *       {@link net.createmod.ponder.api.scene.PonderStoryBoard} (see
 *       {@link ExamplePonderScenes}).</li>
 * </ul>
 * The scene body itself is intentionally left as a stub — fill it in when you build
 * the schematic.
 */
public class ExamplePonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return ExampleMod.ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        // forComponents(...) lists the items whose Ponder entry should show this
        // scene; addStoryBoard(id, storyboard) attaches the animation. The id
        // ("example_kinetic_block") is also the schematic file name Ponder loads
        // from assets/examplemod/ponder/example_kinetic_block.nbt.
        helper.forComponents(AllBlocks.EXAMPLE_KINETIC_BLOCK.getId())
                .addStoryBoard("example_kinetic_block", ExamplePonderScenes::kineticBlock);
    }
}
