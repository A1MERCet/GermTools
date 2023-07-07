package net.mcbbs.a1mercet.germtools.germ;

public enum ModelType
{
    bedrockEdition("基岩版"),
    javaEdition("Jvav版"),
    armourersWorkshop("时装工坊"),

    ;
    private String name;

    ModelType(String name) {this.name = name;}
    public String getName() {return name;}
}
