package com.ryno.rfruitstalls;

import com.ryno.rfruitstalls.tasks.*;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

import java.util.ArrayList;

public class Util {
    public static boolean playerAtStalls() {
        return Constants.GENERAL_STALL_AREA.contains(Players.local().tile());
    }

    public static boolean inventoryContainsFruit() {
        return Inventory.stream().filter(i -> Constants.FRUIT_STRINGS.contains(i.name())).isNotEmpty();
    }

    public static void setupTasks(ArrayList<Task> taskList) {
        taskList.add(new WalkToStalls("Walking to stalls"));
        taskList.add(new EnableRun("Enabling run"));
        taskList.add(new EatStrangeFruit("Restoring stamina"));
        taskList.add(new StealFruit("Stealing fruit"));
        taskList.add(new DropItems("Dropping fruit"));
    }
}
