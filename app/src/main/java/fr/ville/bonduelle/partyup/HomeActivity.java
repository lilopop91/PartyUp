package fr.ville.bonduelle.partyup;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    /**
     * Adapter qui garde tous les Fragment en mémoire.
     * Utiliser FragmentStatePagerAdapter si on a des problèmes de mémoire
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Fragment qui affiche une liste d'événements passés (section 0) ou à venir (section 1)
     */
    public static class EventListFragment extends Fragment {
        /**
         * L'argument du fragment qui représente le numéro de la section.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public EventListFragment() {
        }

        /**
         * Retourne une nouvelle instance du fragment pour une section donnée
         */
        public static EventListFragment newInstance(int sectionNumber) {
            EventListFragment fragment = new EventListFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Récupère le layout de la liste d'événements
            LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.event_list_home_screen, container, false);
            GridView listView = (GridView) rootView.findViewById(R.id.gridView);
            listView.setAdapter(new ActivityAdapter(getContext()));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Toast.makeText(getContext(), "" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });
            /*// Met un petit texte pour bien montrer que le Fragment a récupéré son numéro de section
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            // Selon la section dans laquelle on est, on affiche un contenu différent
            switch(getArguments().getInt(ARG_SECTION_NUMBER)){
                case 0:
                    textView.setText(getString(R.string.section_format,
                            getString(R.string.past_events_tab)));
                    break;
                case 1:
                    textView.setText(getString(R.string.section_format,
                            getString(R.string.coming_events_tab)));
                    break;
            }*/

            return rootView;
        }
    }

    /**
     * Fragment qui affiche la présentation de l'événement en cours
     */
    public static class CurrentEventFragment extends Fragment {
        public CurrentEventFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Récupère le layout de l'événement en cours
            View rootView = inflater.inflate(R.layout.event_home_screen, container, false);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:
                case 1:
                    return EventListFragment.newInstance(position);
                default:
                    return new CurrentEventFragment();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.past_events_tab);
                case 1:
                    return getString(R.string.coming_events_tab);
                case 2:
                    return getString(R.string.current_events_tab);
            }
            return null;
        }
    }
}
