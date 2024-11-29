package biz.donvi.jakesRTP.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class RandomTeleportCancelEvent extends PlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public RandomTeleportCancelEvent(Player who) {
        super(who);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
