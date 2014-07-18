package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.rt6.Backpack;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

public class DropGems extends Task {

    final int[] gems = {1617, 1619, 1621, 1632, 1625, 1627, 1629, 1631, 6571, 20913, 21345};

    public DropGems(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().id(gems).count() > 0;
    }

    @Override
    public void execute() {
        for ( Item gem : ctx.backpack.select().id(gems) ) {
            gem.interact("Drop");
        }
    }
}
