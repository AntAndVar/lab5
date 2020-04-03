package a.caps;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private String question;
    private String answer;
    private int score;
    private int questionNum=1;
    private String qa;
    private String logs = "";
    private ToneGenerator tone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        game = new Game();
        this.tone = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        ask();


    }

    private void ask(){
        qa =game.qa();
        question=qa.substring(0,qa.indexOf("\n"));
        answer=qa.substring(qa.indexOf("\n")+1,qa.length());
        ((TextView)findViewById(R.id.question)).setText(question);
    }

    public void onDone(View V){
        String yourAns=(((EditText)findViewById(R.id.answer)).getText().toString()).toUpperCase();


        if (yourAns.equals(answer.toUpperCase())){
            score = score + 1;
            ((TextView)findViewById(R.id.score)).setText("Score = " + score);
            tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD,200);
        }
        else {
            tone.startTone(ToneGenerator.TONE_CDMA_ABBR_REORDER,200);
        }


        logs="Q# " + questionNum + ": " + question + "\n" +
                "your answer: " + yourAns + "\n" +
                "correct answer: " + answer + "\n" + "\n" + logs ;


        ((TextView)findViewById(R.id.logs)).setText(logs);


        questionNum = questionNum+1;
        ((TextView)findViewById(R.id.questionNum)).setText( "Q# " + questionNum);

        if (questionNum == 10){
            ((TextView)findViewById(R.id.questionNum)).setText( "Game Over");
            findViewById(R.id.done).setEnabled(false);
        }
        else {
            ask();
        }
        ((TextView)findViewById(R.id.answer)).setText("");

    }




}