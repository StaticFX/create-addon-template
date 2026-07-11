package com.example.examplemod.content.ponder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;

/**
 * Storyboards for ExamplePonderPlugin. Each static method with the signature
 * (SceneBuilder, SceneBuildingUtil) is one storyboard: scene is the timeline you drive
 * imperatively, and util builds the selections and positions those calls take. After
 * writing a scene, run gradlew runData to emit its lang keys into src/generated.
 */
public class ExamplePonderScenes {

    /**
     * Storyboard for the example kinetic block. Left as a stub: build the
     * example_kinetic_block.nbt schematic and animate it here. As written it registers a
     * valid but nearly empty scene.
     */
    public static void kineticBlock(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("example_kinetic_block", "Consuming Stress with the Example Block");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(10);

        // TODO: reveal the block, connect it to rotation, and add overlay text.
    }
}
