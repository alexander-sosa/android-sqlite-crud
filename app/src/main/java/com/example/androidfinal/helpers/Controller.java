package com.example.androidfinal.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.androidfinal.models.ModeloArtist;
import com.example.androidfinal.models.ModeloCancion;
import com.example.androidfinal.models.ModeloItem;

import java.util.ArrayList;

public class Controller {
    // aqui va a estar toda la logica de la BDD
    private BDDHelper ayudanteBaseDeDatos;
    private String ARTISTA = "artista";
    private String CANCION = "cancion";

    public Controller(Context contexto) {
        ayudanteBaseDeDatos = new BDDHelper(contexto);
    }

    // Campos de Soporte al Artista

    public long nuevoArtista(ModeloArtist artista) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", artista.getNombre());
        return baseDeDatos.insert(ARTISTA, null, valoresParaInsertar);
    }

    public int cambioArtista(ModeloArtist artistaEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", artistaEditado.getNombre());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(artistaEditado.getId())};
        return baseDeDatos.update(ARTISTA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminaArtista(ModeloArtist artista) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(artista.getId())};
        return baseDeDatos.delete(ARTISTA, "id = ?", argumentos);
    }

    public int bajaProducto(ModeloArtist artistaEditado) {
        ModeloCancion aux = buscaArtistadeCancion(new ModeloCancion("", artistaEditado.getId()));
        if(aux != null){
            return -1;
        }
        else {
            SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues valoresParaActualizar = new ContentValues();
            valoresParaActualizar.put("nombre", artistaEditado.getNombre());
            // where id...
            String campoParaActualizar = "id = ?";
            String[] argumentosParaActualizar = {String.valueOf(artistaEditado.getId())};
            //return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
            return baseDeDatos.delete(ARTISTA, campoParaActualizar, argumentosParaActualizar);
        }
    }

    public ArrayList<ModeloArtist> obtenerArtistas() {
        ArrayList<ModeloArtist> listaProductos = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"nombre", "id"};
        Cursor cursor = baseDeDatos.query(
                ARTISTA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            Log.i("phx", "No hay ningun dato ");
            // Salimos aquí porque hubo un error, regresar
            // lista vacía
            return listaProductos;
        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return listaProductos;
        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de productos
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            long idProducto = cursor.getLong(1);
            ModeloArtist artistaObtenidaDeBD = new ModeloArtist(nombreObtenidoDeBD, idProducto);
            listaProductos.add(artistaObtenidaDeBD);
        } while (cursor.moveToNext());
        cursor.close();
        return listaProductos;
    }

    public ModeloArtist buscaArtista(ModeloArtist reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, id  FROM "+ARTISTA+" WHERE id = '"+ reg.getId()+"' ",null);
        ModeloArtist res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloArtist(nombre, id);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }

    // Campos de Soporte a las Canciones
    public long nuevaCancion(ModeloCancion cancion) {
        // writable porque vamos a insertar
        ModeloArtist aux = buscaArtista(new ModeloArtist("", cancion.getIdArtista()));
        if(aux == null){
            return -1;
        }
        else {
            SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues valoresParaInsertar = new ContentValues();
            valoresParaInsertar.put("nombre", cancion.getNombre());
            valoresParaInsertar.put("artista", cancion.getIdArtista());
            Log.i("phx", "insertando... " + valoresParaInsertar.toString());
            return baseDeDatos.insert(CANCION, null, valoresParaInsertar);
        }
    }

    public int cambioCancion(ModeloCancion cancionEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", cancionEditada.getNombre());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(cancionEditada.getId())};
        return baseDeDatos.update(CANCION, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminaCancion(ModeloCancion cancion) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(cancion.getId())};
        return baseDeDatos.delete(CANCION, "id = ?", argumentos);
    }

    public int bajaCancion(ModeloCancion artistEditado) {
            SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
            ContentValues valoresParaActualizar = new ContentValues();
            valoresParaActualizar.put("nombre", artistEditado.getNombre());
            // where id...
            String campoParaActualizar = "id = ?";
            String[] argumentosParaActualizar = {String.valueOf(artistEditado.getId())};
            //return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
            return baseDeDatos.delete(CANCION, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<ModeloCancion> obtenerCanciones() {
        ArrayList<ModeloCancion> listaProductos = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"nombre", "artista", "id"};
        Cursor cursor = baseDeDatos.query(
                CANCION,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            Log.i("phx", "No hay ningun dato ");
            // Salimos aquí porque hubo un error, regresar
            // lista vacía
            return listaProductos;
        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return listaProductos;
        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de productos
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            long idArtista = cursor.getLong(1);
            long idCancion = cursor.getLong(2);

            //ModeloArtist aux = buscaArtista(new ModeloArtist("", idArtista));
            //ModeloCancion artistaObtenidaDeBD = new ModeloCancion(nombreObtenidoDeBD, aux.getNombre(), idCancion);
            ModeloArtist aux = buscaArtista(new ModeloArtist("", idArtista));
            Log.i("phx", "Modelo Artista... " + aux.toString());
            ModeloCancion artistaObtenidaDeBD = new ModeloCancion(nombreObtenidoDeBD, aux.getNombre(), idCancion);
            Log.i("phx", "Llegando... " + artistaObtenidaDeBD.toString());
            listaProductos.add(artistaObtenidaDeBD);
        } while (cursor.moveToNext());
        cursor.close();
        return listaProductos;
    }

    public ModeloCancion buscaCancion(ModeloCancion reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, artista, id  FROM "+CANCION+" WHERE id = '"+ reg.getId()+"' ",null);
        ModeloCancion res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int idArtista = c.getInt(c.getColumnIndex("artista"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloCancion(nombre, idArtista, id);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }

    public ModeloCancion buscaArtistadeCancion(ModeloCancion reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, artista, id  FROM "+CANCION+" WHERE artista = '"+ reg.getId()+"' ",null);
        ModeloCancion res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int idArtista = c.getInt(c.getColumnIndex("artista"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloCancion(nombre, idArtista, id);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }

}
