package totemic_commons.pokefenn.item.equipment;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class EquipmentMaterials
{
    public static ItemArmor.ArmorMaterial totemArmour = EnumHelper.addArmorMaterial("totemArmour", 100, new int[]{1, 4, 3, 1}, 18);

    public static ItemTool.ToolMaterial huntingKnife = EnumHelper.addToolMaterial("huntingKnife", 0, 126, 0, -2, 0);

    public static ItemTool.ToolMaterial totemSword = EnumHelper.addToolMaterial("totemSword", 0, 125, 0, 4, 20);

    public static ItemArmor.ArmorMaterial bellShoe = EnumHelper.addArmorMaterial("bellShoe", 126, new int[]{0, 0, 2, 0}, 15);

    public static ItemArmor.ArmorMaterial warBonnet = EnumHelper.addArmorMaterial("warBonnet", 256, new int[]{3, 0, 0, 0}, 18);

    public static ItemTool.ToolMaterial tomahawk = EnumHelper.addToolMaterial("tomahawk", 0, 256, 0, 5, 0);
}
