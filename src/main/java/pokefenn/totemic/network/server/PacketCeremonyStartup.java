package pokefenn.totemic.network.server;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import pokefenn.totemic.api.music.MusicInstrument;
import pokefenn.totemic.network.CSynchronizedMessageHandler;
import pokefenn.totemic.tileentity.totem.StateStartup;
import pokefenn.totemic.tileentity.totem.TileTotemBase;

public class PacketCeremonyStartup implements IMessage
{
    private BlockPos pos;
    private int startupTime = 0;
    private String[] instruments = null;
    private int[] values = null;

    public PacketCeremonyStartup(BlockPos pos, Object2IntMap<MusicInstrument> music, int startupTime)
    {
        this.pos = pos;
        this.startupTime = startupTime;
        this.instruments = music.keySet().stream()
                .map(instr -> instr.getRegistryName().toString())
                .toArray(String[]::new);
        this.values = music.values().toIntArray();
    }

    public PacketCeremonyStartup() {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        startupTime = buf.readInt();

        int len = buf.readByte();
        instruments = new String[len];
        values = new int[len];
        for(int i = 0; i < len; i++)
        {
            instruments[i] = ByteBufUtils.readUTF8String(buf);
            values[i] = buf.readShort();
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(startupTime);

        buf.writeByte(instruments.length);
        for(int i = 0; i < instruments.length; i++)
        {
            ByteBufUtils.writeUTF8String(buf, instruments[i]);
            buf.writeShort(values[i]);
        }
    }

    public static class Handler extends CSynchronizedMessageHandler<PacketCeremonyStartup>
    {
        @Override
        protected void handleClient(PacketCeremonyStartup msg)
        {
            TileEntity tile = Minecraft.getMinecraft().world.getTileEntity(msg.pos);
            if(tile instanceof TileTotemBase)
            {
                TileTotemBase totem = (TileTotemBase) tile;
                if(totem.getState() instanceof StateStartup)
                {
                    StateStartup state = (StateStartup) totem.getState();

                    state.setTime(msg.startupTime);
                    state.setMusic(msg.instruments, msg.values);
                }
            }
        }
    }
}
