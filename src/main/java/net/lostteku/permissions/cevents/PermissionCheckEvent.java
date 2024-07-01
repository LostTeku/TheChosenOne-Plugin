package net.lostteku.permissions.cevents;

import net.lostteku.permissions.PermissionPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PermissionCheckEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private PermissionPlayer player;
    private PermissionPlayer.PlayerRank rank;

    public PermissionCheckEvent(PermissionPlayer player) {
        this.player = player;
    }

    public PermissionPlayer getPlayer() {
        return player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

