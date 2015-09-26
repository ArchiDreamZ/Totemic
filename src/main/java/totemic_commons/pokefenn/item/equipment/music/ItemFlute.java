package totemic_commons.pokefenn.item.equipment.music;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.network.PacketHandler;
import totemic_commons.pokefenn.network.client.PacketSound;
import totemic_commons.pokefenn.recipe.HandlerInitiation;
import totemic_commons.pokefenn.util.EntityUtil;
import totemic_commons.pokefenn.util.TotemUtil;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class ItemFlute extends ItemMusic
{
    public int time;

    public ItemFlute()
    {
        super(Strings.FLUTE_NAME, HandlerInitiation.flute);
        setMaxStackSize(1);
        time = 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {
            int x = (int) player.posX;
            int y = (int) player.posY;
            int z = (int) player.posZ;

            time++;
            if(time >= 5 && !player.isSneaking())
            {
                int bonusMusic = (itemStack.getItemDamage() == 1) ? 2 : 0;
                time = 0;
                TotemUtil.playMusicFromItem(world, (int) player.posX, (int) player.posY, (int) player.posZ, HandlerInitiation.flute, 0, bonusMusic);
                particlesAllAround((WorldServer)world, player.posX, player.posY, player.posZ, false);
                PacketHandler.sendAround(new PacketSound(x, y, z, "flute"), player.worldObj.provider.dimensionId, x, y, z);
            }
            if(time >= 5 && player.isSneaking())
            {
                time = 0;
                TotemUtil.playMusicFromItemForCeremonySelector(player, (int) player.posX, (int) player.posY, (int) player.posZ, musicHandler, 0);
                particlesAllAround((WorldServer)world, player.posX, player.posY, player.posZ, true);
                PacketHandler.sendAround(new PacketSound(x, y, z, "flute"), player.worldObj.provider.dimensionId, x, y, z);
                return itemStack;
            }
            if(itemStack.getItemDamage() == 1)
                for(Entity entity : EntityUtil.getEntitiesInRange(world, player.posX, player.posY, player.posZ, 2, 2))
                {
                    if(entity instanceof EntityAnimal || entity instanceof EntityVillager)
                    {
                        if(entity instanceof EntityAnimal)
                            ((EntityAnimal) entity).targetTasks.addTask(5, new EntityAITempt((EntityCreature) entity, 1, this, false));
                        if(entity instanceof EntityVillager)
                            ((EntityVillager) entity).targetTasks.addTask(5, new EntityAITempt((EntityCreature) entity, 0.5, this, false));
                    }

                }
        }
        return itemStack;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if(stack.getItemDamage() == 1)
            return "item.totemic:fluteInfused";
        else
            return "item.totemic:flute";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return stack.getItemDamage() == 1;
    }

    /*
    @Override
    public int getMaximumMusic(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 90;
    }

    @Override
    public int getMusicOutput(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 5;
    }

    @Override
    public int getRange(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 7;
    }
    */

    public void particlesAllAround(WorldServer world, double x, double y, double z, boolean firework)
    {
        world.func_147487_a("note", x + 0.5D, y + 1.2D, z + 0.5D, 2, 0.5D, 0.0D, 0.5D, 0.0D);
        world.func_147487_a("note", x, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        world.func_147487_a("note", x + 0.5D, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        world.func_147487_a("note", x, y + 1.2D, z + 0.5D, 2, 0.0D, 0.0D, 0.0D, 0.0D);

        if(firework)
        {
            world.func_147487_a("fireworksSpark", x + 0.5D, y + 1.2D, z + 0.5D, 2, 0.5D, 0.0D, 0.5D, 0.0D);
            world.func_147487_a("fireworksSpark", x, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
            world.func_147487_a("fireworksSpark", x + 0.5D, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
            world.func_147487_a("fireworksSpark", x, y + 1.2D, z + 0.5D, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }


}
