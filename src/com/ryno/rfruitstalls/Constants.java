package com.ryno.rfruitstalls;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Item;

import java.util.HashSet;

public class Constants {
    public static Area GENERAL_STALL_AREA = new Area(
        new Tile(1793, 3609, 0),
        new Tile(1794, 3607, 0),
        new Tile(1796, 3605, 0),
        new Tile(1801, 3605, 0),
        new Tile(1802, 3607, 0),
        new Tile(1802, 3610, 0),
        new Tile(1800, 3611, 0),
        new Tile(1796, 3611, 0)
    );
    public static Area WALKING_STALL_AREA = new Area(new Tile(1794, 3609, 0), new Tile(1801, 3606, 0));
    public static int INTERACTABLE_STALL_ID = 28823;


    public static final String STRANGE_FRUIT_STRING = "Strange fruit";
    public static final String COOKING_APPLE_STRING = "Cooking apple";
    public static final String BANANA_STRING = "Banana";
    public static final String STRAWBERRY_STRING = "Strawberry";
    public static final String JANGERBERRIES_STRING = "Jangerberries";
    public static final String LEMON_STRING = "Lemon";
    public static final String REDBERRIES_STRING = "Redberries";
    public static final String PINEAPPLE_STRING = "Pineapple";
    public static final String LIME_STRING = "Lime";
    public static final String PAPAYA_STRING = "Papaya fruit";
    public static final String GOLOVANOVA_STRING = "Golovanova fruit top";

    public static HashSet<String> FRUIT_STRINGS = new HashSet<>(){
        {
            add(STRANGE_FRUIT_STRING);
            add(COOKING_APPLE_STRING);
            add(BANANA_STRING);
            add(STRAWBERRY_STRING);
            add(JANGERBERRIES_STRING);
            add(LEMON_STRING);
            add(REDBERRIES_STRING);
            add(PINEAPPLE_STRING);
            add(LIME_STRING);
            add(PAPAYA_STRING);
            add(GOLOVANOVA_STRING);
        }
    };
}
