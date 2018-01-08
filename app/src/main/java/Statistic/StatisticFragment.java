package Statistic;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mannermakethman.vkd.ourtreasure.R;

import java.util.ArrayList;
import java.util.List;

import base.FragmentBase;
import chart.BarChartFragment;
import chart.PieChartFragment;


public class StatisticFragment extends FragmentBase {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private Activity activity;
    private OnFragmentInteractionListener mListener;

    private EditText fromCalendar, toCalendar;
    private Spinner typeOfChartSpinner;
    private ViewPager viewPager;

    public StatisticFragment() {
        super();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PurposeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void toFromCalendar() {
    }

    private void openFromCalendar() {
    }

    private void resetChart() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_statistic, container, false);
        fromCalendar = (EditText) rootView.findViewById(R.id.statistic_from_text);
        toCalendar = (EditText) rootView.findViewById(R.id.statistic_to_text);
        Spinner typeOfChartSpinner = (Spinner) rootView.findViewById(R.id.type_of_chart_spinner);

        ArrayAdapter<CharSequence> typeOfChartArray =
                ArrayAdapter.createFromResource(applicationContext, R.array.type_of_chart_array, android.R.layout.simple_spinner_dropdown_item);
        typeOfChartSpinner.setAdapter(typeOfChartArray);
        fromCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFromCalendar();
                resetChart();
            }
        });
        toCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFromCalendar();
                resetChart();
            }
        });

        viewPager = (ViewPager) rootView.findViewById(R.id.chart_view_pager);
        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
        pageAdapter.fragmentList.add(new BarChartFragment());
        pageAdapter.fragmentList.add(new PieChartFragment());
        viewPager.setAdapter(pageAdapter);
        activity = getActivity();


        // Inflate the layout for this fragment
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
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
        void onFragmentInteraction(Uri uri);
    }

    private class PageAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<>();

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return fragmentList.get(pos);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}

