package quzzar.mod.thedark;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.Textures.TextureDatabase;

public class DarkListener implements Listener{
	
	public static void runTask(){
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			public void run() {
				
				DarkManager.getWorld().setDifficulty(Difficulty.HARD);
				DarkManager.getWorld().setTime(18000);
				DarkManager.getWorld().setThundering(true);
				DarkManager.getWorld().setStorm(true);
				DarkManager.getWorld().setThunderDuration(100);
				DarkManager.getWorld().setPVP(true);
				
			}
		
		}, 400, 400);// 400 L == 20 sec, 20 ticks == 1 sec
		
	}
	
	
	public static void traverseGate(HeadUnit hu, Player p){
		
		if(hu.getTexture().equals(TextureDatabase.THE_DARK_GATEWAY)){
			
			if(!p.getWorld().equals(DarkManager.getWorld())){
				
				p.teleport(fixedGateway());
				
			}else if (Bukkit.getServer().getWorlds().contains(Bukkit.getWorld("world"))){
				
				p.teleport(Bukkit.getWorld("world").getSpawnLocation());
				
			}
			
		}
		
	}
	
	
	
	@EventHandler
    public void onWorldInit(WorldInitEvent e){
		
		if(e.getWorld().equals(DarkManager.getWorld())){
			
			e.getWorld().getPopulators().add(new DarkWorldCavePopulator());
			e.getWorld().getPopulators().add(new DarkWorldStructurePopulator());
			
		}
		
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e){
		
		if(e.getEntity().getWorld().equals(DarkManager.getWorld())){
			if(e.getEntity() instanceof Player){
				
				Random rand = new Random();
				int r = rand.nextInt(12) + 1;
				if(r==1){
					((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 0));
				} else if (r==2){
					((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
				} else if (r==3){
					((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 0));
				}
				
			}
		}
		
	}
	
	
	private static Location fixedGateway(){
		
		Location gateLoc = new Location(DarkManager.getWorld(), 0, 100, 0);
		
		return gateLoc;
		
	}
	
	
}
