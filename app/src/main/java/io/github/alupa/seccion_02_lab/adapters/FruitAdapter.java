package io.github.alupa.seccion_02_lab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.alupa.seccion_02_lab.R;
import io.github.alupa.seccion_02_lab.models.Fruit;

/**
 * Created by Alvaro on 10-09-2017.
 */

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruits;

    public FruitAdapter(Context context, int layout, List<Fruit> fruits) {
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }

    @Override
    public int getCount() {
        return this.fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return this.fruits.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View Holder pattern
        ViewHolder holder;

        if (convertView == null){
            // Inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            // Referenciamos el elemento a modificar y lo rellenamos
            holder.imageViewIcon = (ImageView) convertView.findViewById(R.id.imageViewIcon);
            holder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
            holder.textViewOrigin = (TextView) convertView.findViewById(R.id.textViewOrigin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Nos traemos el valor actual dependiente de la posicion
        int currentIcon = fruits.get(position).getIcon();
        String currentName = fruits.get(position).getName();
        String currentOrigin = fruits.get(position).getOrigin();

        // Referenciamos el elemento a modificar y lo rellenamos
        holder.imageViewIcon.setImageResource(currentIcon);
        holder.textViewName.setText(currentName);
        holder.textViewOrigin.setText(currentOrigin);

        // Devolvemos la vista inflada y modificada con nuestros datos
        return convertView;
    }

    static class ViewHolder {
        private ImageView imageViewIcon;
        private TextView textViewName;
        private TextView textViewOrigin;
    }
}
