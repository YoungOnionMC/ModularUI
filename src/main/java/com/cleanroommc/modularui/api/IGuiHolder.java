package com.cleanroommc.modularui.api;

import com.cleanroommc.modularui.factory.GuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;

import com.cleanroommc.modularui.value.sync.PanelSyncManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * An interface to implement on {@link net.minecraft.world.level.block.entity.BlockEntity} or {@link net.minecraft.world.item.Item}.
 */
@FunctionalInterface
public interface IGuiHolder<T extends GuiData> {

    /**
     * Only called on client side.
     *
     * @param data      information about the creation context
     * @param mainPanel the panel created in {@link #buildUI(GuiData, PanelSyncManager)}
     * @return a modular screen instance with the given panel
     */
    @OnlyIn(Dist.CLIENT)
    default ModularScreen createScreen(T data, ModularPanel mainPanel) {
        return new ModularScreen(mainPanel);
    }

    /**
     * Called on server and client. Create only the main panel here. Only here you can add sync handlers to widgets directly.
     * If the widget to be synced is not in this panel yet (f.e. in another panel) the sync handler must be registered here
     * with {@link PanelSyncManager}.
     *
     * @param data        information about the creation context
     * @param syncManager sync handler where widget sync handlers should be registered
     */
    ModularPanel buildUI(T data, PanelSyncManager syncManager);
}
