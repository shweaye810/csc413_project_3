package com.tz.shwe.project_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Vector;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    Vector sh_lst;
    Button bt_rct, bt_crc, bt_cls;
    ToggleButton tb_mod, tb_grv;

    Shape sh;
    ShapeFactory sh_fact;
    Context cntx;
    TextView txt_vw;
    public static float width, height, div, min;
    RelativeLayout sh_lyt;
    String mode;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sh_lyt = (RelativeLayout) findViewById(R.id.rltv_lyt);
        div = 3;
        min = 30;

        sh_lst = new Vector();
        bt_rct = (Button) findViewById(R.id.btn_rct);
        bt_crc = (Button) findViewById(R.id.btn_crc);
        bt_cls = (Button) findViewById(R.id.btn_cls);
        tb_mod = (ToggleButton) findViewById(R.id.tb_mod);
        tb_grv = (ToggleButton) findViewById(R.id.tb_grv);


        sh_fact = new ShapeFactory();
        cntx = this.getApplicationContext();
        txt_vw = (TextView) findViewById(R.id.textView);

        mode = "normal";

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

        tb_mod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Shape tmp;
                if (isChecked) {
                    div = 1;
                    min = 100;
                    mode = "enlarge";
                } else {
                    div = 3;
                    min = 30;
                    mode = "normal";
                }
                updateShapeCount();
            }
        });

        tb_grv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gravity_start();
                    origin_loc_stop();
                } else {
                    gravity_stop();
                    origin_loc_start();
                }
            }
        });

    }

    Boolean grv_started = false, org_started = false;
    private Runnable grv_run = new Runnable() {
        @Override
        public void run() {
            enable_gravity();
            if (grv_started) {
                gravity_start();
            }
        }
    };

    public void gravity_stop() {
        grv_started = false;
        handler.removeCallbacks(grv_run);
    }

    public void gravity_start() {
        grv_started = true;
        handler.postDelayed(grv_run, 500);
    }

    private Runnable itm_ret = new Runnable() {
        @Override
        public void run() {
            disable_gravity();
            if (org_started) {
                origin_loc_start();
            }
        }
    };

    public void origin_loc_stop() {
        org_started = false;
        handler.removeCallbacks(itm_ret);
    }

    public void origin_loc_start() {
        org_started = true;
        handler.postDelayed(itm_ret, 500);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        updateSizeInfo();
    }
    private void updateSizeInfo() {
        width = sh_lyt.getWidth();
        height = sh_lyt.getHeight();
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
            if (tmp.getShapeAlpha() > 0.1f) {
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
                crc_cnt++;
        }
        txt_vw.setText(rct_cnt + " Rectangles, " + crc_cnt + " Circles.");
    }
    void enable_gravity() {
        for (int i = 0; i < sh_lst.size(); i++) {
            Shape tmp = (Shape) sh_lst.get(i);
            tmp.add_gravity();
        }
    }
    void disable_gravity() {
        for (int i = 0; i < sh_lst.size(); i++) {
            Shape tmp = (Shape) sh_lst.get(i);
            tmp.remove_gravity();
        }
    }

}
