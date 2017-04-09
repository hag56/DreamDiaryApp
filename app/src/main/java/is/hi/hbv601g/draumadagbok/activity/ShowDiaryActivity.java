package is.hi.hbv601g.draumadagbok.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.fragment.DiaryFragment;
import is.hi.hbv601g.draumadagbok.fragment.DreamFragment;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

//Activity for showing saved dreams
public  class ShowDiaryActivity extends SingleFragmentActivity
        implements DiaryFragment.Callbacks, DreamFragment.OnDreamFragmentInteractionListener{


    //overwritten creation method
    @Override
    public Fragment createFragment(){
        User user = (User) getIntent().getSerializableExtra(USER);
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


    @Override
    public void onDreamFragmentInteraction(Dream dream){
        //required but non implemented listener for dream
    }

    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
    //required to be able to receive data and start intent
    public static Intent nameIntent(Context packageContext, Bundle bndle) {
        Intent i = new Intent(packageContext, ShowDiaryActivity.class);
        i.putExtra(USER, bndle.getSerializable(USER));
        return i;
    }

}

