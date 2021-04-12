package com.quzzar.server.souls.death;

import java.util.SplittableRandom;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.SellingManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.skills.Skills;
import com.quzzar.server.souls.MainListener;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.display.PlayerDisplayProfile;
import com.quzzar.server.souls.limbo.LimboWelcome;

public class DeathHandler {

	private static SplittableRandom splitRand = new SplittableRandom();
	private static double fameToInfamyPercentage = 0.8;

	public static void overworldDeath(PlayerDeathEvent e) {

		/*
		 * ON DEATH: You lose essence.
		 * You drop 1/3 of items.
		 * You lose 0% - 15% of your money.
		 * You gain fame from kills.
		 */
		
		Player victimPlayer = e.getEntity();
		Player killerPlayer = e.getEntity().getKiller();

		PlayerCharacter victimChar = CharacterManager.getCharacter(victimPlayer);
		
		// Lose 0% - 15% of your money.
		double amtLost = Math.round(victimChar.getMoney() * splitRand.nextDouble(0.15) * 100.0) / 100.0;
		victimChar.takeMoney(amtLost);

		if (e.getEntity().getKiller() != null) {
			
			PlayerCharacter killerChar = CharacterManager.getCharacter(killerPlayer);

			// If killed by player, killer gets lost money
			killerChar.giveMoney(amtLost);

			// If killed by player, give them fame (decrease essence based off killValue)
			int killValue = MainListener.upPvpKillValue(killerPlayer.getUniqueId());
			killerChar.increaseInfamy(killValue + fameToInfamyPercentage*victimChar.getData().getFame().getFame());
			killerChar.refresh();

		}

		// Reduce essence (returns true if they still live)
		boolean lives = victimChar.subtractEssence();
		
		// Determine losses (and possible soul perishing)
		if (lives) {
			
			DetermineInvKeep.thirdLoss(victimPlayer);
			
			// Refresh display
			victimChar.refresh();

		} else {

			DetermineInvKeep.totalLoss(victimPlayer);
			permaKill(victimChar);
			
		}

	}

	public static void netherDeath(PlayerDeathEvent e) {

		/*
		 * ON DEATH:
		 * You don't lose essence.
		 * You drop all items.
		 * You don't lose any money.
		 * You gain 1 fame per kill.
		 */

		Player victimPlayer = e.getEntity();
		Player killerPlayer = e.getEntity().getKiller();

		if (e.getEntity().getKiller() != null) {
			
			PlayerCharacter killerChar = CharacterManager.getCharacter(killerPlayer);

			// If killed by player, give them 1 fame
			killerChar.increaseInfamy(1);
			killerChar.refresh();

		}

		// Drop Everything
		DetermineInvKeep.totalLoss(victimPlayer);

	}
	
	public static void endDeath(PlayerDeathEvent e) {

		/*
		 * ON DEATH:
		 * You lose MORE essence.
		 * You don't drop anything.
		 * You don't lose any money.
		 * You respawn where you died.
		 */
		
		Player victimPlayer = e.getEntity();
		Player killerPlayer = e.getEntity().getKiller();

		PlayerCharacter victimChar = CharacterManager.getCharacter(victimPlayer);
		
		if (e.getEntity().getKiller() != null) {
			
			PlayerCharacter killerChar = CharacterManager.getCharacter(killerPlayer);

			// If killed by player, give them fame (decrease essence based off killValue)
			int killValue = MainListener.upPvpKillValue(killerPlayer.getUniqueId());
			killerChar.increaseInfamy(killValue + fameToInfamyPercentage*victimChar.getData().getFame().getFame());
			killerChar.refresh();

		}

		// Reduce essence (returns true if they still live)
		boolean lives = victimChar.subtractEssence(1.5);
		
		// Determine losses (and possible soul perishing)
		if (lives) {
			
			// Refresh display
			victimChar.refresh();

		} else {

			DetermineInvKeep.totalLoss(victimPlayer);
			permaKill(victimChar);
			
		}

	}

	public static void permaKill(PlayerCharacter pChar) {
		
		Player player = pChar.getPlayer();
		
		player.getLocation().getWorld()
			.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 2, 0.8f);

		// Clear ender chest
		player.getEnderChest().clear();

		// Clear current inv (just in case)
		player.getInventory().clear();

		// Clear skills
		Skills.getSkillManager().removePlayer(player.getUniqueId());
		
		// Clear GE sellings
		for(GETrade trade : SellingManager.getSellings(player.getUniqueId())) {
			MarketManager.deleteTradeRecord(trade);
		}
		
		//Force remove from dominion
		DominionUtils.forceRemoveDominionMember(
				DominionUtils.getDominionByPlayer(player.getUniqueId()), player);
		
		// Kill character
		pChar.kill();
		
		PlayerCharacter pCharNew = CharacterManager.getCharacter(player);
		
		// Refresh display
		pCharNew.refresh();
		
		player.spigot().respawn();
		
		player.teleport(Souls.getLimboWorld().getSpawnLocation());
		
		LimboWelcome.welcomeAfterDeath(player);
		
		PlayerDisplayProfile.apply(pCharNew, false);

	}

}
