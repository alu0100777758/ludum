package es.ull.etsii.pai.practicafinal.lwjglImplement.redvsblue;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
 
import org.lwjgl.BufferUtils;
//import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.lwjgl.openal.;

public class OA_AudioManager {
	public static final String SOUNDS_FOLDER = "/sounds/";
	public static final int MAX_CONCURRENT_SOUNDS = 15;							// Numero maximo de sonidos reproduciendo a la vez.
	private static ArrayList<AudioClip> clips = new ArrayList<AudioClip>();		// Truco sucio para engañar al planificador.
	private static ArrayList<AudioClip> loops = new ArrayList<AudioClip>();		// Lista de clips ejecutandose indefinidamente.
	private static boolean sfx = true;											// Determina si estan activados los efectos de sonido.
	private static boolean music = true;										// Determina si esta activada la musica TODO cambiar el comportamiento por defecto 
														//	(por defecto desactivada por comodidad durante desarrollo)
	private WaveDa wavEffect;
	public static void init(){
		try {

	        // you can play wavs by loading the complete thing into 
	        // a sound
	        wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("testdata/cbrown01.wav"));
	        } catch (IOException e) {
	        e.printStackTrace();
	}
	/**
	 * Comienza la reproduccion del audio indicado por parametro.
	 * @param name
	 */
	public static void startAudio(String name) {
		if (!isSfx())
			return;
		
		if (name.length() > 0) {
//			AudioClip audio = Applet.newAudioClip(AudioManager.class
//					.getResource("/sounds/" + name));
			AudioClip audio = new AudioClip(OA_AudioManager.class.getResource("/sounds/" + name).toString());
//			getClips().add(audio);
			audio.play();
			if (getClips().size() >= 2 * MAX_CONCURRENT_SOUNDS) {
				for (int i = 0; i < MAX_CONCURRENT_SOUNDS; i++)
					getClips().get(0).stop();
					getClips().remove(0);
			}
		}
	}

	/**
	 * Reproduce de forma ciclica el clip con nombre indicado.
	 * @param name
	 */
	public static void reproduceAudio(String name) {
		if (!isMusic()) 
			return;
			
		if (name.length() > 0) {
			AudioClip audio = new AudioClip(OA_AudioManager.class
					.getResource(SOUNDS_FOLDER + name).toString());
			audio.play();
			getLoops().add(audio);
		}
	}
	/**
	 * Pausa todos los clips.
	 */
	public static void stopAll() {
		for(AudioClip audio : getLoops())
			audio.stop();
	}


	/**
	 * Para un determinado clip.
	 * @param name
	 */
	public static void stopAudio(String name) {
		AudioClip audio = new AudioClip(OA_AudioManager.class
				.getResource(SOUNDS_FOLDER + name).toString());
		audio.stop();
		getLoops().remove(audio);
	}
	/**
	 * Getters y Setters.
	 * @return
	 */
	public static ArrayList<AudioClip> getClips() {
		return clips;
	}

	public void setClips(ArrayList<AudioClip> clips) {
		this.clips = clips;
	}

	public static ArrayList<AudioClip> getLoops() {
		return loops;
	}

	public static void setLoops(ArrayList<AudioClip> loops) {
		OA_AudioManager.loops = loops;
	}
	
	public static boolean isSfx() {
		return sfx;
	}

	public static void setSfx(boolean sfx) {
		OA_AudioManager.sfx = sfx;
	}

	public static boolean isMusic() {
		return music;
	}

	public static void setMusic(boolean music) {
		OA_AudioManager.music = music;
		if (!music)
			stopAll();
	}

	

}