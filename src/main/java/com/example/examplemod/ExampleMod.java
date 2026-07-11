package com.example.examplemod;

import java.util.concurrent.CompletableFuture;

import com.example.examplemod.content.kinetics.ExampleKineticBlockEntity;
import com.example.examplemod.content.ponder.ExamplePonderPlugin;
import com.example.examplemod.datagen.ExampleCompactingRecipeGen;
import com.example.examplemod.datagen.ExampleCrushingRecipeGen;
import com.example.examplemod.datagen.ExampleCuttingRecipeGen;
import com.example.examplemod.datagen.ExampleDeployingRecipeGen;
import com.example.examplemod.datagen.ExampleEmptyingRecipeGen;
import com.example.examplemod.datagen.ExampleFillingRecipeGen;
import com.example.examplemod.datagen.ExampleLangMerger;
import com.example.examplemod.datagen.ExampleHauntingRecipeGen;
import com.example.examplemod.datagen.ExampleMillingRecipeGen;
import com.example.examplemod.datagen.ExampleMixingRecipeGen;
import com.example.examplemod.datagen.ExamplePressingRecipeGen;
import com.example.examplemod.datagen.ExampleSequencedAssemblyGen;
import com.example.examplemod.datagen.ExampleWashingRecipeGen;
import com.simibubi.create.api.stress.BlockStressValues;
import com.tterrag.registrate.providers.ProviderType;
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
        registerLangPartials();
        registerPonderLang();
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
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("Client setup...");
        // Ponder is client-only, so its plugin is registered here rather than in
        // common setup.
        event.enqueueWork(() -> PonderIndex.addPlugin(new ExamplePonderPlugin()));
    }

    /**
     * Feeds the hand-authored language partials (assets/examplemod/lang/default/*.json)
     * into Registrate's lang provider so runData merges them with the generated block and
     * item names into a single en_us.json. Keeps English copy out of Java, mirroring how
     * Create authors its own translations.
     */
    private void registerLangPartials() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, provider ->
                ExampleLangMerger.mergeInto(provider::add));
    }

    /**
     * Feeds the Ponder scenes' text (titles and captions) into Registrate's lang provider
     * so runData writes it into the same en_us.json as the block and item names. The
     * registered callback only runs during data generation, so it is safe to touch the
     * client-only PonderIndex from here.
     */
    private void registerPonderLang() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            PonderIndex.addPlugin(new ExamplePonderPlugin());
            PonderIndex.getLangAccess().provideLang(ID, provider::add);
        });
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
