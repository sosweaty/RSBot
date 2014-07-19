package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;
import org.powerbot.script.rt6.ItemQuery;

import java.util.concurrent.Callable;

public class M1D1 extends Task {
    Ores oresSelected;

    public M1D1(ClientContext ctx, Ores ores) {
        super(ctx);
        this.oresSelected = ores;
    }

    @Override
    public boolean activate() {
        return ctx.objects.select().id(oresSelected.getObjId()).nearest().poll().inViewport();
    }

    @Override
    public void execute() {
        final GameObject objOre = ctx.objects.id(oresSelected.getObjId()).nearest().poll();
        final ItemQuery<Item> itemOre = ctx.backpack.select().id(oresSelected.getItemId());
        if (itemOre.count() < 1) {
            if (objOre.click()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return !objOre.valid();
                    }
                }, Random.nextInt(100, 200), 10);
            }
        } else {
            ctx.backpack.select().id(oresSelected.getObjId()).poll().interact("Drop", oresSelected.getName());
        }
    }
}
