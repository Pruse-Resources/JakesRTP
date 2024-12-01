package biz.donvi.jakesRTP.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class RandomTeleportEvent extends PlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Location landingLocation;
    private final int warmup;

    public RandomTeleportEvent(Player who, Location landingLocation, int warmup) {
        super(who);
        this.landingLocation = landingLocation;
        this.warmup = warmup;
    }

    public Location getLandingLocation() {
        return landingLocation;
    }

    public int getWarmup() {
        return warmup;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
