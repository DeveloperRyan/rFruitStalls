package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Constants;
import com.ryno.rfruitstalls.Task;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.WebWalkingResult;

public class WalkToStalls extends Task {
    public WalkToStalls(String name) {
        super(name);
    }

    @Override
    public boolean activate() {
        return !Constants.STALL_AREA.contains(Players.local().tile());
    }

    @Override
    public boolean execute() {
        WebWalkingResult status = Movement.builder(Constants.STALL_AREA.getRandomTile()).setRunMin(20).setRunMax(70).move();
        return status.getSuccess();
    }
}
