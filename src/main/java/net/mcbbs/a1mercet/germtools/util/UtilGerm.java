package net.mcbbs.a1mercet.germtools.util;

import com.germ.germplugin.api.GermBlockAPI;
import net.mcbbs.a1mercet.germtools.germ.block.ModuleBlock;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UtilGerm
{
    public static boolean giveBlock(Player operator , String id , boolean force)
    {
        if(!force && (operator==null || !operator.isOp()))return false;

        ItemStack isk = GermBlockAPI.buildItemStackFromModelBlockIndexName(id);
        operator.getInventory().addItem(isk);

        return true;
    }

    public static boolean place(Player operator , ModuleBlock block , Location location , boolean force)
    {
        if(!force && (operator==null || !operator.isOp()))return false;

        block.place(location);

        return true;
    }
}
