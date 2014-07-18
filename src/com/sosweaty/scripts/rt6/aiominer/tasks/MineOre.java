package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.AIOMiner;
import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

import java.util.concurrent.Callable;

public class MineOre extends Task {
    private Ores oresToMine;
    private AIOMiner paint;

    public MineOre(ClientContext ctx, Ores ores) {
        super(ctx);
        this.oresToMine = ores;
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28
                && ctx.players.local().idle()
                && ctx.objects.select().id(oresToMine.getObjId()).nearest().poll().inViewport();
    }

    @Override
    public void execute() {
        final GameObject ore = ctx.objects.select().id(oresToMine.getObjId()).nearest().poll();
        final int i = Random.nextInt(0, 90);

        ctx.combatBar.actionAt(0).select();
        bounds(ore);
        System.out.println("Attempting to click ores.");
        if (ore.click()) {
            if (ore.tile().distanceTo(ctx.players.local().tile()) < 2) {
                if (i % 20 == 0) {
                    ctx.camera.angle(Random.nextInt(0, 360));
                }
            } else if (ore.tile().distanceTo(ctx.players.local().tile()) > -2) {
                ctx.camera.turnTo(ore);
            }
            System.out.println("Calling Condition.wait");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("Waiting for player to finish mining.");
                    return !ore.valid();
                }
            }, Random.nextInt(100, 200), 10);
            System.out.println("Finished mining");
        } else {
            System.out.println("Failed to click ores.");
        }
    }

    public void bounds(GameObject ore) {

        int id = ore.id();

        //Tin Ores
        final int[] bounds11957 = {-56, 128, -148, 0, -108, 92};
        final int[] bounds11958 = {-92, 96, -140, 0, -76, 104};
        final int[] bounds11959 = {-92, 112, -156, 0, -144, 64};


        //Copper Ores
        final int[] bounds11960 = {-132, 92, -128, 0, -112, 108};
        final int[] bounds11961 = {-92, 96, -140, 0, -76, 104};
        final int[] bounds11962 = {-56, 168, -128, 0, -100, 120};


        //Iron Ores
        final int[] bounds11954 = {-56, 168, -128, 0, -100, 120};
        final int[] bounds11955 = {-92, 96, -140, 0, -76, 104};
        final int[] bounds11956 = {-132, 92, -128, 0, -112, 108};

        if (id == 11954) {
            ore.bounds(bounds11954);
        } else if (id == 11955) {
            ore.bounds(bounds11955);
        } else if (id == 11956) {
            ore.bounds(bounds11956);
        } else if (id == 11957) {
            ore.bounds(bounds11957);
        } else if (id == 11958) {
            ore.bounds(bounds11958);
        } else if (id == 11959) {
            ore.bounds(bounds11959);
        } else if (id == 11960) {
            ore.bounds(bounds11960);
        } else if (id == 11961) {
            ore.bounds(bounds11961);
        } else if (id == 11962) {
            ore.bounds(bounds11962);
        }

    }
}
