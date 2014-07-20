package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.AIOMiner;
import com.sosweaty.scripts.rt6.aiominer.constants.Location;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.TilePath;

public class WalkToBank extends Task {
    private Location locationToMine;

    public WalkToBank(ClientContext ctx, Location location) {
        super(ctx);
        this.locationToMine = location;
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && ctx.players.local().idle()
                && !locationToMine.getBankArea().contains(ctx.players.local());
    }

    @Override
    public void execute() {
        final TilePath tilePath = ctx.movement.newTilePath(locationToMine.getTilepath());


        ctx.movement.step(locationToMine.getBankArea().getRandomTile());

        AIOMiner.setStatus("Walking to Bank");
        tilePath.randomize(1, 1).reverse().traverse();
    }
}
