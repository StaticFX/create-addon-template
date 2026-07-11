package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.AllItems;
import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.HauntingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

/**
 * Haunting recipe generator. The convert helper is shorthand for a single-input,
 * single-output recipe.
 */
public class ExampleHauntingRecipeGen extends HauntingRecipeGen {

    GeneratedRecipe EXAMPLE = convert(AllItems.EXAMPLE_ITEM.get(), AllItems.EXAMPLE_RESULT.get());

    public ExampleHauntingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
