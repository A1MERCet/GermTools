package net.mcbbs.a1mercet.germtools.command;

import net.mcbbs.a1mercet.germtools.GermTools;
import net.mcbbs.a1mercet.germtools.config.GermLoader;
import net.mcbbs.a1mercet.germtools.germ.GToolBox;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("gt"))
        {
            if(sender instanceof Player){
                GToolBox.open((Player) sender);
                return true;
            } else if(args.length==1 && Bukkit.getPlayer(args[0])!=null){
                GToolBox.open(Bukkit.getPlayer(args[0]));
                return true;}

            if(args.length==1&&"reload".equals(args[0])) {
                GermLoader.reload();

                return true;
            }else if(args.length==1&&"debug".equals(args[0])) {

                if(GermTools.DEBUG){
                    sender.sendMessage("关闭DEBUG模式");
                    GermTools.DEBUG=false;
                }else{
                    sender.sendMessage("开启DEBUG模式");
                    GermTools.DEBUG=true;
                }

                return true;
            }

        }

        return true;
    }
}
