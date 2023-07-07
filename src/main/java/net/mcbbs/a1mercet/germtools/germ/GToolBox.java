package net.mcbbs.a1mercet.germtools.germ;

import com.germ.germplugin.api.RootType;
import com.germ.germplugin.api.dynamic.gui.*;
import net.mcbbs.a1mercet.germtools.config.GermLoader;
import net.mcbbs.a1mercet.germtools.germ.block.ModuleBlock;
import net.mcbbs.a1mercet.germtools.player.PlayerState;
import net.mcbbs.a1mercet.germtools.util.UtilGerm;
import net.mcbbs.a1mercet.germtools.util.UtilGui2K;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GToolBox extends GermGuiScreen
{
    public static HashMap<Player,GToolBox> handlerList = new HashMap<>();
    protected final Player iplayer;
    protected PlayerState getPlayerState(){return PlayerState.get(iplayer);}

    protected class GAction extends GermGuiCanvas
    {
        protected GChild parent;

        protected GermGuiButton button = UtilGui2K.createButton("main","aestus/toolbox/gray.png",4,0)
                .registerCallbackHandler(this::callbackButton, GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiLabel label = UtilGui2K.createLabel("label","",68,4,2.5F, GermGuiLabel.Align.CENTER);

        public GAction(String indexName)
        {
            super(indexName);
            init();
        }

        protected void init()
        {
            addGuiPart(button);
            addGuiPart(label.setText(getIndexName()));
        }

        protected void callbackButton(Player p,GermGuiButton b)
        {
            if(parent!=null) parent.close();
        }

        public int getLayoutHeight()
        {
            return 30;
        }
    }
    protected class GChild extends GermGuiCanvas
    {
        protected List<GAction> actions = new ArrayList<>();
        protected GermGuiScreen parent;
        public GChild(GermGuiScreen parent)
        {
            super("child");
            this.parent=parent;
            init();
        }

        protected int getActionsHeight()
        {
            int v = 0;
            for (GAction action : actions)
                v+=action.getLayoutHeight();
            return v;
        }

        protected void update()
        {
            int height = getActionsHeight();
            c1.setHeight((height+25)+"/1440*h");
            c2.setHeight((height+23)+"/1440*h");
            c3.setHeight((height+21)+"/1440*h");
            c4.setHeight(height+"/1440*h");
            updateLayout();
        }

        protected GermGuiColor c1 = new GermGuiColor("black1")
                .setColor(0xff000000).setEndColor(0xff000000)
                .setWidth("128/2560*w")
                .setLocationX("0/2560*w").setLocationY("0/1440*h");
        protected GermGuiColor c2 = new GermGuiColor("white2")
                .setColor(0xffffffff).setEndColor(0xffffffff)
                .setWidth("126/2560*w")
                .setLocationX("1/2560*w").setLocationY("1/1440*h");
        protected GermGuiColor c3 = new GermGuiColor("black2")
                .setColor(0xff000000).setEndColor(0xff000000)
                .setWidth("124/2560*w")
                .setLocationX("2/2560*w").setLocationY("2/1440*h");
        protected GermGuiColor c4 = new GermGuiColor("mainunder")
                .setColor(0xff303030).setEndColor(0xff303030)
                .setWidth("122/2560*w")
                .setLocationX("3/2560*w").setLocationY("24/1440*h");

        protected void init()
        {
            setLocationX("x").setLocationY("y");

            {
                addGuiPart(new GermGuiButton("under")
                        .setDefaultPath("aestus/toolbox/air.png").setHoverPath("aestus/toolbox/air.png")
                        .setWidth("100000").setHeight("100000")
                        .setLocationX("-5000").setLocationY("-5000")
                        .registerCallbackHandler((p,b)->close(), GermGuiButton.EventType.LEFT_CLICK)
                        .registerCallbackHandler((p,b)->close(), GermGuiButton.EventType.RIGHT_CLICK)
                );
                addGuiPart(c1);
                addGuiPart(c2);
                addGuiPart(c3);
                addGuiPart(c4);
                addGuiPart(new GermGuiColor("title")
                        .setColor(0xff0e0e0e).setEndColor(0xff0e0e0e)
                        .setWidth("122/2560*w").setHeight("21/1440*h")
                        .setLocationX("3/2560*w").setLocationY("3/1440*h")
                );
                addGuiPart(new GermGuiTexture("icon")
                        .setPath("aestus/toolbox/tool.png")
                        .setWidth("20/2560*w").setHeight("17/1440*h")
                        .setLocationX("4/2560*w").setLocationY("4/1440*h")
                );
                addGuiPart(new GermGuiTexture("lang")
                        .setPath("aestus/toolbox/action_action.png")
                        .setWidth("28/2560*w").setHeight("14/1440*h")
                        .setLocationX("27/2560*w").setLocationY("7/1440*h")
                );
            }

        }

        public GChild addAction(GAction a)
        {
            a.parent=this;
            actions.add(a);
            addGuiPart(a);
            update();
            return this;
        }

        public void updateLayout()
        {
            int h = 0;
            for (GAction a : actions) {
                h += a.getLayoutHeight();
                UtilGui2K.setLocation(a, 0, h);
            }
        }

        protected void close()
        {
            if(getParentPart() instanceof IGuiPartContainer)
                ((IGuiPartContainer) getParentPart()).removeGuiPart(this);
            else if(getParentGuiScreen()!=null)
                getParentGuiScreen().removeGuiPart(this);
            parent.removeGuiPart(this);
        }
    }

    protected class GBlock extends GObject
    {
        protected final ModuleBlock block;
        protected GermGuiSlot slot;
        protected GermGuiLabel id = UtilGui2K.createLabel("id","",90,5,3F, GermGuiLabel.Align.CENTER);
        protected GermGuiLabel match = UtilGui2K.createLabel("match","",300,5,3F, GermGuiLabel.Align.CENTER);
        protected GermGuiButton receive = UtilGui2K.createButton("receive","aestus/toolbox/blockui/get.png",424,3)
                .registerCallbackHandler(this::get, GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiButton place = UtilGui2K.createButton("place","aestus/toolbox/blockui/place.png",504,3)
                .registerCallbackHandler(this::place, GermGuiButton.EventType.LEFT_CLICK);

        public GBlock(ModuleBlock block)
        {
            super(block.id);
            this.block=block;

            slot=createSlot();
            addGuiPart(id);
            addGuiPart(match);
            addGuiPart(slot.setItemStack(block.create()));
            addGuiPart(receive);
            addGuiPart(place);
        }

        @Override
        protected void init()
        {
            super.init();
            button.setDefaultPath("aestus/toolbox/blockui/bar.png");
            button.setHoverPath(null);
            button.setHoverColor(0xFFFFAA00L);
        }

        protected void get(Player operator , GermGuiButton button)
        {
            UtilGerm.giveBlock(operator,block.id,false);
        }
        protected void place(Player operator , GermGuiButton button)
        {
            UtilGerm.place(operator,block,operator.getLocation(),false);
        }

        @Override
        protected void update()
        {
            id.setText(block.id);
            match.setText(block.match);
        }

        @Override
        protected void callbackButton(Player player, Enum<?> anEnum) {
            super.callbackButton(player, anEnum);
            if(anEnum == GermGuiButton.EventType.RIGHT_CLICK){
                PlayerState ps = getPlayerState();

                GChild child = new GChild(GToolBox.this);

                if(ps.isFavorites(block.id))
                    child.addAction(new GAction("[soon]移出收藏夹"){@Override protected void callbackButton(Player p, GermGuiButton b) {
                        super.callbackButton(p, b);
                        getPlayerState().removeFavorites(block.id);
                    }});
                else
                    child.addAction(new GAction("[soon]添加收藏夹"){@Override protected void callbackButton(Player p, GermGuiButton b) {
                        super.callbackButton(p, b);
                        getPlayerState().addFavorites(RootType.ITEM,block.id);
                    }});

                child.addAction(new GAction("[soon]添加到预设"){@Override protected void callbackButton(Player p, GermGuiButton b) {
                    super.callbackButton(p, b);
                    getPlayerState().addFavorites(RootType.ITEM,block.id);
                }});

                child.addAction(new GAction("§4关闭"){@Override protected void callbackButton(Player p, GermGuiButton b) {
                    super.callbackButton(p, b);
                    child.close();
                }});

                GToolBox.this.addGuiPart(child);
            }
        }

        protected GermGuiSlot createSlot()
        {
            return new GermGuiSlot("slot").setIdentity(UUID.randomUUID().toString())
                    .setLocationX("242/2560*w")
                    .setLocationY("37/1440*h")
                    .setWidth("100/2560*w")
                    .setHeight("100/1440*h")
//                    .setItemSize("3/2560*w")
                    .setEmptyPath("aestus/toolbox/air.png")
                    .setFillPath("aestus/toolbox/air.png");
        }

        @Override public int getLayoutHeight() {return 140;}
        @Override public int getLayoutWeight() {return 584;}
    }

    protected class GObject extends GermGuiCanvas
    {
        protected GermGuiButton button;

        public GObject(String indexName)
        {
            super(indexName);
            init();
        }

        protected void callbackButton(Player player, Enum<?> anEnum)
        {
            if(anEnum== GermGuiButton.EventType.LEFT_CLICK){
                //todo
            }
        }

        public int getLayoutHeight()
        {
            return 100;
        }
        public int getLayoutWeight()
        {
            return 584;
        }
        protected void init()
        {
            button=createButton();

            addGuiPart(button);
        }

        protected void update()
        {

        }

        protected GermGuiButton createButton(){
            return new GermGuiButton("button"){@Override public void onCallback(Player player, Enum<?> anEnum) {
                    super.onCallback(player, anEnum);
                    GToolBox.GObject.this.callbackButton(player,anEnum);
                }}.setDefaultPath("item/air.png").setHoverColor(0x4400FF00L).setWidth("tw1/2560*w").setHeight("th1/1440*h");
        }
    }

    protected class GType extends GermGuiCanvas
    {
        protected GermGuiScroll scroll = new GermGuiScroll("list")
                .setWidth("584/2560*w").setHeight("789/1440*h")
                .setLocationX("8/2560*w").setLocationY("166/1440*h")
                .setInvalidV(false).setInvalidH(true)
                .setRelative(true)
                .setSliderV(new GermGuiColor("sliderV").setEndColor(0xAA000000).setColor(0xAA000000).setWidth("0").setHeight("0"))
                .setSliderH(new GermGuiColor("sliderH").setEndColor(0xAA000000).setColor(0xAA000000).setWidth("0").setHeight("0"))
                .setScrollableV("2000/1440*h");
        protected GermGuiInput input = new GermGuiInput("input")
                .setLocationX("72/2560*w").setLocationY("80/1440*h")
                .setWidth("516/2560*w").setHeight("40/1440*h")
                .setAutoClear(false).setSync(true).setBackground(false)
                .registerCallbackHandler((p,i)->search(i.getInput()), GermGuiInput.EventType.ENTER);
        protected GermGuiButton sortID = UtilGui2K.createButton("sort_id","aestus/toolbox/id.png",12,130)
                .registerCallbackHandler((p,b)->comparator(), GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiButton sortMatch = UtilGui2K.createButton("sort_match","aestus/toolbox/type.png",222,130)
                .registerCallbackHandler((p,b)->comparator(), GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiButton actions = UtilGui2K.createButton("actions","aestus/toolbox/action.png",432,130);
        protected GermGuiTexture background = UtilGui2K.createTexture("background","aestus/toolbox/blockui/main.png",0,0);
        protected GermGuiLabel amount = UtilGui2K.createLabel("amount","[0]",390,24,5, GermGuiLabel.Align.RIGHT);
        protected final List<GObject> available = new ArrayList<>();
        protected GermGuiButton mini = UtilGui2K.createButton("mini","aestus/toolbox/mini.png",465,10)
                .registerCallbackHandler((p,b)->mini(), GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiButton close = UtilGui2K.createButton("close","aestus/toolbox/close.png",530,10)
                .registerCallbackHandler((p,b)->close(), GermGuiButton.EventType.LEFT_CLICK);
        protected GermGuiButton reload = UtilGui2K.createButton("reload","aestus/toolbox/reload.png",400,10)
                .registerCallbackHandler(this::reload, GermGuiButton.EventType.LEFT_CLICK);

        public GType(String indexName)
        {
            super(indexName);
            init();
            update();
        }

        protected void init()
        {
            setDrag(new Drag().setWidth("400/2560*w").setHeight("76/1440*h"));
            addGuiPart(background);
            addGuiPart(amount);
            addGuiPart(scroll);
            addGuiPart(UtilGui2K.createTexture("options_tex","aestus/toolbox/options.png",8,76));
            addGuiPart(UtilGui2K.createTexture("input_tex","aestus/toolbox/search.png",12,80));
            addGuiPart(sortMatch);
            addGuiPart(sortID);
            addGuiPart(actions);
            addGuiPart(input);
            addGuiPart(mini);
            addGuiPart(close);
            addGuiPart(reload);

            resetAvailable();
        }

        protected void resetAvailable()
        {

        }

        protected void comparator()
        {
            available.sort((o1, o2) -> {
                if(o1==null&&o2==null)  return 0;
                else if(o1==null)       return -1;
                else if(o2==null)       return 1;
                return o1.getIndexName().compareTo(o2.getIndexName());
            });
        }

        protected void search(String mark)
        {
            if(mark!=null&&!("".equals(mark)))
            {
                for (GObject object : new ArrayList<>(available))
                    if(!object.getIndexName().contains(mark))
                        removeObject(object);
                update();
            }else{
                resetAvailable();
            }
        }

        public void update()
        {
            updateLayout();
            updateAmount();
        }

        protected void mini()
        {
            handlerList.put(iplayer,GToolBox.this);
            GToolBox.this.close();
        }

        public void close()
        {
            handlerList.remove(iplayer);
            GToolBox.this.close();
        }

        protected void reload(Player operator , GermGuiButton button)
        {
            if(!operator.isOp()) return;
            GermLoader.reload();
            blockList.resetAvailable();
        }

        protected void updateLayout()
        {
            for(int i = 0;i<available.size();i++)
            {
                GObject object = available.get(i);
                UtilGui2K.setLocation(object,0,i*object.getLayoutHeight());
            }
        }

        protected void updateAmount()
        {
            amount.setText("["+available.size()+"]");
        }

        protected GType addObject(GObject o){available.add(o);scroll.addGuiPart(o);o.update();return this;}
        protected GType removeObject(GObject o){available.remove(o);scroll.removeGuiPart(o);return this;}

    }

    protected class GTypeBlock extends GType
    {
        public GTypeBlock()
        {
            super("blocks");
        }

        @Override
        protected void init()
        {
            super.init();
            background.setPath("aestus/toolbox/blockui/main.png");
        }

        @Override
        protected void resetAvailable()
        {
            available.clear();
            scroll.clearGuiPart();
            ModuleBlock.BLOCKS.values().forEach(e->addObject(new GBlock(e)));
            update();
        }

    }

    protected GToolBox(Player iplayer)
    {
        super("tool_box",true);

        this.iplayer=iplayer;

        init();
        update();
    }

    protected GTypeBlock blockList;

    protected void init()
    {
        blockList = new GTypeBlock();
        addGuiPart(UtilGui2K.setLocation(blockList,980,220));
    }
    protected void update()
    {
    }

    public static GToolBox get(Player player)
    {
        for(GermGuiScreen g : GuiManager.getOpenedAllGui(player))
            if(g instanceof GToolBox)return (GToolBox) g;
        return null;
    }

    public static GToolBox open(Player player)
    {
        GToolBox hangup = handlerList.get(player);
        if(hangup!=null){
            hangup.openGui(player);
            handlerList.remove(player);
            return hangup;
        }else{
            GToolBox g = new GToolBox(player);
            g.openGui(player);
            return g;
        }
    }
}
