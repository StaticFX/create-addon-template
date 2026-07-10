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
 * Sequenced assembly recipe example.
 * <p>
 * Sequenced assembly is Create's multi-step crafting: an item rides a belt/depot
 * and passes several machines in order (here: a Deployer, then a Mechanical Press),
 * repeating for a number of loops before yielding the result.
 * <p>
 * Recipes are declared as fields; the {@code create(...)} helper collects them and
 * {@link com.simibubi.create.api.data.recipe.BaseRecipeProvider#buildRecipes} emits
 * them to {@code src/generated/resources} when {@code runData} runs.
 */
public class ExampleSequencedAssemblyGen extends SequencedAssemblyRecipeGen {

    // Iron ingot -> deploy a copper ingot onto it, then press it; repeat twice -> example result.
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
