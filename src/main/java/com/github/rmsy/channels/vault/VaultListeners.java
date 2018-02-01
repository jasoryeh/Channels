package com.github.rmsy.channels.vault;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.plugin.Plugin;

public class VaultListeners implements Listener {

    private Plugin plugin;

    public VaultListeners(Plugin p) { plugin = p; }

    @EventHandler
    public void onServiceChange(ServiceRegisterEvent sre) {
        if (sre.getProvider().getService() == Chat.class) VaultSetup.setupVault(plugin);
    }

    @EventHandler
    public void onServiceChange(ServiceUnregisterEvent sue) {
        if (sue.getProvider().getService() == Chat.class) VaultSetup.setupVault(plugin);
    }

}
