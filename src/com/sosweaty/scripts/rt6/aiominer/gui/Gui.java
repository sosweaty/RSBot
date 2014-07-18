package com.sosweaty.scripts.rt6.aiominer.gui;

import com.sosweaty.scripts.rt6.aiominer.constants.Location;
import com.sosweaty.scripts.rt6.aiominer.constants.Ores;
import com.sosweaty.scripts.rt6.aiominer.tasks.*;
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
        mainFrame.setTitle("Settings");
        mainFrame.setVisible(true);

    }

    private void initialize() {
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setBounds(100, 100, 380, 362);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(6, 1, 0, 0));

        final JLabel selectOre = new JLabel("Select what Ore to mine.");
        final JComboBox<String> oreComboBox = new JComboBox<String>();
        oreComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{
                "Choose an ore to mine", "Copper Ore", "Tin Ore", "Iron Ore"
        }));

        final JLabel selectMode = new JLabel("Select what mode to use.");
        final JComboBox<String> modeComboBox = new JComboBox<String>();
        modeComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{
                "Select a mode", "Banking Mode", "Power Mining Mode"
        }));

        final JLabel selectLocation = new JLabel("Select a mining location. leave blank if Power mining");
        final JComboBox<String> locationComboBox = new JComboBox<String>();
        locationComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{
                "Select a mining location.", "Varrock East Mine"
        }));

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
                String oreSelected = oreComboBox.getSelectedItem().toString();
                String modeSelected = modeComboBox.getSelectedItem().toString();
                String locationSelected = locationComboBox.getSelectedItem().toString();

                taskList.add(new CameraPitch(ctx));

                // Ores
                if (oreSelected.equals("Copper Ore"))
                    ores = Ores.COPPER;
                else if (oreSelected.equals("Tin Ore"))
                    ores = Ores.TIN;
                else if (oreSelected.equals("Iron Ore"))
                    ores = Ores.IRON;


                // Check Boxes
                if (dropGemsCheckbox.isSelected())
                    taskList.add(new DropGems(ctx));

                // Mode
                if (modeSelected.equals("Banking Mode")) {
                    taskList.add(new MineOre(ctx, ores));
                } else if (modeSelected.equals("Power Mining Mode")) {
                    taskList.add(new MineOre(ctx, ores));
                    taskList.add(new DropOre(ctx, ores));
                }
                // Location
                if (locationSelected.equals("Varrock East Mine")) {
                    location = Location.VARROCKEAST;
                    taskList.add(new WalkToBank(ctx, location));
                    taskList.add(new WalkToMine(ctx, location));
                    taskList.add(new DepositToBank(ctx, location, ores));
                }
                mainFrame.dispose();
            }
        });
    }

}
