package com.telia.adventure_utilities.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.telia.adventure_utilities.AdventureUtilities;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@Mod(value = AdventureUtilities.MODID, dist = Dist.CLIENT)
public class ClientKeybinding {
    public static final Lazy<KeyMapping> SKILL_TREE_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.adventure_utilities.open_skill_tree",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_P,
                    "key.categories.adventure_utilities"
            ));

    public ClientKeybinding(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_TREE_MAPPING.get());
    }
    
    public static void onClientTick(ClientTickEvent.Post event) {
        while (SKILL_TREE_MAPPING.get().consumeClick()) {
            AdventureUtilities.LOGGER.info("yuh?");
        }
    }
}
