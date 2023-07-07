package net.mcbbs.a1mercet.germtools.util;

import com.germ.germplugin.api.dynamic.DynamicBase;
import com.germ.germplugin.api.dynamic.gui.*;

public class UtilGui2K
{
    public static GermGuiPart<? extends DynamicBase> setLocation(GermGuiPart<? extends DynamicBase> part , int x , int y)
    {
        return part.setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h");
    }
    public static IGuiPartResizeable<?> setSize(IGuiPartResizeable<?> part , int w , int h)
    {
        part.setWidth(w/2560F+"*w");
        part.setHeight(h/1440F+"*h");
        return part;
    }

    public static GermGuiTexture setSize(GermGuiTexture part , int w , int h)
    {
        return part.setWidth(w/2560F+"*w").setHeight(h/1440F+"*h");
    }
    public static GermGuiButton setLocation(GermGuiButton part , int x , int y)
    {
        return part.setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h");
    }
    public static GermGuiButton setSize(GermGuiButton part , int w , int h)
    {
        return part.setWidth(w/2560F+"*w").setHeight(h/1440F+"*h");
    }
    public static GermGuiTexture createBackground(String name , String path)
    {
        return new GermGuiTexture(name).setPath(path).setWidth("w").setHeight("h");
    }
    public static GermGuiTexture createTexture(String name , String path , int x , int y , int width , int height)
    {
        return new GermGuiTexture(name).setPath(path)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth(width/2560F+"*w").setHeight(height/1440F+"*h");
    }
    public static GermGuiLabel createLabel(String name , String text , int x , int y)
    {
        return new GermGuiLabel(name).setText(text).setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h").setFont("font").setFontSize(48);
    }
    public static GermGuiLabel createLabel(String name , String text , int x , int y , float scale)
    {
        return new GermGuiLabel(name).setText(text).setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h").setFont("font").setFontSize(48).setScale(scale+"/2560*w");
    }
    public static GermGuiLabel createLabel(String name , String text , int x , int y , float scale, GermGuiLabel.Align align)
    {
        return new GermGuiLabel(name).setText(text).setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h").setFont("font").setFontSize(48).setScale(scale+"/2560*w").setAlign(align);
    }
    public static GermGuiTexture createTexture(String name , String path , int x , int y )
    {
        return new GermGuiTexture(name).setPath(path)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth("tw1/2560*w").setHeight("th1/1440*h");
    }

    public static GermGuiColor createColor(String name , long color , int x , int y , int width , int height)
    {
        return new GermGuiColor(name)
                .setColor(color).setEndColor(color)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth(width/2560F+"*w").setHeight(height/1440F+"*h");
    }

    public static GermGuiButton createButton(String name , String path , String path2 , int x , int y , int width , int height)
    {
        return new GermGuiButton(name).setDefaultPath(path).setHoverPath(path2)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth(width/2560F+"*w").setHeight(height/1440F+"*h");
    }

    public static GermGuiButton createButton(String name , String path , int x , int y , int width , int height)
    {
        return new GermGuiButton(name).setDefaultPath(path).setHoverColor(0xFF00FF00L)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth(width/2560F+"*w").setHeight(height/1440F+"*h");
    }

    public static GermGuiButton createButton(String name , String path , String path2 , int x , int y)
    {
        return new GermGuiButton(name).setDefaultPath(path).setHoverPath(path2)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth("tw1/2560*w").setHeight("th1/1440*h");
    }

    public static GermGuiButton createButton(String name , String path , int x , int y)
    {
        return new GermGuiButton(name).setDefaultPath(path).setHoverColor(0xFF00FF00L)
                .setLocationX(x/2560F+"*w").setLocationY(y/1440F+"*h")
                .setWidth("tw1/2560*w").setHeight("th1/1440*h");
    }
}
