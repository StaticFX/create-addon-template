package com.example.examplemod.content.ponder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;

/**
 * Storyboards for {@link ExamplePonderPlugin}.
 * <p>
 * Each {@code public static void method(SceneBuilder, SceneBuildingUtil)} here is a
 * {@link net.createmod.ponder.api.scene.PonderStoryBoard} (referenced as a method
 * handle from the plugin). The two parameters are the whole API:
 * <ul>
 *   <li>{@code scene} — the timeline. You drive it imperatively: reveal blocks, run
 *       machines, move the camera, show text, then {@code scene.idle(ticks)} to let
 *       time pass between steps.</li>
 *   <li>{@code util} — factories for the arguments those calls need: block
 *       {@code Selection}s ({@code util.select()}) and positions/vectors
 *       ({@code util.grid()}, {@code util.vector()}).</li>
 * </ul>
 * A minimal scene usually looks like:
 * <pre>
 * scene.title("example_kinetic_block", "Consuming Stress with the Example Block");
 * scene.configureBasePlate(0, 0, 5);
 * scene.world().showSection(util.select().layer(0), Direction.UP);
 * scene.idle(10);
 * // ... reveal the block, start rotation, add overlay text ...
 * </pre>
 * Scene text is looked up from lang keys that Ponder generates for you — run
 * {@code ./gradlew runData} after writing a scene to emit the {@code ponder/*} lang
 * entries into {@code src/generated/resources}, then translate them.
 */
public class ExamplePonderScenes {

    /**
     * Explains {@link com.example.examplemod.AllBlocks#EXAMPLE_KINETIC_BLOCK} —
     * how it joins the kinetic network and draws Stress Units.
     * <p>
     * TODO: build the {@code example_kinetic_block.nbt} schematic and animate it
     * here. Left empty on purpose. As written this registers a valid but blank
     * scene; delete the plugin registration if you want it hidden until it's ready.
     */
    public static void kineticBlock(SceneBuilder scene, SceneBuildingUtil util) {
        // Convention Create follows in every scene — declare the base plate size
        // and title up front, then start telling the story below.
        scene.title("example_kinetic_block", "Consuming Stress with the Example Block");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);

        // TODO: reveal the block, connect it to a source of rotation, and use
        //       scene.overlay()/scene.world() calls to explain its stress impact.
    }
}
