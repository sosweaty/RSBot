package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;

import java.util.concurrent.Callable;

public class DropOre extends Task {
    private final Ores oresToDrop;

    public DropOre(ClientContext ctx, Ores ores) {
        super(ctx);
        this.oresToDrop = ores;
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && ctx.players.local().idle();
    }

    @Override
    public void execute() {
        for (Item ore : ctx.backpack.select().id(oresToDrop.getItemId())) {
            ore.interact("Drop", oresToDrop.getName());
        }
    }
}

