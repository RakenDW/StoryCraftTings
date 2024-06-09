package rus.iglo.storycraft.story.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rus.iglo.storycraft.StoryCraftTing;
import rus.iglo.storycraft.story.npc.ModEntityTypes;
import rus.iglo.storycraft.story.npc.custom.NPCEntity;
import rus.iglo.storycraft.story.npcc.NPCEntityEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static rus.iglo.storycraft.StoryCraftTing.playerall;
import static rus.iglo.storycraft.story.gui.GuiMeneg.DialogScreen.entityss;

public class GuiMeneg {
    public GuiMeneg(String n) {
        DialogScreen.setNameVopros(n);
    }
    public void open() {
        DialogScreen.OpenDialog.execute();
    }
    public void addButton1(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet1(name,() -> {
            runnable.run();
        });
    }
    public void addButton2(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet2(name,() -> {
            runnable.run();
        });
    }
    public void addButton3(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet3(name,() -> {
            runnable.run();
        });
    }
    public void addButton4(String name,Runnable runnable) {
        DialogScreen.addButtonOtvet4(name,() -> {
            runnable.run();
        });
    }
    public void reset() {
        DialogScreen.reset();
    }
    public void setNameVopros(String name) {
        DialogScreen.setNameVopros(name);
    }
    public void setEntity(NPCEntityEntity entity) {
        entityss = entity;
    }

    private GuiMeneg previousGuiMeneg;
    public void setPreviousGuiMeneg(GuiMeneg previousGuiMeneg) {
        this.previousGuiMeneg = previousGuiMeneg;
    }

    public static class DialogScreen extends AbstractContainerScreen<DialogScreen.DialogMenu> {
        private final static HashMap<String, Object> guistate = DialogMenu.guistate;
        private final Level world;
        private static Entity mob;
        private final int x, y, z;
        private final Player entity;
        ImageButton imagebutton_knopka;
        ImageButton imagebutton_knopka1;
        ImageButton imagebutton_knopka2;
        ImageButton imagebutton_knopka3;


        public DialogScreen(DialogMenu container, Inventory inventory, Component text) {
            super(container, inventory, text);
            this.world = container.world;
            this.x = container.x;
            this.y = container.y;
            this.z = container.z;
            this.entity = container.entity;
            this.imageWidth = 0;
            this.imageHeight = 166;
        }

        public static NPCEntityEntity entityss = new NPCEntityEntity();
        static NPCEntity npcEntity = new NPCEntity(ModEntityTypes.NPC.get(),playerall.level);
        public static int ttttt = 0;

        @Override
        public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
            this.renderBackground(ms);
            super.render(ms, mouseX, mouseY, partialTicks);
            this.renderTooltip(ms, mouseX, mouseY);
            if (ttttt == 1) {
                if (execute(entityss) == entityss) {
                    InventoryScreen.renderEntityInInventoryRaw(this.leftPos + -178, this.topPos + 136, 60, 0f + (float) Math.atan((this.leftPos + -178 - mouseX) / 40.0), (float) Math.atan((this.topPos + 86 - mouseY) / 40.0), npcEntity);
                }
            }
            if (ttttt == 0) {
                if (execute(entityss) == entityss) {
                    InventoryScreen.renderEntityInInventoryRaw(this.leftPos + -178, this.topPos + 136, 60, 0f + (float) Math.atan((this.leftPos + -178 - mouseX) / 40.0), (float) Math.atan((this.topPos + 86 - mouseY) / 40.0), entity);
                }
            }
        }
        public NPCEntityEntity execute(NPCEntityEntity entity) {
            entityss = entity;
            return entity;
        }
        @Override
        protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            RenderSystem.setShaderTexture(0, new ResourceLocation("storycraftting:textures/gui/okno.png"));
            this.blit(ms, this.leftPos + -226, this.topPos + -45, 0, 0, 454, 256, 454, 256);

            RenderSystem.setShaderTexture(0, new ResourceLocation("storycraftting:textures/gui/vop.png"));
            this.blit(ms, this.leftPos + -108, this.topPos + -29, 0, 0, 205, 65, 205, 65);

            RenderSystem.disableBlend();
        }
        @Override
        public boolean keyPressed(int key, int b, int c) {
            if (key == 256) {
                this.minecraft.player.closeContainer();
                return true;
            }
            return super.keyPressed(key, b, c);
        }

        @Override
        public void containerTick() {
            super.containerTick();
        }
        public static String otvet1name = "ответ1";
        public static String otvet2name = "ответ2";
        public static String otvet3name = "ответ3";
        public static String otvet4name = "ответ4";
        public static String voprosname = "вопрос";
        @Override
        protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
            this.font.draw(poseStack, Component.translatable(otvet1name), -otvet1, 61, -1);
            this.font.draw(poseStack, Component.translatable(otvet2name), -otvet2, 97, -1);
            this.font.draw(poseStack, Component.translatable(otvet3name), -otvet3, 133, -1);
            this.font.draw(poseStack, Component.translatable(otvet4name), -otvet4, 169, -1);
            this.font.draw(poseStack, Component.translatable(voprosname), -99, -20, -1);
        }

        @Override
        public void onClose() {
            super.onClose();
            Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
        }
        static List<Runnable> list1 = new ArrayList<>();
        public static int otvet1 = 8888;
        public static int ovvettext1 = -8888;
        public static void addButtonOtvet1(String otvet2names, Runnable runnable) {
            otvet1 = 90;
            ovvettext1 = -99;
            otvet1name = otvet2names;
            list1.add(runnable);
        }

        @Override
        public void init() {
            super.init();
            this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
            imagebutton_knopka = new ImageButton(this.leftPos + ovvettext1, this.topPos + 52, 185, 25, 0, 0, 25, new ResourceLocation("storycraftting:textures/gui/knopka.png"), 185, 50, e -> {
                if (true) {
                    StoryCraftTing.SIMPLE_CHANNEL.sendToServer(new DialogMenu.DialogButtonMessage(0, x, y, z));
                    if (!list1.isEmpty()) {
                        Runnable runnable = list1.get(0);
                        entity.closeContainer();
                        runnable.run(); // Вызываем Runnable
                    }
                }
            });
            guistate.put("knopka", imagebutton_knopka);
            this.addRenderableWidget(imagebutton_knopka);
            imagebutton_knopka1 = new ImageButton(this.leftPos + ovvettext2, this.topPos + 88, 185, 25, 0, 0, 25, new ResourceLocation("storycraftting:textures/gui/knopka.png"), 185, 50, e -> {
                if (!list2.isEmpty()) {
                    Runnable runnable = list2.get(0); // Получаем первый Runnable из списка
                    entity.closeContainer();
                    runnable.run(); // Вызываем Runnable
                }
            });
            guistate.put("nopka1", imagebutton_knopka1);
            this.addRenderableWidget(imagebutton_knopka1);
            imagebutton_knopka2 = new ImageButton(this.leftPos + ovvettext3, this.topPos + 124, 185, 25, 0, 0, 25, new ResourceLocation("storycraftting:textures/gui/knopka.png"), 185, 50, e -> {
                if (!list2.isEmpty()) {
                    Runnable runnable = list3.get(0); // Получаем первый Runnable из списка
                    entity.closeContainer();
                    runnable.run(); // Вызываем Runnable
                }
            });
            guistate.put("knopka2", imagebutton_knopka2);
            this.addRenderableWidget(imagebutton_knopka2);
            imagebutton_knopka3 = new ImageButton(this.leftPos + ovvettext4, this.topPos + 160, 185, 25, 0, 0, 25, new ResourceLocation("storycraftting:textures/gui/knopka.png"), 185, 50, e -> {
                if (!list2.isEmpty()) {
                    Runnable runnable = list4.get(0); // Получаем первый Runnable из списка
                    entity.closeContainer();
                    runnable.run(); // Вызываем Runnable
                }
            });
            guistate.put("knopka3", imagebutton_knopka3);
            this.addRenderableWidget(imagebutton_knopka3);
        }
        static List<Runnable> list2 = new ArrayList<>();
        public static int otvet2 = 8888;
        public static int ovvettext2 = -8888;
        public static void addButtonOtvet2(String otvet1names,Runnable runnable) {
            otvet2 = 90;
            ovvettext2 = -99;
            otvet2name = otvet1names;
            list2.add(runnable);
        }
        static List<Runnable> list3 = new ArrayList<>();
        public static int otvet3 = 8888;
        public static int ovvettext3 = -8888;
        public static void addButtonOtvet3(String otvet2names,Runnable runnable) {
            otvet3 = 90;
            ovvettext3 = -99;
            otvet3name = otvet2names;
            list3.add(runnable);
        }
        static List<Runnable> list4 = new ArrayList<>();
        public static int otvet4 = 8888;
        public static int ovvettext4 = -8888;
        public static void addButtonOtvet4(String otvet2names,Runnable runnable) {
            otvet4 = 90;
            ovvettext4 = -99;
            otvet4name = otvet2names;
            list4.add(runnable);
        }
        public static void setNameVopros(String voprosnames) {
            voprosname = voprosnames;
        }
        public static void reset() {
            otvet4 = 8888;
            ovvettext4 = -8888;
            otvet3 = 8888;
            ovvettext3 = -8888;
            otvet1 = 8888;
            ovvettext1 = -8888;
            otvet2 = 8888;
            ovvettext2 = -8888;
        }

        public static class DialogMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
            public final static HashMap<String, Object> guistate = new HashMap<>();
            public final Level world;
            public final Player entity;
            public int x, y, z;
            private IItemHandler internal;
            private final Map<Integer, Slot> customSlots = new HashMap<>();
            private boolean bound = false;

            public DialogMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
                super(DialogMenu.DIALOG.get(), id);
                this.entity = inv.player;
                this.world = inv.player.level;
                this.internal = new ItemStackHandler(0);
            }
            public boolean stillValid(Player player) {
                return true;
            }

            public ItemStack quickMoveStack(Player playerIn, int index) {
                return ItemStack.EMPTY;
            }

            public Map<Integer, Slot> get() {
                return customSlots;
            }
            public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, StoryCraftTing.MODID);
            public static final RegistryObject<MenuType<DialogMenu>> DIALOG = REGISTRY.register("dialog", () -> IForgeMenuType.create(DialogMenu::new));

            @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
            public static class DialogButtonMessage {

                private final int buttonID, x, y, z;

                public DialogButtonMessage(FriendlyByteBuf buffer) {
                    this.buttonID = buffer.readInt();
                    this.x = buffer.readInt();
                    this.y = buffer.readInt();
                    this.z = buffer.readInt();
                }

                public DialogButtonMessage(int buttonID, int x, int y, int z) {
                    this.buttonID = buttonID;
                    this.x = x;
                    this.y = y;
                    this.z = z;
                }

                public static void buffer(DialogButtonMessage message, FriendlyByteBuf buffer) {
                    buffer.writeInt(message.buttonID);
                    buffer.writeInt(message.x);
                    buffer.writeInt(message.y);
                    buffer.writeInt(message.z);
                }

                public static void handler(DialogButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
                    NetworkEvent.Context context = contextSupplier.get();
                    context.enqueueWork(() -> {
                        Player entity = context.getSender();
                        int buttonID = message.buttonID;

                        handleButtonAction(entity, buttonID);
                    });
                    context.setPacketHandled(true);
                }

                public static void handleButtonAction(Player entity, int buttonID) {
                }
                @SubscribeEvent
                public static void registerMessage(FMLCommonSetupEvent event) {
                    StoryCraftTing.addNetworkMessage(DialogButtonMessage.class, DialogButtonMessage::buffer, DialogButtonMessage::new, DialogButtonMessage::handler);
                }
            }
        }

        public static class OpenDialog {
            public static void execute() {
                BlockPos pos = new BlockPos(0, -60, 0);
                NetworkHooks.openScreen((ServerPlayer) playerall, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Dialog");
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                        return new DialogMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
                    }
                }, pos);
            }
        }

        public static class EntityProcDIalogProcedure {
            public static Entity execute(Entity entity) {
                return entity;
            }
        }
    }
}