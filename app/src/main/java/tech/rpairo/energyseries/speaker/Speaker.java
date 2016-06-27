package tech.rpairo.energyseries.speaker;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import tech.rpairo.energyseries.R;

/**
 * Created by Raul on 27/6/16.
 */
public class Speaker implements TextToSpeech.OnInitListener {

//TODO Convertir Speaker de Serializable a Parceable

    //region Variables
    private String fraseRecibida;
    public TextToSpeech tts;
    private int idioma;
    private Context context;
    //endregion

    //region Constructor
    public Speaker(Context context) {
        this.fraseRecibida = null;
        this.tts = new TextToSpeech(context, this);
        this.context = context;
    }
    //endregion

    //region Funciones de interfaz
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(new Locale("spa", "ES"));

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                Toast.makeText(this.context, "No esta disponible la sintesis de voz", Toast.LENGTH_LONG).show();
            else
                habla(this.fraseRecibida);
        } else {
            Toast.makeText(this.context, "No esta disponible la sintesis de voz", Toast.LENGTH_LONG).show();
            //Log.e("TTS", "Initilization Failed!");
            //this.habla(this.fraseRecibida);
        }
    }
    //endregion

    public void habla(String fraseRecibida) {

        this.fraseRecibida = fraseRecibida;

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SpeakerID");
        this.tts.speak(fraseRecibida, TextToSpeech.QUEUE_ADD, map);

        this.tts.setSpeechRate(1.0f);
    }

    public void cerrar() {
        this.tts.stop();
        this.tts.shutdown();
    }
    //endregion

}