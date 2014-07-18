package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.constants.Location;
import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.ClientContext;

import java.util.concurrent.Callable;

public class DepositToBank extends Task {
    private Location locationToMine;
    private Ores oresToMine;

    public DepositToBank(ClientContext ctx, Location location, Ores ores) {
        super(ctx);
        this.locationToMine = location;
        this.oresToMine = ores;
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == 28
                && locationToMine.getBankArea().contains(ctx.players.local());
    }

    @Override
    public void execute() {
        if (ctx.bank.inViewport()) {
            if (ctx.bank.open()) {
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.bank.opened();
                    }
                }, Random.nextInt(100, 200), 10);
                ctx.bank.deposit(oresToMine.getItemId(), Bank.Amount.ALL);
                ctx.bank.close();
            }
        }
    }
}
