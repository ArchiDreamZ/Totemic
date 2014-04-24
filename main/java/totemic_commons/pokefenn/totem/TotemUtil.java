package totemic_commons.pokefenn.totem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import totemic_commons.pokefenn.ModItems;
import totemic_commons.pokefenn.item.armour.ItemTotemArmour;
import totemic_commons.pokefenn.lib.Totems;

/**
 * Created with IntelliJ IDEA.
 * User: Pokefenn
 * Date: 06/02/14
 * Time: 17:07
 */
public class TotemUtil
{


    public static int getArmourAmounts(EntityPlayer player)
    {
        int j = 0;
        for(int i = 0; i < 4; i++)
            if(player.inventory.armorItemInSlot(i) != null)
                if(player.inventory.armorItemInSlot(i).getItem() instanceof ItemTotemArmour)
                    j++;

        return j;
    }

    public static int getStrength(EntityPlayer player)
    {
        return getArmourAmounts(player) > 2 ? 1 : 0;
    }

    public static int decrementAmount(int par1)
    {
        if(par1 == 1)
            return Totems.DECREMENT_CACTUS;
        else if(par1 == 2)
            return Totems.DECREMENT_HORSE;
        else if(par1 == 3)
            return Totems.DECREMENT_HOPPER;
        else if(par1 == 4)
            return Totems.DECREMENT_BAT;
        else if(par1 == 5)
            return Totems.DECREMENT_SUN;
        else if(par1 == 6)
            return Totems.DECREMENT_BLAZE;
        else if(par1 == 7)
            return Totems.DECREMENT_OCELOT;
        else if(par1 == 8)
            return Totems.DECREMENT_SQUID;
        else if(par1 == 9)
            return Totems.DECREMENT_FOOD;
        else if(par1 == 10)
            return Totems.DECREMENT_LOVE;
        else if(par1 == 11)
            return 0;
        else if(par1 == 12)
            return Totems.DECREMENT_MINING;
        else
            return 0;
    }
}
