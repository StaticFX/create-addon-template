package com.example.examplemod;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.content.ponder.ExamplePonderPlugin;
import com.example.examplemod.datagen.ExampleCompactingRecipeGen;
import com.example.examplemod.datagen.ExampleCrushingRecipeGen;
import com.example.examplemod.datagen.ExampleCuttingRecipeGen;
import com.example.examplemod.datagen.ExampleDeployingRecipeGen;
import com.example.examplemod.datagen.ExampleEmptyingRecipeGen;
import com.example.examplemod.datagen.ExampleFillingRecipeGen;
import com.example.examplemod.datagen.ExampleHauntingRecipeGen;
import com.example.examplemod.datagen.ExampleMillingRecipeGen;
import com.example.examplemod.datagen.ExampleMixingRecipeGen;
import com.example.examplemod.datagen.ExamplePressingRecipeGen;
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
        // Goggle overlay text for the generator (see ExampleGeneratorBlockEntity).
        REGISTRATE.addRawLang("gui.examplemod.example_generator.generating", "Generating rotation");
        // Name shown for the Display Link source (see AllDisplaySources / ExampleDisplaySource).
        REGISTRATE.addRawLang("display_source.examplemod.example_source", "Kinetic Speed");
        AllItems.register();
        AllDisplaySources.register();
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
     * Registers stress values: an impact for the consumer and a capacity for the
     * generator. Keeping them here rather than in the block entities makes the values
     * configurable and lets them surface in tooltips.
     */
    private void registerStressValues() {
        // Consumers register an IMPACT (SU drawn per RPM)...
        BlockStressValues.IMPACTS.register(AllBlocks.EXAMPLE_KINETIC_BLOCK.get(), () -> 8.0);
        // ...generators register a CAPACITY (SU added to the network per RPM).
        BlockStressValues.CAPACITIES.register(AllBlocks.EXAMPLE_GENERATOR_BLOCK.get(), () -> 256.0);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("Client setup...");
        // Ponder is client-only, so its plugin is registered here rather than in
        // common setup.
        event.enqueueWork(() -> PonderIndex.addPlugin(new ExamplePonderPlugin()));
    }

    /**
     * Registers the data generators. Running gradlew runData writes their output into
     * src/generated/resources.
     */
    private void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ExampleSequencedAssemblyGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleWashingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleHauntingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleCrushingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleMillingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExamplePressingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleCuttingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleMixingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleCompactingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleFillingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleEmptyingRecipeGen(output, registries));
        generator.addProvider(event.includeServer(), new ExampleDeployingRecipeGen(output, registries));
    }
}
