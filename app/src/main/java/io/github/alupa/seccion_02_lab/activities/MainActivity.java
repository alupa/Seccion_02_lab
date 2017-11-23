package io.github.alupa.seccion_02_lab.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.alupa.seccion_02_lab.R;
import io.github.alupa.seccion_02_lab.adapters.FruitAdapter;
import io.github.alupa.seccion_02_lab.models.Fruit;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Fruit> fruits;
    private ListView listView;
    private GridView gridView;
    private FruitAdapter adapterListView;
    private FruitAdapter adapterGridView;
    private MenuItem itemListView;
    private MenuItem itemGridView;

    private int counter = 0;
    public static final int SWITCH_TO_LIST_VIEW = 1;
    public static final int SWITCH_TO_GRID_VIEW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Datos a mostrar
        fruits = new ArrayList<Fruit>();
        fruits.add(new Fruit("Banana", "Gran Canaria", R.mipmap.banana));
        fruits.add(new Fruit("Apple", "Madrid", R.mipmap.apple));
        fruits.add(new Fruit("Watermelon", "Camerun", R.mipmap.watermelon));

        listView = (ListView) findViewById(R.id.listView);
        gridView = (GridView) findViewById(R.id.gridView);

        listView.setOnItemClickListener(this);
        gridView.setOnItemClickListener(this);

        adapterListView = new FruitAdapter(this, R.layout.list_item, fruits);
        listView.setAdapter(adapterListView);

        adapterGridView = new FruitAdapter(this, R.layout.grid_item, fruits);
        gridView.setAdapter(adapterGridView);

        registerForContextMenu(listView);
        registerForContextMenu(gridView);
    }

    // Inflamos layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        itemListView = menu.findItem(R.id.list_view_option);
        itemGridView = menu.findItem(R.id.grid_view_option);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                // Agregamos nueva fruta
                this.fruits.add(new Fruit("Added nº " + (++counter), "Unknown", R.mipmap.ic_launcher));
                // Notificamos al adaptador del cambio producido
                this.notifyView();
                return true;
            case R.id.list_view_option:
                // Cambiamos la pantalla por un listview
                this.switchToListOrGridView(SWITCH_TO_LIST_VIEW);
                this.notifyView();
                return true;
            case R.id.grid_view_option:
                // Cambiamos la pnatalla por un gridview
                this.switchToListOrGridView(SWITCH_TO_GRID_VIEW);
                this.notifyView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void switchToListOrGridView(int option) {
        if (option == SWITCH_TO_LIST_VIEW){
            if (listView.getVisibility() == View.INVISIBLE){
                gridView.setVisibility(View.INVISIBLE);
                itemGridView.setVisible(true);
                listView.setVisibility(View.VISIBLE);
                itemListView.setVisible(false);
            }
        } else if (option == SWITCH_TO_GRID_VIEW){
            if (gridView.getVisibility() == View.INVISIBLE){
                listView.setVisibility(View.INVISIBLE);
                itemListView.setVisible(true);
                gridView.setVisibility(View.VISIBLE);
                itemGridView.setVisible(false);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fruit fruit = this.fruits.get(position);
        if (!fruit.getOrigin().equals("Unknown")){
            Toast.makeText(this, "The best " + fruit.getName() + " you will find in " + fruit.getOrigin(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sorry, we have no idea about this " + fruit.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void notifyView(){
        this.adapterListView.notifyDataSetChanged();
        this.adapterGridView.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_fruit:
                //Borramos fruta seleccionada
                this.fruits.remove(info.position);
                this.notifyView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
