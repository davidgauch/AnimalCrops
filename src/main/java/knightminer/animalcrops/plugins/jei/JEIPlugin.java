package knightminer.animalcrops.plugins.jei;

import knightminer.animalcrops.AnimalCrops;
import knightminer.animalcrops.core.Utils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.ISubtypeRegistry;
import net.minecraft.util.ResourceLocation;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {

	  @Override
	  public void registerItemSubtypes(ISubtypeRegistry registry) {
		  registry.registerSubtypeInterpreter(AnimalCrops.seeds, (stack) -> {
			  ResourceLocation loc = Utils.getEntityID(stack.getTagCompound());
			  return loc != null ? loc.toString() : "";
		  });
	  }
}