package com.allstarxi.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.nolanlawson.supersaiyan.SectionedListAdapter;
import com.nolanlawson.supersaiyan.Sectionizer;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class TeamFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // Frame Related
    ListView playerDataListView;
    PlayerDataAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2)
    {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public TeamFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View returnView = inflater.inflate(R.layout.fragment_team, container, false);

        // Frame Related
        playerDataListView = (ListView)returnView.findViewById(R.id.teamListView);
        playerDataListView.setOnItemClickListener(this);

        adapter = new PlayerDataAdapter(getActivity(), getActivity().getLayoutInflater());

        final String json = "{\n" +
                "    \"players\": [\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name1\",\n" +
                "            \"price\": \"price1\",\n" +
                "            \"type\": \"gk\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name2\",\n" +
                "            \"price\": \"price2\",\n" +
                "            \"type\": \"def\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name3\",\n" +
                "            \"price\": \"price3\",\n" +
                "            \"type\": \"midfielders\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"country\": \"algeria\",\n" +
                "            \"name\": \"name4\",\n" +
                "            \"price\": \"price4\",\n" +
                "            \"type\": \"midfielders\"\n" +
                "        }\n" +
                "    ]\n" +
                "} ";

        try
        {
            JsonObject object = (JsonObject)new JsonParser().parse(json);
            JsonArray array = object.getAsJsonArray("players");
            adapter.mJsonArray = array;
            adapter.notifyDataSetChanged();
        }
        catch (JsonParseException e)
        {
            e.printStackTrace();
        }

        SectionedListAdapter<PlayerDataAdapter> sectionedAdapter =
                SectionedListAdapter.Builder.create(getActivity(), adapter)
                        .setSectionizer(new Sectionizer<JsonObject>(){

                            @Override
                            public CharSequence toSection(JsonObject item) {
                                return item.get("type").getAsString();
                            }
                        })
                        .sortKeys()
                                //.sortValues()
                        .build();

        playerDataListView.setAdapter(sectionedAdapter);

        // Inflate the layout for this fragment
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

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
