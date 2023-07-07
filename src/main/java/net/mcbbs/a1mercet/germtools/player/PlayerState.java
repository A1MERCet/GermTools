package net.mcbbs.a1mercet.germtools.player;

import com.germ.germplugin.api.RootType;
import net.mcbbs.a1mercet.germtools.config.PlayerStateLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerState
{
    private static final HashMap<String,PlayerState> players = new HashMap<>();
    public static PlayerState get(Player p){return players.get(p.getName());}
    public static PlayerState get(String p){return players.get(p);}
    public static boolean unload(String name, boolean unload) {
        if(!players.containsKey(name))return false;
        return unload(players.get(name),unload);
    }
    public static boolean unload(PlayerState state, boolean unload){
        if(state==null)return false;
        new PlayerStateLoader(state).saveAll();
        if(unload)players.remove(state.name);
        return true;
    }
    public static PlayerState load(String name , boolean load)
    {
        PlayerState state = new PlayerStateLoader(name).loadAll();
        if(load)players.put(name,state);
        return state;
    }

    protected HashMap<RootType, List<String>> favorites = new HashMap<>();
    protected HashMap<String,BlockPreset> blockPreset = new HashMap<>();

    public boolean isFavorites(String id){
        for (List<String> value : favorites.values())
            if(value.contains(id))return true;
        return false;
    }
    public PlayerState addFavorites(RootType type , String id){List<String> l = favorites.getOrDefault(type,new ArrayList<>());l.add(id);favorites.put(type,l);return this;}
    public PlayerState removeFavorites(String id){favorites.forEach((k,v)->v.removeIf(e->e.equals(id)));return this;}

    protected final String name;

    public PlayerState(String name)
    {
        this.name = name;
    }

    public Player getPlayer(){return Bukkit.getPlayer(name);}
    public boolean isOnline(){return Bukkit.getPlayer(name)!=null;}
    public HashMap<RootType, List<String>> getFavorites() {return favorites;}
    public String getName() {return name;}
    public void setFavorites(HashMap<RootType, List<String>> favorites) {this.favorites = favorites;}
    public HashMap<String, BlockPreset> getBlockPreset() {return blockPreset;}
    public void setBlockPreset(HashMap<String, BlockPreset> blockPreset) {this.blockPreset = blockPreset;}
}
