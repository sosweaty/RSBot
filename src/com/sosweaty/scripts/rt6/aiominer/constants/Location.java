package com.sosweaty.scripts.rt6.aiominer.constants;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum Location {
    VARROCKEAST("Varrock East Mine", new Tile[]{new Tile(3253, 3421),
            new Tile(3253, 2424), new Tile(3254, 3427), new Tile(3257, 3428),
            new Tile(3260, 3428), new Tile(3263, 3428), new Tile(3266, 3428),
            new Tile(3269, 3428), new Tile(3272, 3428), new Tile(3273, 3428),
            new Tile(3274, 3428), new Tile(3277, 3428), new Tile(3280, 3428),
            new Tile(3283, 3427), new Tile(3285, 3424), new Tile(3287, 3421),
            new Tile(3287, 3418), new Tile(3288, 3415), new Tile(3289, 3412),
            new Tile(3290, 3409), new Tile(3291, 3406), new Tile(3291, 3403),
            new Tile(3291, 3400), new Tile(3291, 3397), new Tile(3291, 3394),
            new Tile(3291, 3391), new Tile(3291, 3388), new Tile(3292, 3386),
            new Tile(3292, 3385), new Tile(3292, 3384), new Tile(3293, 3381),
            new Tile(3293, 3378), new Tile(3290, 3375), new Tile(3287, 3372),
            new Tile(3287, 3369), new Tile(3285, 3366)},
            new Area(new Tile(3257, 3420), new Tile(3250, 3423)),
            new Area(new Tile(3291, 3360), new Tile(3279, 3372))),
    VARROCKWEST("Varrock West Mine", new Tile[]{new Tile(3185, 3437, 0),
            new Tile(3185, 3434, 0), new Tile(3185, 3431, 0), new Tile(3182, 3428, 0),
            new Tile(3179, 3428, 0), new Tile(3176, 3428, 0), new Tile(3173, 3425, 0),
            new Tile(3171, 3422, 0), new Tile(3171, 3419, 0), new Tile(3172, 3416, 0),
            new Tile(3172, 3413, 0), new Tile(3171, 3410, 0), new Tile(3171, 3407, 0),
            new Tile(3171, 3404, 0), new Tile(3168, 3401, 0), new Tile(3169, 3398, 0),
            new Tile(3170, 3395, 0), new Tile(3172, 3393, 0), new Tile(3175, 3392, 0),
            new Tile(3176, 3390, 0), new Tile(3177, 3387, 0), new Tile(3178, 3384, 0),
            new Tile(3179, 3381, 0), new Tile(3182, 3379, 0), new Tile(3182, 3376, 0),
            new Tile(3183, 3373, 0), new Tile(3181, 3371, 0)},
            new Area(new Tile(3182, 3432), new Tile(3189, 3446)),
            new Area(new Tile(3189, 3380), new Tile(3171, 3363)));

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
