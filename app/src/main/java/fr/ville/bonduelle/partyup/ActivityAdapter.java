package fr.ville.bonduelle.partyup;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * Created by Thomas on 08/11/2016.
 * Récupère les activités depuis le serveur et les fournis à la GridView ou à la liste qui en a besoin
 */
public class ActivityAdapter extends BaseAdapter {
    private Context m_context;
    private LayoutInflater m_inflater;
    private int m_screenWidth;
    public ActivityAdapter(Context c) {
        m_context = c;
        m_inflater = LayoutInflater.from(m_context);

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE))
            .getDefaultDisplay()
                .getMetrics(metrics);
        m_screenWidth = metrics.widthPixels;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            rootView = m_inflater.inflate(R.layout.event_list_item, parent, false);
        }

        ((ImageView) rootView.findViewById(R.id.eventThumbPicture))
                .setImageResource(mThumbIds[position]);

        return rootView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.thumbnail_1,
            R.drawable.thumbnail_4,
            R.drawable.thumbnail_6,
            R.drawable.thumbnail_2,
            R.drawable.thumbnail_4,
            R.drawable.thumbnail_6
    };
}