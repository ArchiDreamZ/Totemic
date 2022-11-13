package pokefenn.totemic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import pokefenn.totemic.advancements.ModCriteriaTriggers;
import pokefenn.totemic.api.TotemicAPI;
import pokefenn.totemic.apiimpl.registry.RegistryApiImpl;
import pokefenn.totemic.client.ModModelLayers;
import pokefenn.totemic.data.TotemicBlockStateProvider;
import pokefenn.totemic.data.TotemicBlockTagsProvider;
import pokefenn.totemic.data.TotemicItemTagsProvider;
import pokefenn.totemic.data.TotemicLootTableProvider;
import pokefenn.totemic.data.TotemicRecipeProvider;
import pokefenn.totemic.handler.ClientInitHandlers;
import pokefenn.totemic.handler.ClientInteract;
import pokefenn.totemic.handler.EntityHandler;
import pokefenn.totemic.handler.PlayerInteract;
import pokefenn.totemic.init.ModBlockEntities;
import pokefenn.totemic.init.ModBlocks;
import pokefenn.totemic.init.ModContent;
import pokefenn.totemic.init.ModEntityTypes;
import pokefenn.totemic.init.ModItems;
import pokefenn.totemic.init.ModMobEffects;
import pokefenn.totemic.init.ModSounds;
import pokefenn.totemic.network.NetworkHandler;

@Mod(TotemicAPI.MOD_ID)
public final class Totemic {
    public static final Logger logger = LogManager.getLogger(Totemic.class);

    public static final CreativeModeTab creativeTab = new CreativeModeTab(TotemicAPI.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.totemic_staff.get());
        }
    };

    public Totemic() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::gatherData);

        ModBlocks.REGISTER.register(modBus);
        ModItems.REGISTER.register(modBus);
        ModMobEffects.REGISTER.register(modBus);
        ModBlockEntities.REGISTER.register(modBus);
        ModEntityTypes.REGISTER.register(modBus);
        ModSounds.REGISTER.register(modBus);

        modBus.register(ModBlocks.class);
        modBus.register(ModItems.class);
        modBus.register(ModContent.class);
        modBus.register(ModEntityTypes.class);

        if(FMLEnvironment.dist.isClient()) {
            modBus.addListener(this::clientSetup);

            modBus.register(ClientInitHandlers.class);
            modBus.register(ModModelLayers.class);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.setFireInfo();
            ModCriteriaTriggers.init();
            ModItems.baykok_bow.get().registerItemProperties();
        });

        //Totem effects are registered during ModBlocks#init
        RegistryApiImpl.registerInstruments();
        RegistryApiImpl.registerCeremonies();
        RegistryApiImpl.freezeRegistries();

        NetworkHandler.init();

        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.register(PlayerInteract.class);
        eventBus.register(EntityHandler.class);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        IEventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.register(ClientInteract.class);
    }

    private void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        if(event.includeServer()) {
            var blockTP = new TotemicBlockTagsProvider(gen, event.getExistingFileHelper());
            gen.addProvider(true, blockTP);
            gen.addProvider(true, new TotemicItemTagsProvider(gen, blockTP, TotemicAPI.MOD_ID, event.getExistingFileHelper()));
            gen.addProvider(true, new TotemicLootTableProvider(gen));
            gen.addProvider(true, new TotemicRecipeProvider(gen));
        }
        if(event.includeClient()) {
            gen.addProvider(true, new TotemicBlockStateProvider(gen, event.getExistingFileHelper()));
        }
    }
}
