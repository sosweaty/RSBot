package com.sosweaty.scripts.rt6.aiominer.tasks;

import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.Random;
import org.powerbot.script.rt6.ClientContext;

public class CameraPitch extends Task {
    private int i = Random.nextInt(70,84);

    public CameraPitch(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.camera.pitch(84);
    }

    @Override
    public void execute() {
        ctx.camera.pitch(84);
    }
}
