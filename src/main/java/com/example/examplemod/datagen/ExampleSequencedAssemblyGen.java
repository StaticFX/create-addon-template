package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.AllItems;
import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Sequenced assembly recipe generator. Shows the multi-step form: a transitional item
 * carried between steps, a loop count, and per-step sub-recipes (a deployer
 * application followed by a press).
 */
public class ExampleSequencedAssemblyGen extends SequencedAssemblyRecipeGen {

    GeneratedRecipe EXAMPLE = create("example_result", b -> b
            .require(Items.IRON_INGOT)
            .transitionTo(AllItems.INCOMPLETE_EXAMPLE.get())
            .addOutput(AllItems.EXAMPLE_RESULT.get(), 1f)
            .loops(2)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(Items.COPPER_INGOT))
            .addStep(PressingRecipe::new, rb -> rb));

    public ExampleSequencedAssemblyGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
