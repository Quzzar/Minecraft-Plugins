package com.quzzar.server.souls.character.packets.display;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.PlayerInfoAction;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.google.common.collect.Lists;
import com.mojang.authlib.properties.Property;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.skin.SkinManager;

public class PlayerDisplayHandler {
	
	public PlayerDisplayHandler() {
		
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Souls.instance, PacketType.Play.Server.PLAYER_INFO) {

	        @Override
	        public void onPacketSending(PacketEvent event) {
	            WrapperPlayServerPlayerInfo wrapper = new WrapperPlayServerPlayerInfo(event.getPacket());

	            List<PlayerInfoData> playerInfoDataList = wrapper.getData();

	            if (wrapper.getAction() != PlayerInfoAction.ADD_PLAYER) {
	                return;
	            }

	            List<PlayerInfoData> newPlayerInfoDataList = Lists.newArrayList();

	            for (PlayerInfoData playerInfoData : playerInfoDataList) {
	                Player player;

	                if (playerInfoData == null 
	                		|| playerInfoData.getProfile() == null 
	                		|| (player = Bukkit.getPlayer(playerInfoData.getProfile().getUUID())) == null 
	                		|| !player.isOnline()) {
	                    newPlayerInfoDataList.add(playerInfoData);
	                    continue;
	                }

	                WrappedGameProfile profile = playerInfoData.getProfile();

	                PlayerCharacter pChar = CharacterManager.getCharacter(player);
	                
	                WrappedGameProfile newProfile = profile.withName(pChar.getName());
	                newProfile.getProperties().putAll(profile.getProperties());
	                
	                
	                Property skinTexture = SkinManager.getSkinTexture(pChar.getSkin());
	                newProfile.getProperties().get("textures").clear();
	                newProfile.getProperties().put("textures", WrappedSignedProperty.fromValues(
	                		skinTexture.getName(), skinTexture.getValue(), skinTexture.getSignature()));
	                
	                
	                PlayerInfoData newPlayerInfoData = new PlayerInfoData(newProfile, playerInfoData.getLatency(),
	                		playerInfoData.getGameMode(), playerInfoData.getDisplayName());
	                newPlayerInfoDataList.add(newPlayerInfoData);
	            }

	            wrapper.setData(newPlayerInfoDataList);
	        }
	    });
		
	}
	
}
