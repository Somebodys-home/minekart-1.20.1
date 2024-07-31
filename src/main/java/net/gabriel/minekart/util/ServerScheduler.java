package net.gabriel.minekart.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import java.util.HashMap;
import java.util.Map;

public class ServerScheduler {

    static final HashMap<Runnable, Integer> ENTRIES = new HashMap<>();

    public static void tick(){
        ENTRIES.replaceAll((task, ticksLeft) -> ticksLeft - 1);
        ENTRIES.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .map(Map.Entry::getKey).forEach(Runnable::run);
        ENTRIES.entrySet().removeIf(entry -> entry.getValue() == 0);
    }

    public static void runAll(){
        ENTRIES.keySet().forEach(Runnable::run);
        ENTRIES.clear();
    }

    public static void schedule(Runnable task, int tickDelay){
        ENTRIES.put(task, tickDelay);
    }
}