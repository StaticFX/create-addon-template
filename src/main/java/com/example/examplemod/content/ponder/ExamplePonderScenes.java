package com.example.examplemod.content.ponder;

import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;

public class ExamplePonderScenes {

    /**
     * Storyboard for the example_ponder.nbt schematic, a flat 5x5 layout. It demonstrates the core
     * building blocks of a Ponder scene: revealing the structure, panning the camera, and
     * drawing outlines with attached text.
     */
    public static void examplePonder(SceneBuilder scene, SceneBuildingUtil util) {
        // The first argument is the scene id: it prefixes this scene's lang keys
        // (ponder.examplemod.example_ponder.*), so keep it matching the storyboard id.
        scene.title("example_ponder", "Welcome to the Example Addon");

        // Declare the 5x5 base plate up front so Ponder scales the scene correctly, then
        // fade it in. Everything in this schematic lives on that layer.
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);

        // Selections describe which block positions an instruction targets. Positions are
        // schematic-local, matching the coordinates baked into example_ponder.nbt.
        Selection sign = util.select().position(2, 0, 2);
        Selection arms = util.select().position(2, 0, 1)
                .add(util.select().position(2, 0, 3));

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
                .pointAt(util.vector().topOf(2, 0, 2));
        scene.idle(90);

        // Pan the camera to show the scene is fully 3D before pointing out the arms.
        scene.rotateCameraY(60);
        scene.idle(20);

        scene.overlay().showOutlineWithText(arms, 80)
                .colored(PonderPalette.BLUE)
                .text("Two Mechanical Arms flank the sign, proving Create's own blocks work too")
                .pointAt(util.vector().topOf(2, 0, 1));
        scene.idle(90);

        // markAsFinished lets the "next scene" prompt appear without waiting out the last
        // text window; reaching the end of the method would mark it finished anyway.
        scene.markAsFinished();
    }
}
