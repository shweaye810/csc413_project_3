package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Vector sh_lst;
    Button bt_rct, bt_crc, bt_cls;
    Shape sh;
    ShapeFactory sh_fact;
    Context cntx;
    Canvas cnv;
    TextView txt_vw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RelativeLayout sh_lyt = (RelativeLayout) findViewById(R.id.rltv_lyt);

        sh_lst = new Vector();
        bt_rct = (Button) findViewById(R.id.btn_rct);
        bt_crc = (Button) findViewById(R.id.btn_crc);
        bt_cls = (Button) findViewById(R.id.btn_cls);


        sh_fact = new ShapeFactory();
        cntx = this.getApplicationContext();
        txt_vw = (TextView) findViewById(R.id.textView);

        bt_rct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adjustShapeAlpha();
                sh = sh_fact.getShape(cntx, "Rectangle");
                sh_lst.add(sh);
                sh_lyt.addView(sh);
                updateShapeCount();
            }
        });

        bt_crc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adjustShapeAlpha();
                sh = sh_fact.getShape(cntx, "Circle");
                sh_lst.add(sh);
                sh_lyt.addView(sh);
                updateShapeCount();
            }
        });

        bt_cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh_lst.clear();
                sh_lyt.removeAllViews();
                updateShapeCount();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    void adjustShapeAlpha() {
        for (int i = 0; i < sh_lst.size(); i++) {
            Shape tmp = (Shape) sh_lst.get(i);
            if (tmp.getShapeAlpha() > 0.0f) {
                tmp.setShapeAlpha(tmp.getShapeAlpha() - 0.1f);
            } else {
                tmp.removeShape();
                sh_lst.remove(i);
            }
        }

    }
    void updateShapeCount() {
        int rct_cnt = 0, crc_cnt = 0;
        for (int i = 0; i < sh_lst.size(); i++) {
            Shape tmp = (Shape) sh_lst.get(i);
            ShapeType sh_t = tmp.getShapeType();
            if (sh_t == ShapeType.Rectangle)
                rct_cnt++;
            else if (sh_t == ShapeType.Circle)
                crc_cnt ++;
        }
        txt_vw.setText(rct_cnt + " Rectangles, " + crc_cnt + " Circles.");
    }

}
