package net.mcbbs.a1mercet.germtools.event;

import net.mcbbs.a1mercet.germtools.config.GermLoader;
import net.mcbbs.a1mercet.germtools.germ.GToolBox;
import net.mcbbs.a1mercet.germtools.player.PlayerState;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

public class EventHandler implements Listener
{
    @org.bukkit.event.EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        GToolBox.handlerList.remove(e.getPlayer());
        PlayerState.unload(e.getPlayer().getName(),true);
    }

    @org.bukkit.event.EventHandler
    public void onLogin(PlayerLoginEvent e)
    {
        PlayerState.load(e.getPlayer().getName(),true);
    }

    @org.bukkit.event.EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        if(e.getPlayer().isOp()&&e.getMessage().equals("/gp reload"))
        {
            GermLoader.reload();
        }
    }

    @org.bukkit.event.EventHandler
    public void onBroadcastMessage(BroadcastMessageEvent e)
    {
        Bukkit.getLogger().severe(e.getMessage());
        if(e.getMessage().equals("/gp reload"))
        {
            GermLoader.reload();
        }
    }

}
