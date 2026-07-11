package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.CuttingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Cutting recipe generator.
 */
public class ExampleCuttingRecipeGen extends CuttingRecipeGen {

    GeneratedRecipe EXAMPLE = create("example_cutting", b -> b
            .require(Items.OAK_LOG)
            .output(Items.OAK_PLANKS, 6)
            .duration(50));

    public ExampleCuttingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
