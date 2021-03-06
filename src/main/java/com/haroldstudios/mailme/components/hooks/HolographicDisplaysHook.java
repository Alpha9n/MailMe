package com.haroldstudios.mailme.components.hooks;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.haroldstudios.mailme.MailMe;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.UUID;

public class HolographicDisplaysHook implements HologramHook {
    private final MailMe plugin;
    
    public HolographicDisplaysHook(final MailMe plugin) {
        this.plugin = plugin;
    }
    
    public void addTempHologram(final Location loc, final String playerName) {
        Location location = loc.clone();
        location.add(0.5,-0.5 + 2,0.5);
        if (location.getWorld() == null) return;
        String line = this.plugin.getLocale().getMessage("hologram-format");
        line = line.replace("@name", playerName);
        final Hologram hologram = HologramsAPI.createHologram(this.plugin, location);
        hologram.appendTextLine(line);
    }
    
    public void removeTempHologram(final Location loc) {
        Location location = loc.clone();
        location.add(0.5,-0.5 + 2,0.5);
        final Collection<Hologram> holograms = HologramsAPI.getHolograms(this.plugin);
        for (final Hologram each : holograms) {
            if (!each.getWorld().equals(location.getWorld())) {
                continue;
            }
            if (each.getX() != location.getX()) {
                continue;
            }
            if (each.getY() != location.getY()) {
                continue;
            }
            if (each.getZ() != location.getZ()) {
                continue;
            }
            each.delete();
        }
    }
}
