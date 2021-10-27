package com.example.spinnerpersonalizado2122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    //Utilizo tres vectores con los elementos que necesito
    String[] ciudades = {"Toledo", "Ciudad Real", "Albacete", "Cuenca", "Guadalajara"};
    String[] descripciones = {"La ciudad de Willy", "Qué gran aeropuerto",
            "El origen de tus cuchillos", "Que bonito es mirar a esta ciudad", "Necesito un chiste Jesús"};

    int imagenes[] = {R.drawable.toledo, R.drawable.ciudadreal, R.drawable.albacete,
            R.drawable.cuenca, R.drawable.guadalajara};

    //Creo una subclase de array adapter para personalizar el adaptador
    public class AdaptadorPersonalizado extends ArrayAdapter<String> {
        //Constructor de mi adaptador donde llamo al padre pasando el contexto (this)
        // el layout, y los elementos
        public AdaptadorPersonalizado(Context contexto, int txtViewResourceId, String[] objects) {
            super(contexto, txtViewResourceId, objects);
        }

        /**
         * Reescribo el método getDropDownView para que me devuelva una fila personalizada
         * en vez del elemento que se encuentra en esa posicion
         *
         * @param posicion
         * @param ViewConvertida
         * @param padre
         * @return
         */
        @Override
        public View getDropDownView(int posicion, View ViewConvertida, ViewGroup padre) {
            return crearFilaPersonalizada(posicion, ViewConvertida, padre);
        }

        /**
         * Método donde sobrescribo getView para que me devuelva mis filas personalizadas
         *
         * @param posicion
         * @param ViewConvertida
         * @param padre
         * @return
         */
        @Override
        public View getView(int posicion, View ViewConvertida, ViewGroup padre) {
            return crearFilaPersonalizada(posicion, ViewConvertida, padre);
        }

        /**
         * Método que me crea mis filas personalizadas pasando como parámetro la posición
         * la vista y la vista padre
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        public View crearFilaPersonalizada(int position, View convertView, ViewGroup parent) {

            //Declaro un objeto inflador de vistas
            LayoutInflater inflater = getLayoutInflater();
            //Declaro una vista de mi fila, y la preparo para inflarla con datos
            // Los parametros son: XML descriptivo
            // Vista padre
            // Booleano que indica si se debe ceñir a las características del padre
            View miFila = inflater.inflate(R.layout.lineaspiner, parent, false);

            //Fijamos el nombre de la ciudad
            TextView nombre = (TextView) miFila.findViewById(R.id.nombre);
            nombre.setText(ciudades[position]);

            //fijamos la descripción de la ciudad
            TextView descripcion = (TextView) miFila.findViewById(R.id.descripcion);
            descripcion.setText(descripciones[position]);

            //Fijamos la imagen de la ciudad
            ImageView imagen = (ImageView) miFila.findViewById(R.id.imagenCiudad);
            imagen.setImageResource(imagenes[position]);

            //Devolvemos la vista inflada con los datos fijados
            return miFila;

        }
    }

    /**
     * Método onCreate donde creo la actividad.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaro el spinner
        Spinner selectorCiudades = (Spinner) findViewById(R.id.spinner);
        //Declaro un adaptador personalizado, donde paso como parámetros
        // la vista actual, el xml donde defino la vista y el array ciudades
        AdaptadorPersonalizado a = new AdaptadorPersonalizado(this, R.layout.lineaspiner, ciudades);
        //Fijamos el adaptador al spinner
        selectorCiudades.setAdapter(a);
        //Nos suscribimos al listener
        selectorCiudades.setOnItemSelectedListener(this);
    }

    /**
     * Método donde implementamos cuando se selecciona un elemento del spinner
     *
     * @param padre
     * @param view
     * @param position
     * @param id
     */
    public void onItemSelected(AdapterView<?> padre, View view, int position, long id) {
        TextView c = (TextView) view.findViewById(R.id.nombre);
        TextView seleccion = (TextView) findViewById(R.id.ciudadSeleccionada);

        seleccion.setText(c.getText());
    }

    public void onNothingSelected(AdapterView<?> parent) {
        TextView seleccion = (TextView) findViewById(R.id.ciudadSeleccionada);
        seleccion.setText("nada seleccionado!");
    }


    /**
     * Método que me crea el menú del action bar, inflándolo a partir
     * del archivo xml my.xml
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    /**
     * Método que responde a los eventos de click de los elementos
     * colocado en el action bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}