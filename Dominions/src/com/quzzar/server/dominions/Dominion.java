package com.quzzar.server.dominions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.ChunkSettingState;
import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.misc.PermissionCategory;
import com.quzzar.server.dominions.misc.PlayerRank;
import com.quzzar.server.dominions.misc.SimpleLocation;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.dominions.settings.SettingsManager;
import com.quzzar.server.dominions.settings.chunk.ChunkSettingType;
import com.quzzar.server.dominions.upgrades.UpgradeState;
import com.quzzar.server.dominions.upgrades.UpgradeType;
import com.quzzar.server.dominions.upgrades.UpgradesManager;

public class Dominion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Log Maps
	private TreeMap<Date, String> financeLog;
	private TreeMap<Date, String> membersLog;
	private TreeMap<Date, String> diplomacyLog;
	
	private HashMap<UUID, PlayerRank> members;
	private HashMap<UUID, DiplomacyState> dominionDiplomacy;
	private HashMap<UUID, DiplomacyState> playerDiplomacy;
	
	private HashMap<SettingType, PermissionCategory> settings;
	private HashMap<UpgradeType, UpgradeState> upgrades;
	
	private HashMap<EntityType, UUID> persistentEntities;
	
	private Set<String> chunks;
	
	private HashMap<String, HashMap<UUID, HashMap<ChunkSettingType, ChunkSettingState>>> chunkSettings;
	
	private ArrayList<UUID> invites;
	
	private String name;
	private String description;
	private double money;
	
	private SimpleLocation homeLoc = null;
	
	private UUID domUUID;
	
	public Dominion(Player pLeader, String name, String description) {
		
		financeLog = new TreeMap<Date, String>();
		membersLog = new TreeMap<Date, String>();
		diplomacyLog = new TreeMap<Date, String>();
		
		members = new HashMap<UUID, PlayerRank>();
		dominionDiplomacy = new HashMap<UUID, DiplomacyState>();
		playerDiplomacy = new HashMap<UUID, DiplomacyState>();
		
		persistentEntities = new HashMap<EntityType, UUID>();
		
		upgrades = new HashMap<UpgradeType, UpgradeState>();
		UpgradesManager.setDefaults(this);
		
		settings = new HashMap<SettingType, PermissionCategory>();
		SettingsManager.setDefaults(this);
		
		chunks = new HashSet<String>();
		
		chunkSettings = new HashMap<String, HashMap<UUID, HashMap<ChunkSettingType, ChunkSettingState>>>();
		
		invites = new ArrayList<UUID>();
		
		members.put(pLeader.getUniqueId(), PlayerRank.Leader);
		
		this.name = name;
		this.description = description;
		this.money = 150;
		
		this.domUUID = UUID.randomUUID();
		
	}
	
	public void addChunk(Chunk chunk) {
		String packVal = DominionUtils.packChunk(chunk);
		chunks.add(packVal);
		chunkSettings.put(packVal, new HashMap<UUID, HashMap<ChunkSettingType, ChunkSettingState>>());
	}
	
	public void removeChunk(Chunk chunk) {
		String packVal = DominionUtils.packChunk(chunk);
		chunks.remove(packVal);
		chunkSettings.remove(packVal);
	}
	
	public void removeAllChunks() {
		chunks.clear();
		chunkSettings.clear();
	}
	
	public boolean hasClaimed(Chunk chunk) {
		return chunks.contains(DominionUtils.packChunk(chunk));
	}
	
	public int getChunkCount() {
		return chunks.size();
	}
	
	public HashMap<UUID, DiplomacyState> getDominionDiplomacy(){
		return dominionDiplomacy;
	}
	
	public void setDominionDiplomacy(Dominion dominion, DiplomacyState state) {
		if(state.equals(DiplomacyState.Neutral)) {
			dominionDiplomacy.remove(dominion.getUUID());
		} else {
			dominionDiplomacy.put(dominion.getUUID(), state);
		}
	}
	
	public DiplomacyState getDominionDiplomacy(Dominion dominion) {
		if(dominion==this) {
			return DiplomacyState.You;
		}
		
		if(dominion==null) {
			return DiplomacyState.Neutral;
		} else {
			DiplomacyState state = dominionDiplomacy.get(dominion.getUUID());
			if(state==null) {
				return DiplomacyState.Neutral;
			}
			
			return state;
		}
		
	}
	
	public HashMap<UUID, DiplomacyState> getPlayerDiplomacy(){
		return playerDiplomacy;
	}
	
	public void setPlayerDiplomacy(UUID playerUUID, DiplomacyState state) {
		
		Dominion pDom = DominionUtils.getDominionByPlayer(playerUUID);
		DiplomacyState domState = getDominionDiplomacy(pDom);
		
		if(domState.equals(state)) {
			playerDiplomacy.remove(playerUUID);
		} else {
			playerDiplomacy.put(playerUUID, state);
		}
		
	}
	
	public DiplomacyState getPlayerDiplomacy(UUID playerUUID) {
		if(playerUUID==null) {
			return DiplomacyState.Neutral;
		} else {
			DiplomacyState state = playerDiplomacy.get(playerUUID);
			if(state==null) {
				return DiplomacyState.Null;
			}
			return state;
		}
	}
	
	public DiplomacyState getDiplomacy(UUID playerUUID) {
		
		DiplomacyState playerDiplomacy = getPlayerDiplomacy(playerUUID);
		
		if(!playerDiplomacy.equals(DiplomacyState.Null)) {
			return playerDiplomacy;
		} else {
			Dominion pDom = DominionUtils.getDominionByPlayer(playerUUID);
			return getDominionDiplomacy(pDom);
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getMoney() {
		return money;
	}
	
	public UUID getUUID() {
		return domUUID;
	}
	
	public boolean withdrawMoney(double amount, boolean force) {
		if(force) {
			money -= amount;
			return true;
		} else {
			if(money-amount>=0) {
				money -= amount;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void depositMoney(double amount) {
		money+=amount;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public HashMap<UUID, PlayerRank> getMembers(){
		return members;
	}
	
	public int getMaxMembers() {
		return 3+chunks.size();
	}
	
	public int getMemberCount() {
		return members.size();
	}
	
	public boolean addMember(UUID uuid) {
		if(members.size()+1<=getMaxMembers()) {
			members.put(uuid, PlayerRank.Member);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeMember(UUID uuid) {
		if(!members.get(uuid).equals(PlayerRank.Leader)) {
			members.remove(uuid);
			return true;
		} else {
			return false;
		}
	}
	
	public PlayerRank getPlayerRank(UUID uuid) {
		return members.get(uuid);
	}
	
	public void setPlayerRank(UUID uuid, PlayerRank rank) {
		members.put(uuid, rank);
	}
	
	public PermissionCategory getPermissionCategory(UUID playerUUID) {
		
		DiplomacyState state = getDiplomacy(playerUUID);
		if(state.equals(DiplomacyState.You)) {
			
			PlayerRank rank = members.get(playerUUID);
			return PermissionCategory.getFromRank(rank);
			
		} else {
			if(state.equals(DiplomacyState.Ally))
				return PermissionCategory.AllMembersAndAllies;
			
			if(state.equals(DiplomacyState.Enemy))
				return PermissionCategory.Anyone;
			
			return PermissionCategory.AnyoneButEnemies;
		}
	}
	
	public ArrayList<UUID> getInvites(){
		return invites;
	}
	
	public void addInvite(UUID uuid){
		invites.add(uuid);
	}
	
	public void removeInvite(UUID uuid){
		invites.remove(uuid);
	}
	
	public HashMap<SettingType, PermissionCategory> getSettings(){
		return settings;
	}
	
	public HashMap<UpgradeType, UpgradeState> getUpgrades(){
		return upgrades;
	}
	
	public HashMap<UUID, HashMap<ChunkSettingType, ChunkSettingState>> getChunkSettings(Chunk chunk){
		return chunkSettings.get(DominionUtils.packChunk(chunk));
	}
	
	public boolean hasUpgrade(UpgradeType type){
		return upgrades.get(type).equals(UpgradeState.PURCHASED);
	}
	
	public int getUpgradeCount(){
		int count = 0;
		for(UpgradeState uS : upgrades.values()) {
			if(uS.equals(UpgradeState.PURCHASED)) {
				count++;
			}
		}
		return count;
	}
	
	public Location getHomeLocation() {
		if(homeLoc==null) {
			return null;
		}
		return homeLoc.toLocation();
	}

	public void setHomeLocation(Location loc) {
		this.homeLoc = new SimpleLocation(loc);
	}
	
	public int getCalculatedWealth() {
		int wealth = (int) (DominionManager.costToClaim*getChunkCount()+getMoney());
		wealth += getUpgradeCount()*1500;
		//wealth += calcWealthFromMembers();
		return wealth;
	}
	
	/*
	private int calcWealthFromMembers() {
		int costForMembers = (int) (-25 * Math.pow(getMemberCount(), 2) + 500 * getMemberCount());
		if(costForMembers < 100*getMemberCount()) {
			costForMembers = 100*getMemberCount();
		}
		return costForMembers;
	}*/
	
	public int getTaxRate() {
		return getCalculatedWealth()/235 + 1;
	}
	
	
	// Logs
	public void addLogRecord(LogType type, String record) {
		if(type.equals(LogType.FINANCE)) {
			financeLog.put(new Date(), record);
			return;
		}
		if(type.equals(LogType.MEMBERS)) {
			membersLog.put(new Date(), record);
			return;
		}
		if(type.equals(LogType.DIPLOMACY)) {
			diplomacyLog.put(new Date(), record);
			return;
		}
	}
	
	public TreeMap<Date, String> getLog(LogType type){
		if(type.equals(LogType.FINANCE)) {
			return financeLog;
		}
		if(type.equals(LogType.MEMBERS)) {
			return membersLog;
		}
		return diplomacyLog;
	}
	
	public void addPersistentEntity(EntityType type, UUID uuid) {
		persistentEntities.put(type, uuid);
	}
	
	public void removePersistentEntity(EntityType type, UUID uuid) {
		persistentEntities.remove(type, uuid);
	}
	
	public HashMap<EntityType, UUID> getPersistentEntitiies() {
		return persistentEntities;
	}

}
