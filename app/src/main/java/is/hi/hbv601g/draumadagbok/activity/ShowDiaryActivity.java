package is.hi.hbv601g.draumadagbok.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import static android.content.ContentValues.TAG;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.fragment.DiaryFragment;
import is.hi.hbv601g.draumadagbok.fragment.DreamFragment;
import is.hi.hbv601g.draumadagbok.manager.ShowDiaryManager;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

public  class ShowDiaryActivity extends SingleFragmentActivity
        implements DiaryFragment.Callbacks{


    public Fragment createFragment(){
        User user = (User) getIntent().getSerializableExtra(USER);
        Log.i(TAG, user.toString());
        return DiaryFragment.newInstance(user);
    }

    @Override
    public void onDreamSelected(Dream dream){
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = ShowDreamActivity.DreamIntent(this, dream);
            startActivity(intent);
        } else {
            Fragment newDetail = DreamFragment.newInstance(dream);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }


    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
    //insert data to intent
    public static Intent nameIntent(Context packageContext, Bundle bndle) {

        Intent i = new Intent(packageContext, ShowDiaryActivity.class);
        i.putExtra(USER, bndle.getSerializable(USER));
        return i;
    }

}

