package com.sosweaty.scripts.rt6.aiominer.constants;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum Location {
    VARROCKEAST("Varrock East Mine", new Tile[]{new Tile(3253, 3420, 0),
            new Tile(3253, 3426, 0), new Tile(3258, 3429, 0), new Tile(3264, 3429, 0),
            new Tile(3273, 3428, 0), new Tile(3274, 3428, 0), new Tile(3281, 3429, 0),
            new Tile(3285, 3425, 0), new Tile(3288, 3420, 0), new Tile(3290, 3415, 0),
            new Tile(3290, 3409, 0), new Tile(3291, 3404, 0), new Tile(3291, 3398, 0),
            new Tile(3291, 3392, 0), new Tile(3292, 3386, 0), new Tile(3292, 3384, 0),
            new Tile(3294, 3380, 0), new Tile(3292, 3375, 0), new Tile(3289, 3372, 0),
            new Tile(3287, 3368, 0), new Tile(3284, 3365, 0)},
            new Area(new Tile(3257, 3420), new Tile(3250, 3423)),
            new Area(new Tile(3291, 3360), new Tile(3279, 3372)));

    private final String name;
    private final Tile[] tilepath;
    private final Area bankArea;
    private final Area mineArea;

    Location(String name, Tile[] tilepath, Area bankArea, Area mineArea) {
        this.name = name;
        this.tilepath = tilepath;
        this.bankArea = bankArea;
        this.mineArea = mineArea;
    }

    public String getName() {
        return name;
    }

    public Tile[] getTilepath() {
        return tilepath;
    }

    public Area getBankArea() {
        return bankArea;
    }

    public Area getMineArea() {
        return mineArea;
    }
}
