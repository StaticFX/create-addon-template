package com.example.examplemod;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * Item registration. Each item overrides its model to borrow a vanilla texture, so the
 * template builds with no texture files of its own. Point the model at your own texture,
 * or remove the override and add assets/examplemod/textures/item/name.png.
 */
public class AllItems {

    public static final ItemEntry<Item> EXAMPLE_ITEM = ExampleMod.REGISTRATE
            .item("example_item", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/amethyst_shard")))
            .register();

    /**
     * Output of the sequenced assembly and several other example recipes.
     */
    public static final ItemEntry<Item> EXAMPLE_RESULT = ExampleMod.REGISTRATE
            .item("example_result", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/netherite_ingot")))
            .register();

    /**
     * Transitional item carried between the steps of the sequenced assembly recipe.
     */
    public static final ItemEntry<Item> INCOMPLETE_EXAMPLE = ExampleMod.REGISTRATE
            .item("incomplete_example", Item::new)
            .model((c, p) -> p.generated(c::getEntry, ResourceLocation.withDefaultNamespace("item/brick")))
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
