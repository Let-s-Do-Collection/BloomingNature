package satisfy.bloomingnature.item;

import de.cristelknight.doapi.common.item.StandardItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import satisfy.bloomingnature.util.BloomingNatureIdentifier;

import java.util.List;

public class BloomingNatureStandardItem extends StandardItem {
    public BloomingNatureStandardItem(Properties properties) {
        super(properties, BloomingNatureIdentifier.of("textures/standard/bloomingnature_standard.png"), () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, true, false, true));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("tooltip.bloomingnature.thankyou_1").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.bloomingnature.thankyou_2").withStyle(ChatFormatting.DARK_PURPLE));
        tooltip.add(Component.translatable("tooltip.bloomingnature.thankyou_4").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("tooltip.bloomingnature.thankyou_3").withStyle(ChatFormatting.GOLD));
    }
}