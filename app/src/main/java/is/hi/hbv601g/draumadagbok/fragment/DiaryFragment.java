package is.hi.hbv601g.draumadagbok.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.activity.LoginActivity;
import is.hi.hbv601g.draumadagbok.activity.MainActivity;
import is.hi.hbv601g.draumadagbok.model.Dream;
import is.hi.hbv601g.draumadagbok.model.User;

import static is.hi.hbv601g.draumadagbok.R.id.dream_recycler_view;

//Fragment that handles the diary layout
public class DiaryFragment extends Fragment {
    private static final String USER = "is.hi.hbv601g.draumadagbok.user";
    private RecyclerView mDreamRecyclerView;
    private DreamAdapter mAdapter;

    //Listener for getting data back to activity
    private Callbacks mListener;
    public interface Callbacks {
        void onDreamSelected(Dream dream);
    }
    // Required empty public constructor
    public DiaryFragment() {
    }

    //for creation
    public static DiaryFragment newInstance(User user) {
        DiaryFragment fragment = new DiaryFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER,user);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        mDreamRecyclerView = (RecyclerView) view.findViewById(dream_recycler_view);
        mDreamRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button mBackButton = (Button )view.findViewById(R.id.backBtn);

        //event listener
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle b = new Bundle();

                //check for guest user
                User us = (User) getArguments().getSerializable(USER);
                if(us.getId() != 0){
                    b.putSerializable(USER, us);
                    Intent i = MainActivity.nameIntent(getContext(), b);
                    startActivity(i);
                }
                else{
                    Intent i = LoginActivity.nameIntent(getContext());
                    startActivity(i);
                }

            }
        });

        updateUI();
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    //for changing UI according to now
    private void updateUI() {
        User use = (User)getArguments().getSerializable(USER);
        List<Dream> dreams = use.getDreams();
        mAdapter = new DreamAdapter(dreams);
        mDreamRecyclerView.setAdapter(mAdapter);
    }

    //Helper class for displaying scrollable list
    private class DreamHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Dream mDream;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public DreamHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_dream, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.dream_header);
            mDateTextView = (TextView) itemView.findViewById(R.id.dream_time);
        }

        public void bind(Dream Dream) {
            mDream = Dream;
            if (mDream.getName().contentEquals("null")){
                mTitleTextView.setText(R.string.tomur_titill );
                mDateTextView.setText(mDream.getDate().toString());
            }
            else{
                mTitleTextView.setText( mDream.getName() );
                mDateTextView.setText(mDream.getDate().toString());
            }
        }

        @Override
        public void onClick(View view) {
            mListener.onDreamSelected(mDream);
        }
    }

    //Helper adapter class for displaying list of dreams
    private class DreamAdapter extends RecyclerView.Adapter<DreamHolder> {

        private List<Dream> mDreams;

        public DreamAdapter(List<Dream> Dreams) {
            mDreams = Dreams;
        }

        @Override
        public DreamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DreamHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DreamHolder holder, int position) {
            Dream Dream = mDreams.get(position);
            holder.bind(Dream);
        }

        @Override
        public int getItemCount() {
            return mDreams.size();
        }
    }
}
