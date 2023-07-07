package net.mcbbs.a1mercet.germtools.config;

import net.mcbbs.a1mercet.germtools.GermTools;
import net.mcbbs.a1mercet.germtools.germ.GToolBox;
import net.mcbbs.a1mercet.germtools.player.PlayerState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PlayerStateLoader
{
    protected PlayerState state;
    File file;
    FileConfiguration cfg;

    public PlayerStateLoader(PlayerState state) {
        this.state = state;
        file = new File(GermTools.INSTANCE.getDataFolder()+"/player/", state.getName());
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public PlayerStateLoader(String name)
    {
        state=new PlayerState(name);
        file = new File(GermTools.INSTANCE.getDataFolder()+"/player/", state.getName());
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public PlayerState loadAll()
    {
        return state;
    }
    public PlayerState saveAll()
    {
        return state;
    }
}
