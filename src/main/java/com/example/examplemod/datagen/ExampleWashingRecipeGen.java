package com.example.examplemod.datagen;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.AllItems;
import com.example.examplemod.ExampleMod;
import com.simibubi.create.api.data.recipe.BaseRecipeProvider.GeneratedRecipe;
import com.simibubi.create.api.data.recipe.WashingRecipeGen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * Fan processing recipe example: <b>Bulk Washing / Splashing</b>.
 * <p>
 * A splashing recipe runs when items are dropped through the air stream of an
 * Encased Fan that is blowing through water. Extend {@link WashingRecipeGen} and
 * declare recipes with {@code create(...)} / {@code require(...).output(...)}.
 */
public class ExampleWashingRecipeGen extends WashingRecipeGen {

    // Washing a block of dirt in a fan's water stream yields the example item.
    GeneratedRecipe EXAMPLE = create("example_washing",
            b -> b.require(Items.DIRT).output(AllItems.EXAMPLE_ITEM.get()));

    public ExampleWashingRecipeGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ExampleMod.ID);
    }
}
