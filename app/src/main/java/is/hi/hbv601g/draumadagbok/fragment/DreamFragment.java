package is.hi.hbv601g.draumadagbok.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import is.hi.hbv601g.draumadagbok.R;
import is.hi.hbv601g.draumadagbok.manager.ShowDiaryManager;
import is.hi.hbv601g.draumadagbok.model.Dream;

import static android.content.ContentValues.TAG;


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

        if (getArguments() != null) {
            mDream = (Dream) getArguments().getSerializable(DREAM);
            title.setText(mDream.getName());
            content.setText(mDream.getContent());
            interp.setText(mDream.getInterpretation());
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
    public interface OnDreamFragmentInteractionListener {
        void onDreamFragmentInteraction(Dream dream);
    }
}
