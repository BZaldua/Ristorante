package iti.ehu.ristorante;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zaldua on 03/01/2018.
 */

public class DishAdapter extends ArrayAdapter<Dish> {

    public DishAdapter(Context context, ArrayList<Dish> dishes){
        super(context, 0, dishes);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        Dish dish = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, parent, false);

        TextView txtNum = (TextView) convertView.findViewById(R.id.row_num);
        TextView txtName = (TextView) convertView.findViewById(R.id.row_dish);

        txtName.setText(dish.getName());
        txtNum.setText(String.valueOf(Math.round(dish.getPrice())));

        return convertView;

    }

}
