package is.hi.hbv601g.draumadagbok.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import static android.content.ContentValues.TAG;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.fragment.DiaryFragment;
import is.hi.hbv601g.draumadagbok.fragment.DreamFragment;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;
//Main activity
public  class ShowDiaryActivity extends SingleFragmentActivity
        implements DiaryFragment.Callbacks, DreamFragment.OnDreamFragmentInteractionListener{


    public Fragment createFragment(){
        User user = (User) getIntent().getSerializableExtra(USER);
        Log.i(TAG, user.toString());
        return DiaryFragment.newInstance(user);
    }

    //listener for diary
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

    //listener for dream
    @Override
    public void onDreamFragmentInteraction(Dream dream){}

    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
    //to be able to receive data via intent
    public static Intent nameIntent(Context packageContext, Bundle bndle) {

        Intent i = new Intent(packageContext, ShowDiaryActivity.class);
        i.putExtra(USER, bndle.getSerializable(USER));
        return i;
    }

}

