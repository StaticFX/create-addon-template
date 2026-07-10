package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.AllItems;
import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.HauntingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

/**
 * Fan processing recipe example: <b>Haunting</b>.
 * <p>
 * A haunting recipe runs when items pass through the air stream of an Encased Fan
 * that is blowing through Soul Fire. {@link HauntingRecipeGen#convert} is a shortcut
 * for a 1-in / 1-out conversion.
 */
public class ExampleHauntingRecipeGen extends HauntingRecipeGen {

    // Haunt the example item to obtain the example result.
    GeneratedRecipe EXAMPLE = convert(AllItems.EXAMPLE_ITEM.get(), AllItems.EXAMPLE_RESULT.get());

    public ExampleHauntingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
