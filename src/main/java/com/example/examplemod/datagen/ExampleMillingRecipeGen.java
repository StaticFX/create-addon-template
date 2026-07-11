package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.MillingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Milling recipe generator.
 */
public class ExampleMillingRecipeGen extends MillingRecipeGen {

    GeneratedRecipe EXAMPLE = create("example_milling", b -> b
            .require(Items.COBBLESTONE)
            .output(Items.SAND)
            .duration(100));

    public ExampleMillingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
