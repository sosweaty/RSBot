package com.sosweaty.scripts.rt6.aiominer.constants;

public enum Ores {
    COPPER("Copper Ore", new int[]{3027, 3229, 11960, 11961, 11962}, 436),
    TIN("Tin Ore", new int[]{11957, 11958, 11959}, 438),
    IRON("Iron Ore", new int[]{11954, 11955, 11956}, 440);

    private final String name;
    private final int[] objId;
    private final int itemId;

    Ores(String name, int[] objid, int itemId) {
        this.name = name;
        this.objId = objid;
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public int[] getObjId() {
        return objId;
    }

    public int getItemId() {
        return itemId;
    }

}
