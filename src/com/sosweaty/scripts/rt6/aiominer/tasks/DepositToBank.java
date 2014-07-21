package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.aiominer.AIOMiner;
import com.sosweaty.scripts.rt6.aiominer.constants.Location;
import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.rt6.Bank;
import org.powerbot.script.rt6.ClientContext;

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
        AIOMiner.setStatus("Depositing to bank");
        if (ctx.bank.inViewport()) {
            if (ctx.bank.open()) {
                if (ctx.bank.deposit(oresToMine.getItemId(), Bank.Amount.ALL)) {
                    ctx.bank.close();
                }
            }
        }
    }
}