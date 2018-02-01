package com.github.rmsy.channels.vault;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class VaultSetup {
    private static Chat vaultChat;
    private static VaultListeners vaultListeners;

    public static void setupVault(Plugin plugin) {
        Chat newChat = plugin.getServer().getServicesManager().load(Chat.class);
        if(newChat != vaultChat)
            plugin.getLogger().info("Vault chat refreshed - " + (newChat == null ? "nope" : newChat.getName()));
        vaultChat = newChat;
        if(HandlerList.getRegisteredListeners(plugin).contains(vaultListeners)) {
            HandlerList.unregisterAll(vaultListeners);
        }
        VaultListeners vl = new VaultListeners(plugin);
        vaultListeners = vl;
        Bukkit.getPluginManager().registerEvents(vl, plugin);
        plugin.getLogger().info("Registered new listeners for services");
    }

    public static String getPrefix(Player p) {
        return Bukkit.getServer().getPluginManager().getPlugin("Vault") == null ?
                "" : translateColor(vaultChat.getPlayerPrefix(p));
    }

    private static String translateColor(String s) {
        return s == null ? null : ChatColor.translateAlternateColorCodes('&', s);
    }
}
