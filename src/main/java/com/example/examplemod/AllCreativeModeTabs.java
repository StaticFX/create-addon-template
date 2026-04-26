package com.example.examplemod;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AllCreativeModeTabs {

    public static final RegistryEntry<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            ExampleMod.REGISTRATE.defaultCreativeTab("main_tab", builder ->
                    builder
                            .title(Component.translatable("itemGroup." + ExampleMod.ID))
                            .icon(() -> new ItemStack(Items.HONEYCOMB))  // Replace with your own icon
                            .build()
            ).register();

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
