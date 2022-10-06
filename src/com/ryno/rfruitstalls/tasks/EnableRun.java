package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Task;
import com.ryno.rfruitstalls.Util;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class EnableRun extends Task {
    public EnableRun(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return !Movement.running() && Util.playerAtStalls();
    }

    @Override
    public boolean execute() {
        Movement.running(true);

        return Condition.wait(Movement::running, 50, 10);
    }
}
