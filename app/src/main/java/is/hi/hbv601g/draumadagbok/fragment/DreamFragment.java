package is.hi.hbv601g.draumadagbok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.model.Dream;




public class DreamFragment extends Fragment {

    private Dream mDream;
    private TextView title;
    private TextView content;
    private TextView interp;
    private static final String DREAM = "is.hi.hbv601g.draumadagbok.dream";


    private OnDreamFragmentInteractionListener mListener;

    public DreamFragment() {
        // Required empty public constructor
    }


    public static DreamFragment newInstance(Dream dream) {
        DreamFragment fragment = new DreamFragment();
        Bundle args = new Bundle();
        args.putSerializable(DREAM, dream);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dream, container, false);
        title = (TextView)v.findViewById(R.id.dtitle);
        content = (TextView)v.findViewById(R.id.dcontent);
        interp = (TextView)v.findViewById(R.id.dinterp);

        //insert to UI info on dream
        if (getArguments() != null) {
            mDream = (Dream) getArguments().getSerializable(DREAM);
            if(mDream.getName().contentEquals("null")){
                title.setText(R.string.tomur_titill);

                content.setText(mDream.getContent());
                interp.setText(mDream.getInterpretation());
            }
            else{
                title.setText(mDream.getName());
                content.setText(mDream.getContent());
                interp.setText(mDream.getInterpretation());
            }

            //layout settings
            title.setGravity(Gravity.CENTER);
            content.setGravity(Gravity.CENTER);
            interp.setGravity(Gravity.CENTER);
        }


        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDreamFragmentInteractionListener) {
            mListener = (OnDreamFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    //interface for data transfer back to activity
    public interface OnDreamFragmentInteractionListener {
        void onDreamFragmentInteraction(Dream dream);
    }
}
