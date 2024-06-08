package rus.iglo.storycraft.story.action;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import rus.iglo.storycraft.StoryCraftTing;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import static rus.iglo.storycraft.StoryCraftTing.DRINKING_KEY;

@Mod.EventBusSubscriber(modid = StoryCraftTing.MODID)
public class Actions {
    private static final Queue<DelayedTask> taskQueue = new ConcurrentLinkedQueue<>();
    public static void scheduleDelayedTask(Runnable action, long delay, TimeUnit timeUnit) {
        long delayTicks = timeUnit.toSeconds(delay) * 20;
        taskQueue.add(new DelayedTask(action, delayTicks));
    }
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            DelayedTask currentTask = taskQueue.peek();
            if (currentTask != null) {
                currentTask.tick();
                if (currentTask.isCompleted()) {
                    currentTask.run();
                    taskQueue.poll();
                }
            }
        }
    }
    private static class DelayedTask {
        private final Runnable action;
        private long delayTicks;

        public DelayedTask(Runnable action, long delayTicks) {
            this.action = action;
            this.delayTicks = delayTicks;
        }

        public void tick() {
            delayTicks--;
        }

        public boolean isCompleted() {
            return delayTicks <= 0;
        }

        public void run() {
            action.run();
        }
    }
    public void addScenetimer(Runnable runnable,int timer) {
        Actions.scheduleDelayedTask(runnable,timer,TimeUnit.SECONDS);
    }
    public void playScene(Runnable runnable) {
        Actions.scheduleDelayedTask(runnable,1,TimeUnit.MILLISECONDS);
    }

    static List<Runnable> list = new ArrayList<>();
    private static int currentIndex = 0;

    @SubscribeEvent
    public static void test(InputEvent.Key inputEvent) {
        if (DRINKING_KEY.consumeClick()) {
            if (currentIndex < list.size()) {
                Runnable currentRunnable = list.get(currentIndex);
                currentRunnable.run();
                list.remove(currentIndex);
                currentIndex++;
            } else {
                currentIndex = 0;
            }
        }
    }

    public void addSceneKey(Runnable runnable) {
        list.add(runnable);
    }
}