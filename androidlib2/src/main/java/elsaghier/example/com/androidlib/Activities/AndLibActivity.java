package elsaghier.example.com.androidlib.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import elsaghier.example.com.androidlib.R;

public class AndLibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_lib);
        TextView joke = findViewById(R.id.joke_TV);
        String jokeText = getIntent().getStringExtra("javaJoke");
        joke.setText(jokeText);
    }
}
