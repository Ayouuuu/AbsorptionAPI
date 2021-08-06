package com.ayou.absorptionapi;

import com.ayou.absorptionapi.nms.NMSVersionHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbsorptionAPI extends JavaPlugin {
    private NMSVersionHandler nmsVersionHandler;

    @Override
    public void onEnable() {
        nmsVersionHandler = new NMSVersionHandler(this);
    }

    public NMSVersionHandler getNmsVersionHandler() {
        return nmsVersionHandler;
    }
}
