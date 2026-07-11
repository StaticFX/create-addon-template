# Create Mod Addon Template

A ready-to-use template for building [Create](https://modrinth.com/mod/create) mod addons with **Java** and **NeoForge 1.21.1**.

## What's Included

- **NeoForge 1.21.1** with Create 6.0.10 dependency
- **Create Registrate** — Create's registration system, pre-configured
- **Ponder & Flywheel** — Create's rendering and documentation libraries
- **JEI** — recipe viewer integration (optional, compile-only)
- **Mixin support** — pre-configured mixins
- **GitHub Actions** — automatic builds on push/PR
- **Gradle 8.10** with configuration cache enabled

## Examples

The template ships small examples of the most common Create addon
additions. All of their recipes and assets are produced by data generation
(`./gradlew runData`) into `src/generated/resources` — nothing is hand-written.

- **SU-consuming kinetic block + goggle overlay** — `content/kinetics/ExampleKineticBlock` +
  `ExampleKineticBlockEntity`. An encased-shaft-style block that joins the kinetic
  network and draws Stress Units. Its stress impact is registered on the block in
  `AllBlocks` via `BlockStressValues.IMPACTS` (from the shared `STRESS_IMPACT` constant).
  Its model is an inset casing and `ExampleShaftRenderer` spins a Create shaft that pokes
  out of it, so the rotation is actually visible. It overrides `addToGoggleTooltip` to add
  a live "Drawing stress" readout beneath the default kinetic stats.
- **Kinetic generator + goggle overlay** — `content/kinetics/ExampleGeneratorBlock` +
  `ExampleGeneratorBlockEntity`. The counterpart source: it *adds* stress capacity
  (`BlockStressValues.CAPACITIES`, registered in `AllBlocks`) and produces rotation via
  `getGeneratedSpeed()`. Crucially it overrides `initialize()` to call
  `updateGeneratedRotation()` — without that a generator never pushes its speed into the
  network and nothing turns. It shares `ExampleShaftRenderer` and adds custom lines to the
  Engineer's Goggles tooltip (`IHaveGoggleInformation#addToGoggleTooltip`).
- **Display Link source** — `content/display/ExampleDisplaySource` + `AllDisplaySources`.
  A `DisplaySource` that reports a block's kinetic speed onto a Display Board; attached
  to the generator in `AllBlocks` via `.transform(DisplaySource.displaySource(...))`.
- **Processing recipes** — `datagen/Example*RecipeGen` for crushing, milling, pressing,
  cutting, mixing (heated), compacting, filling & emptying (fluids), and deploying,
  plus **splashing** and **haunting** fan processing.
- **Sequenced assembly recipe** — `datagen/ExampleSequencedAssemblyGen`. An item is
  processed through a Deployer then a Mechanical Press, looping twice, using a
  transitional "incomplete" item.
- **Ponder plugin** — `content/ponder/ExamplePonderPlugin` +
  `ExamplePonderScenes`. Wires up Create's in-game animated manual for the addon and
  is registered client-side in `ExampleMod#onClientSetup`. Ships a worked scene driven
  by the `assets/examplemod/ponder/example_ponder.nbt` schematic (two Mechanical Arms and
  a sign), demonstrating base-plate reveal, camera panning, and outlined captions. Its
  scene text is generated into `en_us.json` through Registrate's lang provider
  (`ExampleMod#registerPonderLang`), so it appears after `./gradlew runData`.

- **Create-style item tooltip** — the `example_item`'s tooltip is authored as lang keys in
  `assets/examplemod/lang/default/tooltips.json` (`.tooltip.summary` +
  `.tooltip.condition1`/`.behaviour1`). The `ItemDescription` modifier set up on the
  Registrate in `ExampleMod` reads them and adds the "Hold Shift" prompt and highlight
  styling automatically — no custom `Item` class needed. Wrap words in `_underscores_` to
  highlight them.
- **Lang: keys in code, copy in JSON** — like Create, English strings live in hand-authored
  partials under `assets/examplemod/lang/default/` (`interface.json`, `tooltips.json`), while
  `Lang` holds only translation keys. `datagen/ExampleLangMerger` merges the partials into the
  `en_us.json` that Registrate generates for block/item names, so `./gradlew runData` writes a
  single lang file. Add copy by editing a partial (or dropping a new one and listing it in
  `ExampleLangMerger`) — never by putting English in Java.
- **Additional languages** — `assets/examplemod/lang/de_de.json` is a worked German
  translation. Registrate only *generates* `en_us`, so every other language is a plain static
  file (exactly how Create ships its Crowdin translations): copy the keys from the generated
  `en_us.json`, translate the values, and Minecraft overlays them when that language is
  selected, falling back to `en_us` for any key you leave out. It needs no datagen — the file
  is loaded as-is. (`_underscores_` and `%s` placeholders must be kept in the translation.)

Item models borrow existing vanilla textures so the template builds with **no `.png`
files of its own** — swap the textures in `AllItems`/`AllBlocks` for your own art.

## Getting Started

### Quickest: scaffold with create-addon-cli

Use the [create-addon-cli](https://github.com/StaticFX/create-addon-cli) tool for quick scaffolding

```bash
npm create addon-cli@latest
# or
npx create-addon-cli my-mod --name "Sick Mod"
```

It clones this template and rebrands every `Example` / `examplemod` reference to your
mod. The scaffolder lives in its own repo,
[`create-addon-cli`](https://github.com/StaticFX/create-addon-cli). Skip straight to
[Build and run](#build-and-run).

### Or: use the GitHub template

#### 1. Use this template

Click **"Use this template"** on GitHub, or clone and rename.

#### 2. Rename it to your mod

Run the bootstrap task once — it rewrites every `Example` / `examplemod` reference,
moves the Java package, renames the `Example*` classes and the mixin config, and
clears `src/generated`:

```bash
./gradlew renameMod --name "Sick Mod"
# optional overrides:
./gradlew renameMod --name "Sick Mod" --id sickmod --group com.acme.sickmod
```

`--id` defaults to the name lowercased (`sickmod`); `--group` defaults to
`com.example.<id>`. Commit or stash first — it edits files in place; review the
result with `git diff`. Once you're happy, delete `gradle/rename-mod.gradle` and its
`apply from:` line in `build.gradle`, then regenerate assets with `./gradlew runData`.

Finish up in `gradle.properties` (author, description, version, license):

```properties
mod_version=0.1.0
mod_authors=YourName
mod_description=Your mod description.
mod_license=MIT
```

<details>
<summary>Prefer to rename by hand?</summary>

1. Edit `mod_id` / `mod_name` / `mod_group_id` in `gradle.properties`
2. Rename `src/main/java/com/example/examplemod/` to match your `mod_group_id`
3. Update `ExampleMod.java` — change `ID` to your `mod_id`
4. Rename `src/main/resources/examplemod.mixins.json` to `{mod_id}.mixins.json` and
   update the `package` path inside it
</details>

### Build and run

```bash
./gradlew build          # Build the mod
./gradlew runClient      # Launch Minecraft with your mod
./gradlew runServer      # Launch a dedicated server
./gradlew runData        # Run data generators
```

## Where to start?

1. Go into your ExampleMod.java class to get a quick overview over what is defined and where exactly.

## License

This template is provided under the [MIT License](LICENSE). Your mod built from this template can use any license you choose.
