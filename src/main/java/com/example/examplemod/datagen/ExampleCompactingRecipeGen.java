package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Compacting recipe generator. Shows several inputs producing a single output.
 */
public class ExampleCompactingRecipeGen extends CompactingRecipeGen {

    GeneratedRecipe EXAMPLE = create("example_compacting", b -> b
            .require(Items.CLAY_BALL)
            .require(Items.CLAY_BALL)
            .require(Items.CLAY_BALL)
            .require(Items.CLAY_BALL)
            .output(Items.CLAY));

    public ExampleCompactingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
