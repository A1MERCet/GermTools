package net.mcbbs.a1mercet.germtools.germ.block;

import com.germ.germplugin.api.GermBlockAPI;
import net.mcbbs.a1mercet.germtools.germ.ModelType;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ModuleBlock
{

    public static HashMap<String,ModuleBlock> BLOCKS = new HashMap<>();

    public final String id;
    public ModelType modelType;
    public String match;
    public String model;
    public String texture;
    public String glow;
    public String animation;

    public ModuleBlock(String id)
    {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID="+id+",Match="+match+",Model="+model;
    }

    public ItemStack create()
    {
        return GermBlockAPI.buildItemStackFromModelBlockIndexName(id);
    }
    public void place(Location loc)
    {
        GermBlockAPI.setModelBlockIndexName(loc,id);
    }

    public ModuleBlock setMatch(String match) {this.match = match;return this;}
    public ModuleBlock setModel(String model) {this.model = model;return this;}
    public ModuleBlock setTexture(String texture) {this.texture = texture;return this;}
    public ModuleBlock setGlow(String glow) {this.glow = glow;return this;}
    public ModuleBlock setAnimation(String animation) {this.animation = animation;return this;}
    public ModuleBlock setModelType(ModelType modelType) {this.modelType = modelType;return this;}
}
