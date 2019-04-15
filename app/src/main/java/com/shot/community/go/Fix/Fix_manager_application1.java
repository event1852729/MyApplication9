package com.shot.community.go.Fix;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shot.community.go.R;

/**
 * Created by user on 2017/11/9.
 */

public class Fix_manager_application1 extends AppCompatActivity {

    TextView Place,Detail,Id,fixid;
    EditText fixname,fixphone;
    Button ok,no;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_application);

        Place = (TextView)findViewById(R.id.place_text);
        Detail = (TextView)findViewById(R.id.detail_text);
        Id = (TextView)findViewById(R.id.idnum);
        fixid = (TextView)findViewById(R.id.fixid);

        fixname = (EditText)findViewById(R.id.people);
        fixphone = (EditText)findViewById(R.id.phone);

        ok = (Button)findViewById(R.id.agree);
        no = (Button)findViewById(R.id.nonagree);

        int p = Fix_item.pos;
        Detail.setText(Fix_item.D1[p]);
        Place.setText(Fix_item.P1[p]);

        imageView = (ImageView)findViewById(R.id.image);

    }
}
