package com.example.examplemod;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.content.ponder.ExamplePonderPlugin;
import com.example.examplemod.datagen.ExampleHauntingRecipeGen;
import com.example.examplemod.datagen.ExampleSequencedAssemblyGen;
import com.example.examplemod.datagen.ExampleWashingRecipeGen;
import com.simibubi.create.api.stress.BlockStressValues;
import net.createmod.ponder.foundation.PonderIndex;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleMod.ID)
public class ExampleMod {
    public static final String ID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID)
            .setTooltipModifierFactory(item ->
                    new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                            .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
            );

    public ExampleMod(IEventBus modBus) {
        REGISTRATE.registerEventListeners(modBus);

        AllCreativeModeTabs.register();
        REGISTRATE.setCreativeTab(AllCreativeModeTabs.MAIN_TAB);
        // Title for the creative tab (the tab's Component uses this key). Registered
        // through Registrate so it ends up in the generated lang file with everything else.
        REGISTRATE.addRawLang("itemGroup." + ID, "Example Create Addon");
        AllItems.register();
        AllBlocks.register();
        AllBlockEntityTypes.register();

        modBus.addListener(this::onCommonSetup);
        modBus.addListener(this::onClientSetup);
        modBus.addListener(this::onGatherData);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(ID, path);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Common setup...");
        event.enqueueWork(this::registerStressValues);
    }

    /**
     * Give kinetic blocks their Stress Unit impact. Registering here (rather than
     * hard-coding it in the block entity) makes the value configurable, and lets
     * Create surface it in the Engineer's Goggles tooltip and in Ponder.
     * <p>
     * The {@link java.util.function.DoubleSupplier} is the SU drawn per RPM.
     */
    private void registerStressValues() {
        BlockStressValues.IMPACTS.register(AllBlocks.EXAMPLE_KINETIC_BLOCK.get(), () -> 8.0);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("Client setup...");
        // Ponder is client-only, so its plugin is registered here rather than in
        // common setup. This mirrors how Create registers its own CreatePonderPlugin.
        event.enqueueWork(() -> PonderIndex.addPlugin(new ExamplePonderPlugin()));
    }

    /**
     * Register data generators. Create's recipe generators emit their JSON into
     * {@code src/generated/resources} when you run {@code ./gradlew runData}.
     * (Registrate registers its own block/item models, blockstates and lang.)
     */
    private void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ExampleSequencedAssemblyGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleWashingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleHauntingRecipeGen(output, registries));
    }
}
