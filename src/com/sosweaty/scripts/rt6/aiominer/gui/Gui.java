package com.sosweaty.scripts.rt6.aiominer.gui;

import com.sosweaty.scripts.rt6.aiominer.constants.Location;
import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.aiominer.tasks.*;
import com.sosweaty.scripts.rt6.aiominer.tasks.Movement.WalkToBank;
import com.sosweaty.scripts.rt6.aiominer.tasks.Movement.WalkToMine;
import com.sosweaty.scripts.rt6.framework.Task;
import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Gui extends ClientAccessor {

    private Ores ores;
    private Location location;
    private JPanel contentPane;
    private final JFrame mainFrame = new JFrame();
    private final List<Task> taskList;

    public Gui(ClientContext ctx, List<Task> taskList) {
        super(ctx);
        this.taskList = taskList;
        initialize();
        mainFrame.setTitle("AIO Miner Settings");
        mainFrame.setVisible(true);

    }

    private void initialize() {
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setBounds(100, 100, 380, 362);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(8, 1, 0, 0));

        final JLabel selectOre = new JLabel("Select what Ore to mine.");
        final JComboBox<Ores> oreComboBox = new JComboBox<Ores>(Ores.values());

        final JLabel selectMode = new JLabel("Select what mode to use.");
        final JComboBox<String> modeComboBox = new JComboBox<String>();
        modeComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{
                "Not Selected", "Power Mining Mode", "Banking Mode", "M1D1 Mode"
        }));

        final JLabel selectLocation = new JLabel("Select a mining location. leave blank if Power mining");
        final JComboBox<Location> locationComboBox = new JComboBox<Location>(Location.values());

        final JCheckBox dropGemsCheckbox = new JCheckBox("Drop Gems?");
        mainFrame.add(selectOre);
        mainFrame.add(oreComboBox);
        mainFrame.add(selectMode);
        mainFrame.add(modeComboBox);
        mainFrame.add(selectLocation);
        mainFrame.add(locationComboBox);
        mainFrame.add(dropGemsCheckbox);

        final JButton btnStart = new JButton("Start");
        mainFrame.add(btnStart);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modeSelected = modeComboBox.getSelectedItem().toString();

                taskList.add(new CameraPitch(ctx));
                taskList.add(new CloseWidgetCockBlocks(ctx));
                taskList.add(new AntiBan(ctx));

                // Ores
                ores = (Ores) oreComboBox.getSelectedItem();

                // Location
                location = (Location) locationComboBox.getSelectedItem();

                // Check Boxes
                if (dropGemsCheckbox.isSelected())
                    taskList.add(new DropGems(ctx));

                // Mode
                if (modeSelected.equals("Power Mining Mode")) {
                    taskList.add(new MineOre(ctx, ores, location));
                    taskList.add(new DropOre(ctx, ores));
                } else if (modeSelected.equals("Banking Mode")) {
                    taskList.add(new MineOre(ctx, ores, location));
                    taskList.add(new WalkToBank(ctx, location));
                    taskList.add(new WalkToMine(ctx, location));
                    taskList.add(new DepositToBank(ctx, location, ores));
                } else if (modeSelected.equals("M1D1 Mode")) {
                    taskList.add(new M1D1(ctx, ores));
                }
                mainFrame.dispose();
            }
        });
    }

}
