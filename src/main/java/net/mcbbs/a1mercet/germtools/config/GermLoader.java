package net.mcbbs.a1mercet.germtools.config;

import com.germ.germplugin.api.GermSrcManager;
import com.germ.germplugin.api.RootType;
import net.mcbbs.a1mercet.germtools.GermTools;
import net.mcbbs.a1mercet.germtools.germ.ModelType;
import net.mcbbs.a1mercet.germtools.germ.block.ModuleBlock;
import net.mcbbs.a1mercet.germtools.util.UtilLogger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class GermLoader
{
    public static String path = GermTools.INSTANCE.getDataFolder().getParentFile().getAbsolutePath()+"/GermPlugin/";

    public static void reloadBlock()
    {
        UtilLogger.log("加载方块配置文件中");

        ModuleBlock.BLOCKS.clear();

//        File totalFile = new File(path+"/item/");
//        File[] fileAry = totalFile.listFiles();
//        if(fileAry != null)
//            for (File ofile : fileAry)
//            {
//                if(ofile.getName().equals("default.yml"))continue;
//
//                File file = new File(path+"/item/", ofile.getName());
//                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
//                if(cfg.getKeys(false)!=null)
//                    for(String id : cfg.getKeys(false))
//                        try {
//                            String path = id+".";
//                            UtilLogger.debug("  Loading:"+id);
//                            if(cfg.getString(path+"type")==null||!cfg.getString(path+"type").equals("block"))continue;
//
//                            String match        = cfg.getString(path+"match");
//                            ModelType modelType = ModelType.valueOf(cfg.getString(path+"renderItem.type"));
//                            String model        = cfg.getString(path+"renderItem.modelPath");
//
//                            ModuleBlock b = new ModuleBlock(id)
//                                    .setMatch(match)
//                                    .setModelType(modelType)
//                                    .setModel(model);
//
//                            ModuleBlock.BLOCKS.put(b.id,b);
//
//                            UtilLogger.debug("  已加载:["+b+"]");
//
//                        }catch (Exception e){e.printStackTrace();Bukkit.getLogger().severe("加载 "+RootType.ITEM.name()+" 的 "+id+" 出错");}
//            }
        for(GermSrcManager.SrcData d : GermSrcManager.getGermSrcManager().getSrcSets())
        {
            UtilLogger.debug("RootType:"+d.getRoot().name() + " FileName:"+d.getYamlName());
            YamlConfiguration cfg = d.getSrc();
            if(d.getRoot()==RootType.ITEM && cfg.getKeys(false)!=null)
            {
                UtilLogger.debug("  RootType:"+d.getRoot().name());
                for(String id : cfg.getKeys(false))
                {
                    try {
                        String path = id+".";
                        if(cfg.getString(path+"type")==null||!cfg.getString(path+"type").equals("block"))continue;

                        String match        = cfg.getString(path+"match");
                        ModelType modelType = ModelType.valueOf(cfg.getString(path+"renderItem.type"));
                        String model        = cfg.getString(path+"renderItem.modelPath");

                        ModuleBlock b = new ModuleBlock(id)
                                .setMatch(match)
                                .setModelType(modelType)
                                .setModel(model);

                        ModuleBlock.BLOCKS.put(b.id,b);

                        UtilLogger.debug("  已加载:["+b+"]");

                    }catch (Exception e){e.printStackTrace();Bukkit.getLogger().severe("加载 "+RootType.ITEM.name()+" 的 "+id+" 出错");}
                }
            }

        }

        UtilLogger.log("方块配置加载完成");
    }
    public static void reload()
    {
        reloadBlock();
    }
}
