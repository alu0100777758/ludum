package es.ull.etsii.pai.practicafinal.physics;

import java.io.Serializable;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.practicafinal.redvsblue.RvsB_World;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Tracer implements Serializable{
	private Positionable owner;
	boolean [] channels = {true,false,true,true};
	Line_trace trace;
	public Tracer(Line_trace trace) {
		this.trace = trace;
	}
	public ArrayList<Entity>  getCollision(RvsB_World rvsB_World){
		ArrayList<Entity> actors = new ArrayList<>();
		if(channels[BvsR_Map.PLANE_ACTORS]){
			for(Entity ent : rvsB_World.getMapData().getActors()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_BACKGROUND]){
			for(Entity ent : rvsB_World.getMapData().getBackground()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_FOREKGROUND]){
			for(Entity ent : rvsB_World.getMapData().getForeground()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		if(channels[BvsR_Map.PLANE_MAP]){
			for(Entity ent : rvsB_World.getStaticMap()){
				if(trace.collides(ent)){
					actors.add(ent);
				}
			}
		}
		return actors;
	}
	public Positionable getOwner() {
		return owner;
	}
	public void setOwner(Positionable owner) {
		this.owner = owner;
	}
	public boolean[] getChannels() {
		return channels;
	}
	public void setChannels(boolean[] channels) {
		this.channels = channels;
	}
	public Line_trace getTrace() {
		return trace;
	}
	public void setTrace(Line_trace trace) {
		this.trace = trace;
	}
	
}
