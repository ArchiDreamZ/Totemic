package totemic_commons.pokefenn.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import totemic_commons.pokefenn.ModBlocks;
import totemic_commons.pokefenn.ModItems;
import totemic_commons.pokefenn.item.ItemTotemicItems;

public class CraftingRecipes
{

    public static void init()
    {
        oreDictionary();
        shapelessRecipes();
        shapedRecipes();
        furnaceRecipes();
    }

    static void shapedRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.totemicStaff,
                " ls", " s ", "s l", ('s'), "stickWood", ('l'), "treeLeaves"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.totemWhittlingKnife,
                "  i", " sf", "s  ", ('s'), "stickWood", ('i'), "ingotIron", ('f'), Items.flint));

        //Generic
        GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.totemTorch,
                "sfs", "sws", " s ", ('s'), "stickWood", ('w'), "logWood", ('f'), Blocks.torch));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.barkStripper,
                "iii", "s s", "s s", ('i'), "ingotIron", ('s'), "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.totempedia,
                "bpb", "bpb", "bpb", ('b'), "logWood", ('p'), Items.paper));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.subItems, 3, ItemTotemicItems.bellsIron),
                " n ", "nnn", " n ", ('n'), "nuggetIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(Items.iron_ingot,
                "nnn", "nnn", "nnn", ('n'), "nuggetIron"));
        GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.tipi,
                " s ", "sws", "w w", ('s'), "stickWood", ('w'), Blocks.wool));

        //Music
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.drum, 1, 0),
                "eee", "lwl", "wlw", ('e'), Items.leather, ('l'), "logWood", ('w'), Blocks.wool));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.windChime, 1, 0),
                "iwi", "tst", "  t", ('i'), "ingotIron", ('s'), Items.string, ('w'), "logWood", ('t'), "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.flute, 1, 0),
                " sc", " c ", "c  ", ('c'), "stickWood", ('s'), "treeLeaves"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.ceremonialRattle, 1, 0),
                " ww", " bw", "s  ", ('s'), "stickWood", ('w'), "logWood", ('b'), Items.bone));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.jingleDress, 1, 0),
                " l ", "blb", "lbl", ('l'), "treeLeaves", ('b'), "bellsIron"));
    }

    static void shapelessRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.redCedarPlank, 5, 0), ModBlocks.redCedarStripped);
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.redCedarPlank, 3, 0), ModBlocks.cedarLog);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.subItems, 9, ItemTotemicItems.nuggetIron), Items.iron_ingot);

        //Music
    }

    static void oreDictionary()
    {
        OreDictionary.registerOre("cropVine", new ItemStack(Blocks.vine));
        OreDictionary.registerOre("treeLeaves", new ItemStack(ModBlocks.totemLeaves, 1));
        OreDictionary.registerOre("logWood", new ItemStack(ModBlocks.cedarLog, 1, 0));
        OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.redCedarPlank, 1, 0));
        OreDictionary.registerOre("nuggetIron", new ItemStack(ModItems.subItems, 1, ItemTotemicItems.nuggetIron));
        OreDictionary.registerOre("bellsIron", new ItemStack(ModItems.subItems, 1, ItemTotemicItems.bellsIron));
    }

    static void furnaceRecipes()
    {
        GameRegistry.addSmelting(ModBlocks.redCedarStripped, new ItemStack(Items.coal, 1, 1), 0.5F);
        GameRegistry.addSmelting(ModBlocks.cedarLog, new ItemStack(Items.coal, 1, 1), 0.5F);
    }

}
