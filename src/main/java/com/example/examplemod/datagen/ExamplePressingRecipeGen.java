package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.AllItems;
import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Pressing recipe generator.
 */
public class ExamplePressingRecipeGen extends PressingRecipeGen {

    GeneratedRecipe EXAMPLE = create("example_pressing", b -> b
            .require(Items.IRON_INGOT)
            .output(AllItems.EXAMPLE_ITEM.get()));

    public ExamplePressingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
