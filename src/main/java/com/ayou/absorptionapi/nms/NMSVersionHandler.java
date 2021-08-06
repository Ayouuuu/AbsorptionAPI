package com.ayou.absorptionapi.nms;

import com.ayou.absorptionapi.nms.player.PlayerHandler;
import com.ayou.absorptionapi.utils.Validate;
import com.ayou.absorptionapi.utils.VersionUtility;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NMSVersionHandler {
    private final JavaPlugin plugin;
    private PlayerHandler playerHandler;

    public NMSVersionHandler(JavaPlugin javaPlugin) {
        this.plugin = Validate.notNull(javaPlugin,"plugin must not be null");
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    private Class<?> findHandlerClass(final String classType,String packageName) throws ClassNotFoundException {
        final String nmsVersion = VersionUtility.getNetMinecraftServerVersion();
        final String className = "com.ayou.absorptionapi.nms."+packageName+"." + classType + "_" + nmsVersion;
        return Class.forName(className);
    }

    private <O> O getHandler(String packageName,final Class<O> typeClass, final String classType) {
        final JavaPlugin plugin = this.getPlugin();
        final String nmsVersion = VersionUtility.getNetMinecraftServerVersion();
        try {
            final Class<?> handlerClass = this.findHandlerClass(classType,packageName);
            final Class<? extends O> aClass = handlerClass.asSubclass(typeClass);
            final Constructor<? extends O> constructor = aClass.getDeclaredConstructor(JavaPlugin.class);
            return (O)constructor.newInstance(plugin);
        }
        catch (ReflectiveOperationException ex) {
            final Logger logger = plugin.getLogger();
            logger.warning("Could not find '" + classType + "' for version '" + nmsVersion + "'. Searching for fallback handler...");
            final String className = "com.github.sirblobman.api.nms." + classType + "_Fallback";
            try {
                final Class<?> fallbackClass = Class.forName(className);
                final Class<? extends O> aClass2 = fallbackClass.asSubclass(typeClass);
                final Constructor<? extends O> constructor2 = aClass2.getDeclaredConstructor(JavaPlugin.class);
                return (O)constructor2.newInstance(plugin);
            }
            catch (ReflectiveOperationException ex2) {
                logger.log(Level.WARNING, "Original Error that caused fallback search:", ex);
                throw new IllegalStateException("Missing fallback class '" + className + "'.", ex2);
            }
        }
    }

    public PlayerHandler getPlayerHandler() {
        if (this.playerHandler != null) {
            return this.playerHandler;
        }
        this.playerHandler = this.getHandler("player",PlayerHandler.class, "PlayerHandler");
        return this.getPlayerHandler();
    }
}
