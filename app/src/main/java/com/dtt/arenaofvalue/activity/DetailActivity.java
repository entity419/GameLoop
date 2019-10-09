package com.dtt.arenaofvalue.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dtt.arenaofvalue.R;
import com.dtt.arenaofvalue.adapter.DetailAdapter;
import com.dtt.arenaofvalue.constance.Constance;
import com.dtt.arenaofvalue.model.Gem;
import com.dtt.arenaofvalue.model.Heroes;
import com.dtt.arenaofvalue.model.Skill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    static final int PERMISSION_CODE = 1;
    static final int IMAGE_PICK_CODE = 1000;

    private Context mContext;
    private ImageView imgR, imgP, imgG;
    private TextView tv1R, tv1P, tv1G;

    String iR, iP, iG, laneR, laneP, laneG;

    private DetailAdapter detailAdapter;
    private RecyclerView rcView;
    private LinearLayout linearLayout;
    private Intent intent;
    private String link, nameHero, url;
    private List<Skill> listSkills;
    private TextView tvNameHero;
    private ImageView imgBack;
    private List<Gem> listGem;

    private int sizeBody = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mContext = this;
        // anh tran man hinh
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        intent = getIntent();
        link = intent.getStringExtra(Constance.LINK_DETAIL);
        nameHero = intent.getStringExtra(Constance.NAME_HERO);
        url = intent.getStringExtra(Constance.IMAGE_DETAIL);
        initviews();

    }


    private void setBackground(String url) {
        Glide.with(this).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayout.setAlpha(0.5f);
                    linearLayout.setBackground(resource);
                }
            }
        });
    }

    private void initviews() {
//        linearLayout = findViewById(R.id.ll_detail);
        linearLayout = findViewById(R.id.ll_bg);
        tvNameHero = findViewById(R.id.tv_name);
        tvNameHero.setText(nameHero);
        imgBack = findViewById(R.id.img_back);
        setBackground(url);

        imgR = findViewById(R.id.img_r);
        imgP = findViewById(R.id.img_p);
        imgG = findViewById(R.id.img_g);

        tv1R = findViewById(R.id.lane1_r);
        tv1P = findViewById(R.id.lane1_p);
        tv1G = findViewById(R.id.lane1_g);


        listSkills = new ArrayList<>();
        listGem = new ArrayList<>();
        parse(link);
        rcView = findViewById(R.id.rc_item_skill);

        detailAdapter = new DetailAdapter(this, listSkills);
        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcView.setLayoutManager(manager);
        rcView.setNestedScrollingEnabled(false);
        rcView.setAdapter(detailAdapter);

    }

    private void setGem(ArrayList<Gem> arrGem) {
        if (arrGem.size() > 0) {
            iR = arrGem.get(0).getImgR();
            iP = arrGem.get(0).getImgP();
            iG = arrGem.get(0).getImgG();
            laneR = arrGem.get(0).getLaneR();
            laneP = arrGem.get(0).getLaneP();
            laneG = arrGem.get(0).getLaneG();
        } else {
            return;
        }

        Glide.with(mContext).load(iR).into(imgR);
        Glide.with(mContext).load(iP).into(imgP);
        Glide.with(mContext).load(iG).into(imgG);
        tv1R.setText(laneR);
        tv1P.setText(laneP);
        tv1G.setText(laneG);
        Log.d("tungdtasdas", "setGem: " + iR);
        Log.d("tungdtasdas", "setGem: " + iP);
        Log.d("tungdtasdas", "setGem: " + iG);
    }

    @SuppressLint("StaticFieldLeak")
    private void parse(final String link) {

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                parseDetailHero(link);
                parseGem(link);
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                detailAdapter.setData((ArrayList<Skill>) listSkills);
                setGem((ArrayList<Gem>) listGem);

            }
        }.execute();
    }

    public ArrayList<Skill> parseDetailHero(String link) {
        ArrayList<Skill> arrSkill = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).timeout(10000).get();
            Elements skill = doc.getElementsByClass("item-skill");
            for (int i = 0; i < skill.size(); i++) {

                Element docName = skill.get(i).getElementsByClass("in-skill").get(0);


                String img = skill.get(i).getElementsByClass("img-skill").get(0).getElementsByTag("img").attr("src");
                String linkImg = "https://lienquan.garena.vn" + img;
                Log.d("tungdt36", "IMG: " + linkImg);

                String inSkill = docName.text();

                Element title = docName.select("h2").first();
                String titleSkill = title.text();
                Log.d("tungdt36", "titleSkill: " + titleSkill);

                String countDowm = docName.getElementsByClass("txt").get(0).text();
                Log.d("tungdt36", "countDowm: " + countDowm);
                String mana = docName.getElementsByClass("txt").get(1).text();
                Log.d("tungdt36", "tieuhao: " + mana);
                String detail = docName.getElementsByClass("txt").get(2).text();
                Log.d("tungdt36", "detail: " + detail);


                Skill skillHero = new Skill(linkImg, titleSkill, mana, countDowm, detail);
                arrSkill.add(skillHero);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        listSkills = arrSkill;

        return arrSkill;
    }

    private ArrayList<Gem> parseGem(String link) {

        ArrayList<Gem> arrGem = new ArrayList<>();
        try {

            Document doc = Jsoup.connect(link).timeout(10000).get();
            /*
            read image backgournd and skill
             */
            Element docImgBG = doc.getElementsByClass("bxskin").get(0);

//            Element docSkinHeroes = docImgBG.getElementsByClass("cont-skin").get(0);
//            String ID = docSkinHeroes.getElementsByTag("div").attr("id");
//            String image = docSkinHeroes.getElementById(ID).getElementsByTag("img").attr("src");

            ArrayList<String> skin = new ArrayList<>();


            Element docSkinHero = docImgBG.getElementsByClass("cont-skin").get(0);
            Elements skinHeroes = docSkinHero.getElementsByTag("div");
            Element skinSrc = docSkinHero.getElementsByTag("div").first();
            String id = skinHeroes.attr("id");
            String srcSkin = skinSrc.getElementById(id).getElementsByTag("img").attr("src");
            Log.d("tungasdas", "parse: " + "https://lienquan.garena.vn/" + srcSkin);


//            for (int i = 0; i < skinHeroes.size() - 1; i++) {
//                String id = skinHeroes.get(i).attr("id");
//                skin.add(id);
//            }

            /*
                read Gem
             */
            Gem gem = new Gem();
            int size = doc.select("tbody").size();
            if (size == 3) {
                sizeBody = 1;
            } else if (size == 4) {
                sizeBody = 2;
            }
            Element trs = doc.select("tbody").get(sizeBody);
            Element tr0 = trs.getElementsByTag("tr").get(0);
            Element tr1 = trs.getElementsByTag("tr").get(1);
            int count = 0;
            for (Element td : tr0.children()) {
                String img = td.getElementsByTag("img").attr("src");
                String[] splip = img.split("files/");
                if (count == 0) {
                    gem.setImgR("https://lienquan.garena.vn/files/" + splip[1]);
                    Log.d("tiudfnglkdf", "parse: " + "https://lienquan.garena.vn/files/" + splip[1]);
                    count++;
                } else if (count == 1) {
                    gem.setImgP("https://lienquan.garena.vn/files/" + splip[1]);
                    count++;
                    Log.d("tiudfnglkdf", "parse: " + "https://lienquan.garena.vn/files/" + splip[1]);
                } else if (count == 2) {
                    gem.setImgG("https://lienquan.garena.vn/files/" + splip[1]);
                    count = 0;
                }
            }
            for (Element td : tr1.children()) {
                String lane = td.text();
                if (count == 0) {
                    gem.setLaneR(lane);
                    count++;
                } else if (count == 1) {
                    gem.setLaneP(lane);
                    count++;
                } else if (count == 2) {
                    gem.setLaneG(lane);
                    count = 0;
                }
            }
            arrGem.add(gem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listGem = arrGem;
        return arrGem;
    }
}
