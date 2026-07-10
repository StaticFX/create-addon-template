package com.example.examplemod;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * Item registration using Create's Registrate.
 * <p>
 * Each item overrides its model to borrow an existing vanilla texture, so the
 * template generates working assets with no {@code .png} files of its own. Replace
 * the texture in {@code .model(...)} (or delete the override and add
 * {@code assets/examplemod/textures/item/<name>.png}) with your own art.
 */
public class AllItems {

    public static final ItemEntry<Item> EXAMPLE_ITEM = ExampleMod.REGISTRATE
            .item("example_item", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/amethyst_shard")))
            .register();

    /**
     * Final output of the sequenced assembly example recipe
     * ({@code datagen/ExampleSequencedAssemblyGen}) and the haunting recipe.
     */
    public static final ItemEntry<Item> EXAMPLE_RESULT = ExampleMod.REGISTRATE
            .item("example_result", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/netherite_ingot")))
            .register();

    /**
     * The "in-progress" item shown on the belt/depot while the sequenced assembly
     * recipe is running. Sequenced assembly requires a dedicated transitional item
     * (see {@code SequencedAssemblyRecipeBuilder#transitionTo}).
     */
    public static final ItemEntry<Item> INCOMPLETE_EXAMPLE = ExampleMod.REGISTRATE
            .item("incomplete_example", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/brick")))
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
