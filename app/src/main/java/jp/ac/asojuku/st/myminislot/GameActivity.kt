package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*


class GameActivity : AppCompatActivity() {

    var temoti2=0;

    var kake =0;

    var wincoin=0;

    val imageArray:Array<Int> = arrayOf(
            R.drawable.banana,
            R.drawable.bar,
            R.drawable.bigwin,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.lemon,
            R.drawable.orange,
            R.drawable.seven,
            R.drawable.waltermelon
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btn_back.setOnClickListener{onBackButtonTapped(it)};
        btn_stop.setOnClickListener{onStopButtonTapped()};

        val pref = PreferenceManager.getDefaultSharedPreferences(this);

        temoti2 = pref.getInt("temoti",0);
        kake = pref.getInt("kakekin",0);

        temoti.setText(Integer.toString(temoti2));
        kakekin.setText(Integer.toString(kake));


    }

    fun onBackButtonTapped(view: View?){
        val intent = Intent(this, MainActivity::class.java);
        // 画面遷移スタート
        this.startActivity(intent);
    }

    fun onStopButtonTapped(){
        var x = Random().nextInt(9);
        var y = Random().nextInt(9);
        var z = Random().nextInt(9);

        img1.setImageResource(imageArray[x]);
        img2.setImageResource(imageArray[y]);
        img3.setImageResource(imageArray[z]);

        if(x == y && y == z){
            //かけきん*2=wincoin
            wincoin = kake * 2;
            //もちきん+wincoin = もちきん
            temoti2 = temoti2 + wincoin;
            //もちきんをぷりふぁれんすに登録
            val pref = PreferenceManager.getDefaultSharedPreferences(this);

            val editor = pref.edit()

            editor.putInt("temoti",temoti2).apply()

        }
        else{

            //もちきん-かけきん　= もちきん
            val pref = PreferenceManager.getDefaultSharedPreferences(this);

            val editor = pref.edit()

            editor.putInt("temoti",temoti2).apply()
        }
    }





    fun setCoin(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val my = pref.getInt("MY_Coin",990);
        val vet = pref.getInt("MY_Bet",10);

        temoti.setText(my.toString());
        kakekin.setText(vet.toString());

    }
}
