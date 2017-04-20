package com.timeron.timeronwallet.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.timeron.timeronwallet.R;
import com.timeron.timeronwallet.resource.WalletDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Timeron on 2017-03-25.
 *
 */

public class MultiSelectAdapter extends BaseAdapter {

    List<? extends Map<String, String>> data = new ArrayList<>();
    List<Long> selectedIds = new ArrayList<>();

    Context context = null;
    int resource;
    String[] from;
    int[] to;

    private static final String ID = "_id";

    public MultiSelectAdapter(Context context, List<Map<String, String>> data,
                       @LayoutRes int resource, String[] from, @IdRes int[] to){
        this.context = context;
        this.data = data;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Map<String, String> map = data.get(position);
        return Long.parseLong(map.get("_id"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.row_record, parent, false);
        } else {
            view = convertView;
        }

        Map<String, String> row = (Map<String, String>) getItem(position);
        for (int i = 0; i < from.length; i++) {
            TextView textView = (TextView) view.findViewById(to[i]);
            if (textView != null) {
                textView.setText(row.get(from[i]));
            }
        }
        ImageView sync = (ImageView) view.findViewById(R.id.lr_sync);
        if(Integer.parseInt(row.get(WalletDAO.SYNC)) > 0){
            sync.getDrawable().setColorFilter( 0xff00ff00, PorterDuff.Mode.MULTIPLY );
        }else{
            sync.getDrawable().setColorFilter( 0xffffffff, PorterDuff.Mode.MULTIPLY );
        }

        if (selectedIds.contains(Long.parseLong(row.get(ID)))) {
            view.findViewById(R.id.lr_layout_comtainer).setBackgroundColor(ResourcesCompat.getColor(view.getResources(), R.color.colorGreyDark, null));
            view.findViewById(R.id.lr_check).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.lr_layout_comtainer).setBackgroundColor(ResourcesCompat.getColor(view.getResources(), R.color.colorGreyToBlack, null));
            view.findViewById(R.id.lr_check).setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public void addSelection(Long id){
        selectedIds.add(id);
    }

    public void removeSelection(Long id){
        selectedIds.remove(id);
    }

    public List<Long> getSelectedIds(){
        return selectedIds;
    }

    public void updateItem(int position, String amount, String value) {
        Map<String, String> map = data.get(position);
        map.put(amount, value);
    }
}
