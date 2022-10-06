package com.ryno.rfruitstalls.tasks;

import com.ryno.rfruitstalls.Constants;
import com.ryno.rfruitstalls.Task;
import com.ryno.rfruitstalls.Util;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.InteractionType;
import org.powbot.api.rt4.walking.model.Skill;

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
        System.out.println("[DEBUG] Streaming for stalls");
        long start = System.nanoTime();
        GameObject stall = Objects.stream().within(8).id(Constants.INTERACTABLE_STALL_ID).first();
        long end = System.nanoTime();
        System.out.println("[DEBUG] Total time: " + (end - start) / 1000000);

        int xp = Skill.Thieving.experience();
        if (stall.valid()) {
            System.out.println("[DEBUG] Checking for stall in viewport");
            start = System.nanoTime();
            if (!stall.inViewport()) {
                Camera.turnTo(stall);
            }
            end = System.nanoTime();
            System.out.println("[DEBUG] Total time: " + (end - start) / 1000000);

            System.out.println("[DEBUG] Interacting with stall");
            start = System.nanoTime();
            stall.click("Steal-from");
            end = System.nanoTime();
            System.out.println("[DEBUG] Total time: " + (end - start) / 1000000);

            System.out.println("[DEBUG] Waiting for stall to be empty");
            start = System.nanoTime();
            boolean status = Condition.wait(() -> xp < Skill.Thieving.experience(), 50, 100);
            end = System.nanoTime();
            System.out.println("[DEBUG] Total time: " + (end - start) / 1000000);

            return status;
        }
        return false;
    }
}
