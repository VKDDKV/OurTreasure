package home;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mannermakethman.vkd.ourtreasure.R;

import java.util.List;

import account.AccountFragment;
import base.ActivityBase;
import core.AppInfo;
import expense.ExpenseFragment;
import history.HistoryFragment;
import purpose.PurposeFragment;

public class HomeActivity extends ActivityBase {
    List<String> buttonNavigationViewList;
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ArrayAdapter<String> drawerAdapter;
    Button drawerLeftNavigation;
    RelativeLayout leftDrawer;
    HomeHeaderFragment headerFragment;
    LinearLayout navigationExpense, navigationHistory, navigationAccount, navigationPurpose;
    int showingFragment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //set main Fragment
        ExpenseFragment expenseFragment = new ExpenseFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, expenseFragment, "EXPENSE");
        fragmentTransaction.commit();
        //set showingFragment
        showingFragment = AppInfo.SHOWING_EXPENSE_FRAGMENT;
        navigationExpense = (LinearLayout) findViewById(R.id.navigation_expend);
        navigationAccount = (LinearLayout) findViewById(R.id.navigation_account);
        navigationPurpose = (LinearLayout) findViewById(R.id.navigation_purpose);
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

    public void navigationPurpose(View view) {
        showingFragment = AppInfo.SHOWING_PURPOSE_FRAGMENT;
        switchNavigation();
        clearBackgroundNavigation();
        navigationPurpose.setBackgroundColor(getResources().getColor(R.color.navigation_background_selected));


    }

    private void clearBackgroundNavigation() {
        navigationHistory.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationExpense.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationAccount.setBackgroundColor(getResources().getColor(R.color.navigation_background));
        navigationPurpose.setBackgroundColor(getResources().getColor(R.color.navigation_background));
    }

    private void switchNavigation() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        switch (showingFragment) {
            case AppInfo.SHOWING_EXPENSE_FRAGMENT:
                ExpenseFragment expenseFragment = (ExpenseFragment) getFragmentManager().findFragmentByTag("EXPENSE");
                if (expenseFragment == null) {
                    expenseFragment = new ExpenseFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, expenseFragment, "EXPENSE");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_PURPOSE_FRAGMENT:
                PurposeFragment purposeFragment = (PurposeFragment) getFragmentManager().findFragmentByTag("PURPOSE");
                if (purposeFragment == null) {
                    purposeFragment = new PurposeFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, purposeFragment, "PURPOSE");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_HISTORY_FRAGMENT:
                HistoryFragment historyFragment = (HistoryFragment) getFragmentManager().findFragmentByTag("HISTORY");
                if (historyFragment == null) {
                    historyFragment = new HistoryFragment();
                }
                fragmentTransaction.replace(R.id.fragment_content, historyFragment, "HISTORY");
                fragmentTransaction.commit();
                break;
            case AppInfo.SHOWING_ACCOUNT_FRAGMENT:
                AccountFragment accountFragment = (AccountFragment) getFragmentManager().findFragmentByTag("ACCOUNT");
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
