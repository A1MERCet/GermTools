package net.mcbbs.a1mercet.germtools.util;

import net.mcbbs.a1mercet.germtools.GermTools;
import org.bukkit.Bukkit;

public class UtilLogger
{
    public static void log(String str)
    {
        Bukkit.getLogger().info("[GermTools][INFO]:"+str);
    }
    public static void warn(String str)
    {
        Bukkit.getLogger().warning("[GermTools][WARN]:"+str);
    }
    public static void error(String str)
    {
        Bukkit.getLogger().severe("[GermTools][ERROR]:"+str);
    }
    public static void debug(String str)
    {
        if(GermTools.DEBUG)
            Bukkit.getLogger().warning("[GermTools][DEBUG]:"+str);
    }
}
