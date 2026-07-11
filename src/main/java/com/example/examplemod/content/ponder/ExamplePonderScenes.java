package com.example.examplemod.content.ponder;

import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.Direction;

public class ExamplePonderScenes {

    /**
     * Storyboard for the example_ponder.nbt schematic: a 5x5 andesite base plate (layer 0)
     * with two Mechanical Arms and a sign standing on top of it (layer 1). It demonstrates
     * the core building blocks of a Ponder scene: fading in the base plate, revealing the
     * structure above it, panning the camera, and drawing outlines with attached text.
     */
    public static void examplePonder(SceneBuilder scene, SceneBuildingUtil util) {
        // The first argument is the scene id: it prefixes this scene's lang keys
        // (ponder.examplemod.example_ponder.*), so keep it matching the storyboard id.
        scene.title("example_ponder", "Welcome to the Example Addon");

        // showBasePlate fades in the schematic's bottom layer (the andesite floor). The
        // schematic must actually contain those blocks — Ponder does not invent a platform.
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);

        // Reveal everything standing on the base plate (layer 1 and up): the arms and sign.
        scene.world().showSection(util.select().layersFrom(1), Direction.DOWN);
        scene.idle(10);

        // Selections describe which block positions an instruction targets. Positions are
        // schematic-local, matching the coordinates baked into example_ponder.nbt.
        Selection sign = util.select().position(2, 1, 2);
        Selection arms = util.select().position(2, 1, 1)
                .add(util.select().position(2, 1, 3));

        // An independent text window is anchored to the overlay rather than a block, which
        // is handy for a scene's opening caption.
        scene.overlay().showText(70)
                .text("Every block here is loaded from example_ponder.nbt")
                .independent(10);
        scene.idle(80);

        // showOutlineWithText highlights a selection and tethers a caption to it. pointAt
        // aims the caption's leader line at a point in scene space.
        scene.overlay().showOutlineWithText(sign, 80)
                .colored(PonderPalette.GREEN)
                .text("Block entities like this sign render and tick just as they do in-world")
                .pointAt(util.vector().topOf(2, 1, 2));
        scene.idle(90);

        // Pan the camera to show the scene is fully 3D before pointing out the arms.
        scene.rotateCameraY(60);
        scene.idle(20);

        scene.overlay().showOutlineWithText(arms, 80)
                .colored(PonderPalette.BLUE)
                .text("Two Mechanical Arms flank the sign, proving Create's own blocks work too")
                .pointAt(util.vector().topOf(2, 1, 1));
        scene.idle(90);

        // markAsFinished lets the "next scene" prompt appear without waiting out the last
        // text window; reaching the end of the method would mark it finished anyway.
        scene.markAsFinished();
    }
}
