package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.AIOMiner;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

import java.util.concurrent.Callable;

public class DropGems extends Task {

    private final int[] gems = {1617, 1619, 1621, 1623, 1625, 1627, 1629, 1631, 6571, 20913, 21345};

    public DropGems(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().id(gems).count() > 0;
    }

    @Override
    public void execute() {
        AIOMiner.setStatus("Found Gem");
        for (final Item gem : ctx.backpack.select().id(gems)) {
            AIOMiner.setStatus("Dropping Gem");
            gem.interact("Drop");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !gem.valid();
                }
            }, Random.nextInt(100, 150), 10);
        }
    }
}
