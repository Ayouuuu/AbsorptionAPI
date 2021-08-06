package com.ayou.absorptionapi.nms.player;

import com.ayou.absorptionapi.nms.Handler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlayerHandler extends Handler {
    protected PlayerHandler(JavaPlugin plugin) {
        super(plugin);
    }

    public abstract void forceRespawn(final Player p0);

    public abstract double getAbsorptionHearts(final Player p0);

    public abstract void setAbsorptionHearts(final Player p0, final double p1);
}
