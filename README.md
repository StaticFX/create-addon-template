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

## Worked Examples

The template ships small, self-contained examples of the most common Create addon
building blocks. All of their recipes and assets are produced by data generation
(`./gradlew runData`) into `src/generated/resources` — nothing is hand-written.

- **SU-consuming kinetic block** — `content/kinetics/ExampleKineticBlock` +
  `ExampleKineticBlockEntity`. An encased-shaft-style block that joins the kinetic
  network and draws Stress Units. Its stress impact is registered in
  `ExampleMod#registerStressValues` via `BlockStressValues.IMPACTS`, and it reuses
  Create's `KineticBlockEntityRenderer` to spin.
- **Sequenced assembly recipe** — `datagen/ExampleSequencedAssemblyGen`. An item is
  processed through a Deployer then a Mechanical Press, looping twice, using a
  transitional "incomplete" item.
- **Fan processing recipes** — `datagen/ExampleWashingRecipeGen` (splashing / bulk
  washing) and `datagen/ExampleHauntingRecipeGen` (haunting). These run when items
  pass through an Encased Fan's air stream over water / soul fire.
- **Ponder plugin** — `content/ponder/ExamplePonderPlugin` +
  `ExamplePonderScenes`. Wires up Create's in-game animated manual for the addon and
  is registered client-side in `ExampleMod#onClientSetup`. The scene body is left as
  a stub — build the `example_kinetic_block.nbt` schematic and animate it to finish
  it.

Item models borrow existing vanilla textures so the template builds with **no `.png`
files of its own** — swap the textures in `AllItems`/`AllBlocks` for your own art.

## Getting Started

### 1. Use this template

Click **"Use this template"** on GitHub, or clone and rename.

### 2. Rename it to your mod

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

### 3. Build and run

```bash
./gradlew build          # Build the mod
./gradlew runClient      # Launch Minecraft with your mod
./gradlew runServer      # Launch a dedicated server
./gradlew runData        # Run data generators
```

## License

This template is provided under the [MIT License](LICENSE). Your mod built from this template can use any license you choose.
