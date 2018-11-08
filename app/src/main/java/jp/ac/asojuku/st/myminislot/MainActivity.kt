package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.view.View
import jp.ac.asojuku.st.myminislot.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    var kakecoin = 0;
    var temoticoin = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener { OnSlotButtonTapped(it) };
        kakekinup.setOnClickListener { OnkakekinUPButtonTapped(it) };
        kakekindown.setOnClickListener { OnkakekinDOWNButtonTapped(it) };
        temoti_resset.setOnClickListener{ OnTemoti_RessetButtonTapped(it) };


    }

    override fun onResume() {
        super.onResume()

        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val editor = pref.edit();

//        editor.clear().apply();

//        editor.putInt("MY_COIN",990)
//                .putInt("BET_COIN",10)
//                .apply()

        temoticoin = pref.getInt("temoti",990)
        kakecoin =pref.getInt("kakekin",10)

        temoti.setText(Integer.toString(temoticoin))
        kakekin.setText(Integer.toString(kakecoin))


    }
    fun OnSlotButtonTapped(view: View?){
        val intent = Intent(this,GameActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.edit().putInt("temoti",temoticoin).commit();
        pref.edit().putInt("kakekin",kakecoin).commit();
        startActivity(intent)
    }

    fun OnkakekinUPButtonTapped(view: View?){
        if(temoticoin > 0) {
            kakecoin = kakecoin + 10;
            temoticoin = temoticoin -10;
            kakekin.setText(kakecoin.toString());
            temoti.setText(temoticoin.toString());
        }else{
            kakekin.setText(kakecoin.toString());
        }
    }

    fun OnkakekinDOWNButtonTapped(view: View?){
        if(10 < kakecoin) {
            kakecoin = kakecoin - 10;
            temoticoin = temoticoin + 10;
            kakekin.setText(kakecoin.toString());
            temoti.setText(temoticoin.toString());
        }else{
            kakekin.setText(kakecoin.toString());
        }
    }

    fun OnTemoti_RessetButtonTapped(view: View?){
        temoticoin = 990;
        kakecoin = 10;
        temoti.setText(temoticoin.toString());
        kakekin.setText(kakecoin.toString());
    }
}
