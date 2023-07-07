package net.mcbbs.a1mercet.germtools;

import net.mcbbs.a1mercet.germtools.command.CommandHandler;
import net.mcbbs.a1mercet.germtools.config.GermLoader;
import net.mcbbs.a1mercet.germtools.event.EventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GermTools extends JavaPlugin {

    public static GermTools INSTANCE;
    public static boolean DEBUG = true;

    @Override
    public void onEnable()
    {
        INSTANCE=this;
        getServer().getPluginManager().registerEvents(new EventHandler(),this);
        Bukkit.getPluginCommand("gt").setExecutor(new CommandHandler());
        GermLoader.reload();
    }

    @Override
    public void onDisable()
    {

    }
}
