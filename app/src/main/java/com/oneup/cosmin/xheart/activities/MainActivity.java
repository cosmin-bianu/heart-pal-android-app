package com.oneup.cosmin.xheart.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oneup.cosmin.xheart.R;
import com.oneup.cosmin.xheart.exceptions.ShittyCodeException;
import com.oneup.cosmin.xheart.processing.cases.Range;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    /**
     * status csanid  aa nu il pun aici ca eu asa stiam
     * history
     * checklist (pt pending cases) adica?
     * can ii afiseaza sfat sa ii punem acolo, in caz ca nu poate rezolva pe loc problema sa nu uite de ea.   okcan
     * settings
     *
     * asa. hai sa scriem ceva si sa iti arat cum faci modificari si sa le ok. hai sa incepem altfel.
     * iti arat cum tragi de pe net ce e incarcat deja (pt modificarile pe care le fac eu, te anunt cand incarc)
     * asa. eu am incarcat acuma. hai sa il tragem.
     * ai inteles ce ti-am aratat? da apas un buton albastru si se da update :).)
     * am dat acolo show diff si ti-am aratat 2 coduri in paralel.
     *  ce era cu verde era adaugat. albastru era modificat , si daca era rosu era sters. a de aia m=am prins
     *  pai si de unde il iau?
     *  ce anume?
     *  lib-ul? da
     *  deci ce facem acum?
     */


    //var mereu sa declara sus, mai ales daca se folosesc in mai multe metode.
    //private inseamna ca se vede doar in clasa asta. doar acoladele astea.
    private Range veryGoodRange;
    private Range goodRange;
    private Range badRange;
    private Range dedRange;

    private LinearLayout statusLight;
    private TextView statusText;
    private Toolbar toolbar;
    private GraphView graph;
    private LineGraphSeries<DataPoint> points;
    /*
    //TEST
    private Button testVerde;
    private Button testGalben;
    private Button testRosu;
    private Button testNegru;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        try {
            veryGoodRange = new Range(0, 3, true, false);
            goodRange = new Range(3, 5, true, false);
            badRange = new Range(5, 8, true, false);
            dedRange = new Range(8, 10, true, true);
        }catch (ShittyCodeException e){
            e.printStackTrace();
        }


        statusLight = (LinearLayout) navigationView.getHeaderView(0);
        statusText = statusLight.findViewById(R.id.status_text);

        graph = findViewById(R.id.ecg_graph);
        graph.setClickable(false);
        Viewport gvp = graph.getViewport();
        gvp.setMinX(0);
        gvp.setMaxX(5);
        gvp.setScrollable(false);
        gvp.setScrollableY(false);
        gvp.setMinY(-127);
        gvp.setMaxY(127);

        points = new LineGraphSeries<>();
        graph.addSeries(points);
        final EditText editTextX = findViewById(R.id.tst_etX);
        final EditText editTextY = findViewById(R.id.tst_etY);
        findViewById(R.id.tst_button).setOnClickListener(
            new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToGraph(
                        new DataPoint(
                                Double.parseDouble(editTextX.getText().toString()),
                                Double.parseDouble(editTextY.getText().toString())
                        )
                );
            }
        });

        /*
        //TEST /*
        testVerde = findViewById(R.id.tst_verde);
        testGalben = findViewById(R.id.tst_galben);
        testRosu = findViewById(R.id.tst_rosu);
        testNegru = findViewById(R.id.tst_negru);

        //☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦
        testVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(1);
            }
        });
        testGalben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(4);
            }
        });
        testRosu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(6);
            }
        });
        testNegru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(9);
            }
        });
        *///☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦ ☦
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //simple as that. era butonul de settings de adineauri

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // Handle the camera action, todo deci aici sunt in xml elementele
        //totul luminat?nu inteleg pt ce sunt atatea else ifuri. nici eu. e cod prost scris.
        //am gasit la un moment dat comentarii de la devii de la google in codul de aici. le-au uitat acolo si
        //le-au publicat asa si imi aminteste de asta. stai. ce plm sunt alea?
        // te referi la clasa sau la ce?alea scrise aiurea acolo :)) cu galben ?cu de toate.
        // cand aia de la google au scris codul au lasat acolo un comentariu legat de faptul ca toate functiile sunt
        // chemate de la o sg instanta si ca de ce nu sunt statice. nush ce cauta ala acolo daca pune intrebari d-astea
        // totusi e bine ca intreaba, dar prost tre sa fii sa uiti aia acolo. mrg nici drq nu trb sa  se uite prin
        // codul ala. eu ma uitam acolo ca nu aveam net sa ma uit pe android docs. :)))))) low buget shit.
        // prost buget shit fratele meu. asta e. macar nu ezita la intrebari. ok
        // eventual uita-te acu in icoane si zi ce vrei sa pun
        //switch(plm) hai sa facem un test.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO USE RESOURCES FOR STRINGS
    //todo finalBackground colors pana cauti acolo pun elementele ok? ce elemente? in drawer. itemii si eu caut culorile acum? da.
    private void updateStatus(int value){
        int finalBackground;
        String finalText;
        if(veryGoodRange.isWithinRange(value)){
            finalText = "Foarte bun";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.veryGood);
        }
        else if (goodRange.isWithinRange(value)){
            finalText = "Bun";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.good);
        }
        else if (badRange.isWithinRange(value)){
            finalText = "Rău";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.bad);
        }
        else{
            finalText = "Foarte rău";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.ded);
        } //acum sunt cu verde toate
        statusText.setText(finalText);
        ColorDrawable finalDrawable = new ColorDrawable(finalBackground);
        statusLight.setBackground(finalDrawable); //todo rugaciune sa mearga
        toolbar.setBackgroundColor(finalBackground);
        toolbar.setTitle(finalText); //zic daca ai env de tv? da. ca tre sa unesc proiectele si dureaza putin. hai ca stau sa ma uit atunci
        //cum vrei cd G:\
    }

    private void addToGraph(DataPoint p){
        //fixme: W/GraphView: scrollToEnd works only with manual x axis bounds
        points.appendData(p, true, 100);
        graph.addSeries(points);
    }
}
