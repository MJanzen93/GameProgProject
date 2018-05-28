package Game;

public final class ConstantValues
{
    // size of the world
    public static final int WORLD_WIDTH      = 116000;
    public static final int WORLD_HEIGHT     = 4000;
    // size of the displayed part of the world
    public static int WORLDPART_WIDTH  = 1600;
    public static int WORLDPART_HEIGHT = 800;
    // border: when to scroll
    static final int SCROLL_BOUNDS_X    =  600;
    static final int SCROLL_BOUNDS_Y    =  300;

    static final double SPAWN_INTERVAL = 0.2;
    static final double SPAWN_GRENADE  = 10.0;
    static final double LIFE_GRENADE   = 15.0;

    static final int TYPE_AVATAR  = 1;
    static final int TYPE_TEXT    = 2;
    static final int TYPE_TREE    = 3;
    static final int TYPE_ZOMBIE  = 4;
    static final int TYPE_SHOT    = 5;
    static final int TYPE_GRENADE = 6;
}
