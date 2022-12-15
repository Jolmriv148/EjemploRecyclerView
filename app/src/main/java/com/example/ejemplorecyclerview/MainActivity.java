package com.example.ejemplorecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    String []nombres={"anana","banana","cereza","kiwi","limon","manzana","naranja",
            "sandia"};
    float []precios={170,120,260,155,130,180,120,39};
    int []fotos={R.drawable.anana,R.drawable.banana,R.drawable.cereza,R.drawable.kiwi,
            R.drawable.limon,R.drawable.manzana,R.drawable.naranja,R.drawable.sandia};

    RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rv);

        //Creamos un manager para el linearLayout que hemos creado
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //Lo asociamos con el RecyclerView
        rv.setLayoutManager(linearLayoutManager);
        //Creamos un objeto que hará de adaptador. La clase a la pertenece debe implementar RecyclerView.Adapter
        AdaptadorPersonalizado adaptadorPersonalizado=new AdaptadorPersonalizado();
        //Asociamos el adaptador que hemos creado con el RecyclerView
        rv.setAdapter(adaptadorPersonalizado);

    }

    private class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizadoHolder>{

        @NonNull
        @Override
        //Método al que se llamará para crear un Objeto ViewHolder que contendrá una vista en la que se ha inflado cada uno de los elementos que forman parte del RecyclerView
        public AdaptadorPersonalizadoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflar la vista fruta
            View viewAInflar = getLayoutInflater().inflate(R.layout.frutas, parent, false);
            return new AdaptadorPersonalizadoHolder(viewAInflar);
        }

        @Override
        //Cada vez que se cree un ViewHolder se llamará a esté método para fijar los valores que correspondan en el elemento del RecyclerView
        public void onBindViewHolder(@NonNull AdaptadorPersonalizadoHolder holder, int position) {
            holder.fijarValor(position);
        }

        @Override
        //Método que indica el número de elementos que debe tener el RecyclerView
        public int getItemCount() {
            return nombres.length;
        }
    }

    //Clase que debe heredar de RecyclerView.ViewHolder e implementar métodos para gestionar cada vista a añadir dentro del RecyclerView
    private class AdaptadorPersonalizadoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Declaramos los objetos de la vista a vista a inflar
        ImageView iv;
        TextView nombre,precio;

        public AdaptadorPersonalizadoHolder(@NonNull View itemView) {
            super(itemView);

            iv=itemView.findViewById(R.id.iv);
            nombre=itemView.findViewById(R.id.tvNombre);
            precio=itemView.findViewById(R.id.tvPrecio);

            //Si necesitamos realizar una acción al seleccionar un elemento la vista que toma como parámetro el viewHolder deberá tener un OnClickListener
            itemView.setOnClickListener((View.OnClickListener) this);


        }

        public void fijarValor(int position) {
            iv.setImageResource(fotos[position]);
            nombre.setText(nombres[position]);
            precio.setText(""+precios[position]);

        }

        @Override
        //Método que se ejecuta al pulsar sobre el conjunto de la vista
        public void onClick(View view) {
            //El método getLayoutPosition() nos da la posición que se encuentra la vista que gestiona el viewHolder dentro del RecyclerView
            Toast.makeText(getApplicationContext(),nombres[getLayoutPosition()],Toast.LENGTH_SHORT).show();
        }
    }
}