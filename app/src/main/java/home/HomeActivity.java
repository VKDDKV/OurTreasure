package home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mannermakethman.vkd.ourtreasure.R;

import java.util.List;

import Statistic.StatisticFragment;
import account.AccountFragment;
import base.ActivityBase;
import core.AppInfo;
import expense.ExpenseFragment;
import history.HistoryFragment;

public class HomeActivity extends ActivityBase {
    List<String> buttonNavigationViewList;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ArrayAdapter<String> drawerAdapter;
    Button drawerLeftNavigation;
    RelativeLayout leftDrawer;
    HomeHeaderFragment headerFragment;
    LinearLayout navigationExpense, navigationHistory, navigationAccount, navigationStatistic;
    int showingFragment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //set main Fragment
        StatisticFragment staticticFragment = new StatisticFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, staticticFragment, "Purpose");
        fragmentTransaction.commit();
        //set showingFragment
        showingFragment = AppInfo.SHOWING_STATISTIC_FRAGMENT;
        navigationExpense = (LinearLayout) findViewById(R.id.navigation_expend);
        navigationAccount = (LinearLayout) findViewById(R.id.navigation_account);
        navigationStatistic = (LinearLayout) findViewById(R.id.navigation_statistic);
        navigationHistory = (LinearLayout) findViewById(R.id.navigation_history);
    }

    public void navigationExpense(View view) {
        showingFragment = AppInfo.SHOWING_EXPENSE_FRAGMENT;
        switchNavigation();
        clearBackgroundNavigation();
        navigationExpense.setBackgroundColor(getResources().getColor(R.color.navigation_background_selected));

    }

    public void navigationHistory(View view) {
        showingFragment = AppInfo.SHOWING_HISTORY_FRAGMENT;
        switchNavigation();
        clearBackgroundNavigation();
        navigationHistory.setBackgroundColor(getResources().getColor(R.color.navigation_background_selected));

    }

    public void navigationAccount(View view) {
        showingFragment = AppInfo.SHOWING_ACCOUNT_FRAGMENT;
        switchNavigation();
        clearBackgroundNavigation();
        navigationAccount.setBackgroundColor(getResources().getColor(R.color.navigation_background_selected));

    }

    public void navigationStatistic(View view) {
        showingFragment = AppInfo.SHOWING_STATISTIC_FRAGMENT;
        switchNavigation();
        clearBackgroundNavigation();
        navigationStatistic.setBackgroundColor(getResources().getColor(R.color.navigation_background_selected));


    }

    private void clearBackgroundNavigation() {
        navigationHistory.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationExpense.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationAccount.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationStatistic.setBackgroundColor(getResources().getColor(R.color.navigation_background));
    }

    private void switchNavigation() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (showingFragment) {
            case AppInfo.SHOWING_EXPENSE_FRAGMENT:
                ExpenseFragment expenseFragment = (ExpenseFragment) getSupportFragmentManager().findFragmentByTag("EXPENSE");
                if (expenseFragment == null) {
                    expenseFragment = new ExpenseFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, expenseFragment, "EXPENSE");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_STATISTIC_FRAGMENT:
                StatisticFragment statisticFragment = (StatisticFragment) getSupportFragmentManager().findFragmentByTag("PURPOSE");
                if (statisticFragment == null) {
                    statisticFragment = new StatisticFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, statisticFragment, "PURPOSE");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_HISTORY_FRAGMENT:
                HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentByTag("HISTORY");
                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, historyFragment, "HISTORY");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_ACCOUNT_FRAGMENT:
                AccountFragment accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag("ACCOUNT");
                if (accountFragment == null) {
                    accountFragment = new AccountFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, accountFragment, "ACCOUNT");
                fragmentTransaction.commit();
                break;
        }

    }

    private void createDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (RelativeLayout) findViewById(R.id.left_drawer_navigation_layout);
        // Set the adapter for the list view
        String[] arrayString = new String[5];
        arrayString[0] = "Account";
        arrayString[1] = "PaymentToday";
        arrayString[2] = "History";
        arrayString[3] = "Setting";
        arrayString[4] = "About Us";

        drawerAdapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, arrayString);
        // Set the list's click listener
        mDrawerList = (ListView) findViewById(R.id.drawer_list_view);
        mDrawerList.setAdapter(drawerAdapter);
        drawerLeftNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(leftDrawer);
            }
        });
        //mDrawerLayout.setOn
    }


}
