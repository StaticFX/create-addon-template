package com.example.examplemod;

/**
 * Block registration using Create's Registrate.
 * <p>
 * Example:
 * <pre>
 * public static final BlockEntry&lt;Block&gt; EXAMPLE_BLOCK = ExampleMod.REGISTRATE
 *         .block("example_block", Block::new)
 *         .simpleItem()
 *         .register();
 * </pre>
 */
public class AllBlocks {

    public static void register() {
        // Force class loading to trigger Registrate calls
    }
}
