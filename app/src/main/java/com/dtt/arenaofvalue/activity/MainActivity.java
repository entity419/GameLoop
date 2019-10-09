package com.dtt.arenaofvalue.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dtt.arenaofvalue.OnItemClick;
import com.dtt.arenaofvalue.R;
import com.dtt.arenaofvalue.adapter.ListHeroAdapter;
import com.dtt.arenaofvalue.constance.Constance;
import com.dtt.arenaofvalue.model.Gem;
import com.dtt.arenaofvalue.model.Heroes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick, View.OnClickListener {


    private ListHeroAdapter adapter;
    private List<Heroes> listHeroes;
    private RecyclerView rcListHero;
    private Heroes heroes;
    LinearLayout linearLayout;

    private Button btn1, btn2, btn3, btn4, btn5, btn6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        initviews();

    }

    private void initviews() {

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);


        linearLayout = findViewById(R.id.ll_main);
        listHeroes = new ArrayList<>();
        adapter = new ListHeroAdapter(this, listHeroes, this);
        rcListHero = findViewById(R.id.rc_list_hero);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        rcListHero.setLayoutManager(gridLayoutManager);
        rcListHero.setAdapter(adapter);

//        new ParseAsyncTask().execute("https://lienquan.garena.vn/tuong");
        parse("https://lienquan.garena.vn/tuong");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                searchHeroes("1");
                break;
            case R.id.btn2:
                searchHeroes("2");
                break;
            case R.id.btn3:
                searchHeroes("3");
                break;
            case R.id.btn4:
                searchHeroes("4");
                break;
            case R.id.btn5:
                searchHeroes("5");
                break;
            case R.id.btn6:
                searchHeroes("6");
                break;
        }
    }



    @Override
    public void onClick(Heroes heroes) {
        String link = heroes.getLinkDetail();
        String name = heroes.getName();
        String url = heroes.getImage();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constance.LINK_DETAIL, link);
        intent.putExtra(Constance.NAME_HERO, name);
        intent.putExtra(Constance.IMAGE_DETAIL, url);
        startActivity(intent);

    }

    private void searchHeroes(String type) {
        ArrayList<Heroes> heroesType = new ArrayList<>();
        for (Heroes h : listHeroes) {
            if (h.getType().equalsIgnoreCase(type)) {
                heroesType.add(h);
            }
        }
        adapter.setData(heroesType);
    }

    @SuppressLint("StaticFieldLeak")
    private void parse(final String link) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
//                ArrayList<Heroes> arrTemp = parseHeroes(link);
//                listHeroes = arrTemp;
                parseHeroes(link);
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                adapter.setData((ArrayList<Heroes>) listHeroes);
            }
        }.execute();
    }

    private ArrayList<Heroes> parseHeroes(String link) {
        ArrayList<Heroes> arrHero = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).timeout(10000).get();
            Elements heroes = doc.getElementsByClass("list-champion");
            for (int i = 0; i < heroes.size(); i++) {

                Element docName = heroes.get(i).getElementsByClass("heroes").get(0);

                String name = docName.getElementsByTag("p").get(0).text();
                Log.d("tungdt", "name heroes: " + name);

//                String linkHeroes = docName.attr("href");
                String linkHeroes = docName.getElementsByTag("a").attr("href");
                Log.d("tungdt", "link heroes: " + linkHeroes);

                String imageHeroes = docName.getElementsByTag("img").attr("src");
                String imageIcon = "https://lienquan.garena.vn" + imageHeroes;

                Log.d("tungdt", "imageHeroes: " + "https://lienquan.garena.vn" + imageHeroes);

                String type = docName.getElementsByTag("p").attr("data-type");
                Log.d("tungkajdklas", "parse: " + type);
                Heroes champ = new Heroes(name, linkHeroes, null, imageIcon, type);
                arrHero.add(champ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        listHeroes = arrHero;
        return arrHero;
    }



}
