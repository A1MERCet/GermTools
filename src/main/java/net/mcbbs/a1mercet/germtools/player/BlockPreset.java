package net.mcbbs.a1mercet.germtools.player;

import net.mcbbs.a1mercet.germtools.util.UtilGerm;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BlockPreset
{
    protected List<String> content = new ArrayList<>();

    public void apply(Player operator)
    {
        for (String s : content)
            UtilGerm.giveBlock(operator,s,false);
    }

    public String get(int i){if(i<0||i>=content.size())return null;return content.get(i);}
    public BlockPreset add(String v){content.add(v);return this;}
    public List<String> getContent() {return content;}
    public void setContent(List<String> content) {this.content = content;}
}
