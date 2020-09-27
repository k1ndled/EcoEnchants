package com.willfp.ecoenchants.display.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.willfp.ecoenchants.display.AbstractPacketAdapter;
import com.willfp.ecoenchants.display.EnchantDisplay;
import org.bukkit.inventory.ItemFlag;

public final class PacketWindowItems extends AbstractPacketAdapter {
    public PacketWindowItems() {
        super(PacketType.Play.Server.WINDOW_ITEMS);
    }

    @Override
    public void onSend(PacketContainer packet) {
        packet.getItemListModifier().modify(0, (itemStacks) -> {
            itemStacks.forEach(item -> {
                boolean hideEnchants = false;

                if(item != null && item.getItemMeta() != null) {
                    hideEnchants = item.getItemMeta().getItemFlags().contains(ItemFlag.HIDE_ENCHANTS);
                }

                EnchantDisplay.displayEnchantments(item, hideEnchants);
            });
            return itemStacks;
        });
    }
}