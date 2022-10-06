package com.ryno.rfruitstalls;

import org.powbot.api.event.TickEvent;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackSkillOption;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;
import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;

@ScriptManifest(name = "rFruitStalls", description = "Trains thieving at Hosidius fruit stalls.",
        version = "0.1.0", category = ScriptCategory.Thieving)
//@ScriptConfiguration.List(
//        {
//                @ScriptConfiguration(name = "String Bows",
//                        description = "String Bows",
//                        optionType = OptionType.BOOLEAN)
//        }
//)

public class Script extends AbstractScript {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private Task currentTask = null;
    ErrorManager errorManager = new ErrorManager(10, 1, 0.01);

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("rFruitStalls", "", "127.0.0.1:5555", true, false);
    }

    @Override
    public void onStart() {
        Util.setupTasks(taskList);

        Paint paint = PaintBuilder.newBuilder()
                .x(40)
                .y(45)
                .addString("Status: ", () -> {
                    if (currentTask == null) return "Setting up";
                    return currentTask.name;
                })
                .addString("Error Rate: ", () -> String.format("%.2f", errorManager.getErrorScore()))
                .trackSkill(Skill.Thieving, TrackSkillOption.Exp)
                .build();

        addPaint(paint);
    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            System.out.println("[DEBUG] Checking task " + task.name);
            if (task.activate()) {
                currentTask = task;
                System.out.println("[DEBUG] Current task: " + currentTask.name);

                if (!task.execute()) {
                    errorManager.incrementErrorScore();

                    if (errorManager.checkForThreshold()) {
                        System.out.println("[DEBUG] Stopping due to error count.");
                        ScriptManager.INSTANCE.stop();
                    }
                }

                if (ScriptManager.INSTANCE.isStopping()) {
                    System.out.println("[DEBUG] Stopping because I was told to.");
                    break;
                }
            }
        }
    }

    @Subscribe
    public void onTick(TickEvent tickEvent) {
        errorManager.decrementErrorScore();
    }
}
