/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplerpg.sound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
//import simplerpg.Button.Options;
import simplerpg.Launcher;

/**
 *
 * @author phamn
 */
public class SoundPlayer {
    
    private Clip clip;
    
    
    public SoundPlayer(File path)
    {
    	
        try{
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
           
        }catch(Exception e){}
    }
    
    //Decrease Sound
    public void soundDecrease()
    {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(gainControl.getValue()-2.0f>-16.0f)
            {
            	gainControl.setValue(gainControl.getValue()-2.0f);
            }
            else gainControl.setValue(-60.0f);
            //clip.start();
        }
    }
    ///Increase Sound
    public void soundIncrease()
    {
    	if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if(gainControl.getValue()==-60.0f)	gainControl.setValue(-14.0f);
            else
            	if(gainControl.getValue()+2.0f<=0)
            		gainControl.setValue(gainControl.getValue()+2.0f);
            //clip.start();
        }
    }
    public void playMusic(){
        if (clip != null) 
        {
            stop();
            clip.setFramePosition(0);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void play(){
        if(clip !=null){
            stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void stop()
    {
        if(clip.isRunning()) clip.stop();
    }
    public boolean isRunning()
    {
        return clip.isRunning();
    }
    public void close(){
        clip.close();
    }
    
}
