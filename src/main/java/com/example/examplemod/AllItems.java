package com.example.examplemod;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

/**
 * Item registration using Create's Registrate.
 */
public class AllItems {

    public static final ItemEntry<Item> EXAMPLE_ITEM = ExampleMod.REGISTRATE
            .item("example_item", Item::new)
            .register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
