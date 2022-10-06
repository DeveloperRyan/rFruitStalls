package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Constants;
import com.ryno.rfruitstalls.Task;
import com.ryno.rfruitstalls.Util;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;

public class StealFruit extends Task {
    public StealFruit(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Util.playerAtStalls() && !Inventory.isFull();
    }

    @Override
    public boolean execute() {
        GameObject stall = Objects.stream().id(Constants.INTERACTABLE_STALL_ID).within(8).first();

        if (!stall.inViewport()) {
            Camera.turnTo(stall);
        }

        if (stall.valid()) {
            stall.interact("Steal-from");

            return Condition.wait(() -> !stall.valid(), 100, 10);
        }
        return false;
    }
}
