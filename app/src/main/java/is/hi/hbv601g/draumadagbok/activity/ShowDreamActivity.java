package is.hi.hbv601g.draumadagbok.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.UUID;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.fragment.DiaryFragment;
import is.hi.hbv601g.draumadagbok.fragment.DreamFragment;
import is.hi.hbv601g.draumadagbok.model.Dream;

public class ShowDreamActivity extends SingleFragmentActivity
        implements DreamFragment.OnDreamFragmentInteractionListener{

    private static final String DREAM = "is.hi.hbv601g.draumadagbok.dream";
    Dream mDream;

    //overwritten creator
    @Override
    protected Fragment createFragment(){
        mDream = (Dream) getIntent().getSerializableExtra(DREAM);
        return DreamFragment.newInstance(mDream);
    }

    //event listener for dream interaction
    @Override
    public void onDreamFragmentInteraction(Dream dream){
        mDream = dream;

    }
    //for data transfer
    public static Intent DreamIntent(Context packageContext, Dream dream){
        Intent i = new Intent(packageContext, ShowDreamActivity.class);
        i.putExtra(DREAM, dream);
        return i;
    }

}
