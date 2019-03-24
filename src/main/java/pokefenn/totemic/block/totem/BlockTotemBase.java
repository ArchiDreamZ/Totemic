package pokefenn.totemic.block.totem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.StateContainer.Builder;

public class BlockTotemBase extends BlockDirectional {
    public final String woodType;

    public BlockTotemBase(String woodType, Properties properties) {
        super(properties);
        this.woodType = woodType;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void fillStateContainer(Builder<Block, IBlockState> builder) {
        builder.add(FACING);
    }
}
