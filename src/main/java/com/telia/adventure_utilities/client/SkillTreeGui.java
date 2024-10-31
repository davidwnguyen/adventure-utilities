package com.telia.adventure_utilities.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.resources.ResourceLocation;

public class SkillTreeGui implements Renderable, GuiEventListener {
    public static final ResourceLocation TEXTURES = ResourceLocation.fromNamespaceAndPath("examplemod", "textures/gui/container/example_container.png");

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {

    }

    @Override
    public void setFocused(boolean pFocused) {

    }

    @Override
    public boolean isFocused() {
        return false;
    }
}
