package knightminer.animalcrops;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import knightminer.animalcrops.blocks.BlockAnimalCrops;
import knightminer.animalcrops.core.CommonProxy;
import knightminer.animalcrops.core.Config;
import knightminer.animalcrops.items.ItemAnimalSeeds;
import knightminer.animalcrops.tileentity.TileAnimalCrops;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = AnimalCrops.modID, version = AnimalCrops.version, name = AnimalCrops.name, dependencies =
		"required-after:forge@[14.23.1.2594,);" )
public class AnimalCrops {
	public static final String name = "AnimalCrops";
	public static final String modID = "animalcrops";
	public static final String version = "${version}";

	@SidedProxy(clientSide = "knightminer.animalcrops.core.ClientProxy", serverSide = "knightminer.animalcrops.core.CommonProxy")
	public static CommonProxy proxy;

	public static Block crops;
	public static ItemAnimalSeeds seeds;

	public static final Logger log = LogManager.getLogger(modID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.preInit(event);

		proxy.preInit();
	}

	@Mod.EventBusSubscriber(modid=modID)
	public static class Registration {
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			crops = new BlockAnimalCrops();
			crops.setRegistryName(new ResourceLocation(modID, "crops"));
			crops.setUnlocalizedName(modID + ".crops");
			event.getRegistry().register(crops);

			GameRegistry.registerTileEntity(TileAnimalCrops.class, modID + ":crops");
		}

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			seeds = new ItemAnimalSeeds(crops);
			seeds.setRegistryName(new ResourceLocation(modID, "seeds"));
			seeds.setUnlocalizedName(modID + ".seeds");
			event.getRegistry().register(seeds);
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Config.init(event);
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
}