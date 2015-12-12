package es.ull.etsii.pai.practicafinal.physics;

import java.io.Serializable;
import java.util.ArrayList;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.Entity;
import es.ull.etsii.pai.prct9.geometry.Positionable;

public class Tracer implements Serializable{
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
