package com.ayou.absorptionapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionUtility {
    private static final Pattern VERSION_PATTERN;
    static {
        VERSION_PATTERN = Pattern.compile("(\\(MC: )([\\d.]+)(\\))");
    }

    public static int getMinorVersion() {
        final String majorMinorVersion = getMajorMinorVersion();
        final int periodIndex = majorMinorVersion.indexOf(46);
        final int nextIndex = periodIndex + 1;
        final String minorString = majorMinorVersion.substring(nextIndex);
        return Integer.parseInt(minorString);
    }

    public static int getMajorVersion() {
        final String majorMinorVersion = getMajorMinorVersion();
        final int periodIndex = majorMinorVersion.indexOf(46);
        final String majorString = majorMinorVersion.substring(0, periodIndex);
        return Integer.parseInt(majorString);
    }

    public static String getMajorMinorVersion() {
        final String minecraftVersion = getMinecraftVersion();
        final int lastPeriodIndex = minecraftVersion.lastIndexOf(46);
        return (lastPeriodIndex < 2) ? minecraftVersion : minecraftVersion.substring(0, lastPeriodIndex);
    }


    public static String getMinecraftVersion() {
        final String bukkitVersion = Bukkit.getVersion();
        final Matcher matcher = VersionUtility.VERSION_PATTERN.matcher(bukkitVersion);
        return matcher.find() ? matcher.group(2) : "";
    }

    public static String getNetMinecraftServerVersion() {
        final Server server = Bukkit.getServer();
        final Class<? extends Server> serverClass = server.getClass();
        final Package serverPackage = serverClass.getPackage();
        final String serverPackageName = serverPackage.getName();
        final int lastPeriodIndex = serverPackageName.lastIndexOf(46);
        final int nextIndex = lastPeriodIndex + 2;
        return serverPackageName.substring(nextIndex);
    }
}
