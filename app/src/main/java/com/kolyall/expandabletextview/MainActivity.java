package com.kolyall.expandabletextview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.kolyall.view.expandabletext.ExpandableView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableView expandableView = findViewById(R.id.expandableView);
        expandableView.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
//        expandableView.setText("Lorem Ipsum s simply dummy text of the printing and typesetting indu");
    }
}
