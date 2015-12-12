package es.ull.etsii.pai.practicafinal.physics;

import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.redvsblue.Actor;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Tracer {
	private Positionable owner;
	
	public Entity[] getCollision(BvsR_Map map){
		boolean [] channels = {true,true, true};
		Line_trace trace = 
		ArrayList<Entity> actors = new ArrayList<>();
		if(channels[BvsR_Map.PLANE_ACTORS]){
			for(Entity ent : map.getActors())
		}
	}
}
