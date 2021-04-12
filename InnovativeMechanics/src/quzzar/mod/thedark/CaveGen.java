package quzzar.mod.thedark;

import org.bukkit.Chunk;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

public class CaveGen{
	
	public Chunk chunk;
	
    private final double f1xz;
    private final double f1y;
    
    private final int amplitude1 = 100;
    private final double subtractForLessThanCutoff;
    
    private final double f2xz = 0.25;
    private final double f2y = 0.05;
    private final int amplitude2 = 2;
    
    private final double f3xz = 0.025;
    private final double f3y = 0.005;
    private final int amplitude3 = 20;
    
    private final int caveBandBuffer;
    
    private final NoiseGenerator noiseGen1;
    private final NoiseGenerator noiseGen2;
    private final NoiseGenerator noiseGen3;

    public CaveGen(Chunk chunk) {
        this.chunk = chunk;
        subtractForLessThanCutoff = amplitude1 - DarkManager.cutoff;
        f1xz = 1.0 / DarkManager.sxz;
        f1y = 1.0 / DarkManager.sy;
        if (DarkManager.caveMax - DarkManager.caveMin > 128) {
            caveBandBuffer = 32;
        } else {
            caveBandBuffer = 16;
        }
        noiseGen1 = new SimplexNoiseGenerator(chunk.getWorld());
        noiseGen2 = new SimplexNoiseGenerator((long) noiseGen1.noise(chunk.getX(), chunk.getZ()));
        noiseGen3 = new SimplexNoiseGenerator((long) noiseGen1.noise(chunk.getX(), chunk.getZ()));
    }
    
    public boolean isCave(int x, int y, int z) {
        double xx = (chunk.getX() << 4) | (x & 0xF);
        double yy = y;
        double zz = (chunk.getZ() << 4) | (z & 0xF);

        double n1 = (noiseGen1.noise(xx * f1xz, yy * f1y, zz * f1xz) * amplitude1);
        double n2 = (noiseGen2.noise(xx * f2xz, yy * f2y, zz * f2xz) * amplitude2);
        double n3 = (noiseGen3.noise(xx * f3xz, yy * f3y, zz * f3xz) * amplitude3);
        double lc = linearCutoffCoefficient(y);

        boolean isInCave = n1 + n2 - n3 - lc > DarkManager.cutoff;
        return isInCave;
    }
    
    private double linearCutoffCoefficient(int y) {
        
        if (y < DarkManager.caveMin || y > DarkManager.caveMax) {
            return subtractForLessThanCutoff;
            
        } else if (y >= DarkManager.caveMin && y <= DarkManager.caveMin + caveBandBuffer) {
            double yy = y - DarkManager.caveMin;
            return (-subtractForLessThanCutoff / (double) caveBandBuffer) * yy + subtractForLessThanCutoff;
            
        } else if (y <= DarkManager.caveMax && y >= DarkManager.caveMax - caveBandBuffer) {
            double yy = y - DarkManager.caveMax + caveBandBuffer;
            return (subtractForLessThanCutoff / (double) caveBandBuffer) * yy;
            
        } else {
            return 0;
        }
    }
	
	
}