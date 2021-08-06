package com.ayou.absorptionapi.nms;

import com.ayou.absorptionapi.utils.Validate;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Handler {
    private final JavaPlugin plugin;

    protected Handler(JavaPlugin plugin) {
        this.plugin = Validate.notNull(plugin,"plugin must not be null!");
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
