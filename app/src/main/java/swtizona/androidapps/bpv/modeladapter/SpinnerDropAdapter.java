package swtizona.androidapps.bpv.modeladapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import swtizona.androidapps.bpv.R;

public class SpinnerDropAdapter extends BaseAdapter {
    Activity activity;
    LayoutInflater inflater;
    String [] objects;
    private int color;

    public SpinnerDropAdapter(Activity activity, String [] objects, int color){
        this.activity = activity;
        this.objects = objects;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.color = color;
    }

    @Override
    public int getCount() {
        return objects.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.adapter_spinner, null);

        LinearLayout background = row.findViewById(R.id.row);
        TextView text = row.findViewById(R.id.spinnerDropItem);

        background.setBackgroundColor(color);
        text.setText(objects[position]);
        return row;
    }
}
