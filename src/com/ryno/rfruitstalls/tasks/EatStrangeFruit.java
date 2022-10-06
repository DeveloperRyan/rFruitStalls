package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Constants;
import com.ryno.rfruitstalls.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class EatStrangeFruit extends Task {
    public EatStrangeFruit(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return Movement.energyLevel() < 50 && Inventory.stream().filter(i -> i.name().equals("Strange fruit")).isNotEmpty();
    }

    @Override
    public boolean execute() {
        Item fruit = Inventory.stream().filter(i -> i.name().equals(Constants.STRANGE_FRUIT_STRING)).first();

        if (fruit.valid()) {
            fruit.interact("Eat");
        }

        return Condition.wait(() -> !fruit.valid(), 100, 15);
    }
}
