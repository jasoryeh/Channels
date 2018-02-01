package com.github.rmsy.channels.listener;

import com.github.rmsy.channels.Channel;
import com.github.rmsy.channels.ChannelsPlugin;
import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

/** Listener for chat-related events. */
public class ChatListener implements Listener {

    /** The plugin. */
    private final ChannelsPlugin plugin;

    private ChatListener() {
        this.plugin = null;
    }

    /**
     * Creates a new ChatListener.
     *
     * @param plugin The plugin.
     */
    public ChatListener(final ChannelsPlugin plugin) {
        this.plugin = Preconditions.checkNotNull(plugin, "plugin");
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(final PlayerChatEvent event) {
        Player sender = event.getPlayer();
        Channel channel = this.plugin.getPlayerManager().getMembershipChannel(sender);
        if(channel != null) {
            event.setCancelled(true);
            channel.sendMessage(event.getMessage(), sender);
        }
    }
}
