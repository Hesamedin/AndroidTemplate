package com.allstarxi.app.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allstarxi.app.R;
import com.allstarxi.app.adapter.LeagueDataAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeaguesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeaguesFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LeaguesFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ListView globalLeagueDataListView;
    LeagueDataAdapter globalAdapter;

    ListView customLeagueDataListView;
    LeagueDataAdapter customAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaguesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaguesFragment newInstance(String param1, String param2) {
        LeaguesFragment fragment = new LeaguesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public LeaguesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View returnView = inflater.inflate(R.layout.fragment_leagues, container, false);

        // Frame Related
        globalLeagueDataListView = (ListView)returnView.findViewById(R.id.globalLeaguesListView);
        globalLeagueDataListView.setOnItemClickListener(this);

        globalAdapter = new LeagueDataAdapter(getActivity(), getActivity().getLayoutInflater());

        final String globalLeauesJson = "{\n" +
                "    \"leagues\": [\n" +
                "        {\n" +
                "            \"name\": \"Overall League\",\n" +
                "            \"rank\": \"5124\",\n" +
                "            \"teams\": \"12532\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Brazil Fans\",\n" +
                "            \"rank\": \"912\",\n" +
                "            \"teams\": \"5320\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Turkey\",\n" +
                "            \"rank\": \"312\",\n" +
                "            \"teams\": \"955\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        try
        {
            JsonObject object = (JsonObject)new JsonParser().parse(globalLeauesJson);
            JsonArray array = object.getAsJsonArray("leagues");
            globalAdapter.mJsonArray = array;
            View globalLeaguesHeader = getActivity().getLayoutInflater().inflate(R.layout.league_list_header, null);
            globalLeagueDataListView.addHeaderView(globalLeaguesHeader);
            globalLeagueDataListView.setAdapter(globalAdapter);
            globalAdapter.notifyDataSetChanged();
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }



        customLeagueDataListView = (ListView)returnView.findViewById(R.id.customLeaguesListView);
        customLeagueDataListView.setOnItemClickListener(this);

        customAdapter = new LeagueDataAdapter(getActivity(), getActivity().getLayoutInflater());

        final String customLeaguesJson = "{\n" +
                "    \"leagues\": [\n" +
                "        {\n" +
                "            \"name\": \"Overall League\",\n" +
                "            \"rank\": \"5124\",\n" +
                "            \"teams\": \"12532\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Brazil Fans\",\n" +
                "            \"rank\": \"912\",\n" +
                "            \"teams\": \"5320\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Turkey\",\n" +
                "            \"rank\": \"312\",\n" +
                "            \"teams\": \"955\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        try
        {
            JsonObject object = (JsonObject)new JsonParser().parse(customLeaguesJson);
            JsonArray array = object.getAsJsonArray("leagues");
            customAdapter.mJsonArray = array;

            View customLeaguesHeader = getActivity().getLayoutInflater().inflate(R.layout.league_list_header, null);
            customLeagueDataListView.addHeaderView(customLeaguesHeader);

            customLeagueDataListView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }

        return returnView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
