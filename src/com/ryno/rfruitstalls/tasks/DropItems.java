package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Constants;
import com.ryno.rfruitstalls.Task;
import com.ryno.rfruitstalls.Util;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;

public class DropItems extends Task {
    public DropItems(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Inventory.isFull() && Util.inventoryContainsFruit();
    }

    @Override
    public boolean execute() {
        // Get all fruit in the inventory & drop them
        if (!Inventory.opened()) {
            Game.tab(Game.Tab.INVENTORY);
        }
        return Inventory.drop(Inventory.stream().filter(i -> Constants.FRUIT_STRINGS.contains(i.name())).list());
    }
}
