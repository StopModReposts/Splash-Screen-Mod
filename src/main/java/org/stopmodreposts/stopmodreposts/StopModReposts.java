package org.stopmodreposts.stopmodreposts;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, acceptedMinecraftVersions = References.ACCEPTEDVERSIONS, certificateFingerprint = "893c317856cf6819b3a8381c5664e4b06df7d1cc")
public class StopModReposts {


	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		References.init(event);
	}

}
