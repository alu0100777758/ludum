package es.ull.etsii.pai.practicafinal.physics;

import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Tracer {
	private Positionable owner;
	boolean [] channels = {true,true, true};
	Line_trace trace;
	public Tracer(Line_trace trace) {
		this.trace = trace;
	}
	public ArrayList<Entity>  getCollision(BvsR_Map map){
		ArrayList<Entity> actors = new ArrayList<>();
		if(channels[BvsR_Map.PLANE_ACTORS]){
			for(Entity ent : map.getActors()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_BACKGROUND]){
			for(Entity ent : map.getBackground()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_FOREKGROUND]){
			for(Entity ent : map.getForeground()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_MAP]){
			for(Entity ent : map.getStaticMap()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		return actors;
	}
}
