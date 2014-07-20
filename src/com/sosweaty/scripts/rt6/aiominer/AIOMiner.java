package com.sosweaty.scripts.rt6.aiominer;

import com.sosweaty.scripts.rt6.aiominer.gui.Gui;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script.Manifest;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Manifest(description = "Mines ores in various locations", name = "Sweaty AIO Miner")
public class AIOMiner extends PollingScript<ClientContext> implements PaintListener, MessageListener {

    private final Color paintBgColor = new Color(20, 20, 20, 165);
    private final Color paintBgColor2 = new Color(255, 255, 255, 165);
    private final Color textHColor = new Color(255, 255, 255, 255);
    private final Color textBColor = new Color(190, 190, 190, 255);
    private int ores, oresPerHour, currentXp, xpGained, startXp, xpPerHour;
    private String scriptName, version;
    private static String status = "Waiting for Input";

    List<Task> tasklist = new ArrayList<Task>();

    @Override
    public void start() {
        startXp = ctx.skills.experience(Skills.MINING);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui(ctx, tasklist);
            }
        });
    }

    public void poll() {
        for (Task task : tasklist) {
            if (task.activate()) {
                task.execute();
            }
        }
    }

    @Override
    public void messaged(MessageEvent evt) {
        if (evt.text().startsWith("You manage to mine some"))
            ores++;
    }

    public static void setStatus(String s) {
        status = s;
    }

    @Override
    public void repaint(Graphics g) {
        scriptName = "AIO Miner";
        version = "0.1";
        currentXp = ctx.skills.experience(Skills.MINING);
        xpGained = currentXp - startXp;
        xpPerHour = (int) (xpGained * (3600000d / getRuntime()));

        oresPerHour = (int) (ores * (3600000d / getRuntime()));
        Font fontTitle = new Font("Verdana", 0, 32);
        Font fontBody = new Font("Verdana", 0, 12);
        g.setColor(paintBgColor2);
        g.fillRect(8, 8, 179, 44);
        g.setColor(paintBgColor);
        g.fillRect(10, 10, 175, 40);

        g.setFont(fontTitle);
        g.setColor(textHColor);
        g.drawString(scriptName, 15, 42);

        MouseInfo.getPointerInfo().

                getLocation();

        g.setColor(Color.yellow);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y - 5, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y + 5);
        g.drawLine(ctx.mouse.getLocation().x - 5, ctx.mouse.getLocation().y + 5, ctx.mouse.getLocation().x + 5, ctx.mouse.getLocation().y - 5);

        g.setColor(paintBgColor2);
        g.fillRect(8, 53, 179, 184);
        g.setColor(paintBgColor);
        g.fillRect(10, 55, 175, 180);
        g.setFont(fontBody);
        g.setColor(textHColor);
        g.drawString("Script Info", 15, 70);
        g.drawString("Ore Stats", 15, 145);
        g.drawString("XP Stats", 16, 190);
        //g.drawString("Profit Stats",15, 220); >> TO DO

        g.setColor(textBColor);
        g.drawString("» Run Time: " + formatTime(getRuntime()), 20, 85);
        g.drawString("» Status: " + status, 20, 100);
        g.drawString("» Version: " + version, 20, 115);
        g.drawString("» By: Sosweaty", 20, 130);


        g.drawString("» Ores Mined: " + ores, 20, 160);
        g.drawString("» Ores p/h: " + oresPerHour, 20, 175);

        g.drawString("» XP Gained: " + xpGained, 20, 205);
        g.drawString("» XP p/h: " + xpPerHour, 20, 220);
        //g.drawString("» Profit Gained", 20, 235); >> TO DO
        //g.drawString("» Profit p/h", 20, 250); >> TO DO

    }

    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
                + (s < 10 ? "0" + s : s);
    }

}