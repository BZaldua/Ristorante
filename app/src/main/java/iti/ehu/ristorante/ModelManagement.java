package iti.ehu.ristorante;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ModelManagement extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restauranteDB";

    private static final String TABLE_DISHES = "platos";
    private static final String TABLE_COMMAND = "comanda";
    private static final String TABLE_USER = "usuarios";

    private static final String KEY_ID = "id";
    private static final String DISH_TYPE = "tipoDePlato";
    private static final String DISH_NAME = "nombrePlato";
    private static final String DISH_PRICE = "precioPlato";

    private static final String COMMAND_VALUE = "contenidoComanda";
    private static final String COMMAND_PRICE = "precioTotal";

    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";


    public ModelManagement(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla PLATOS
        String CREATE_DISHES_TABLE = "CREATE TABLE " + TABLE_DISHES + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + DISH_TYPE + " INTEGER," + DISH_NAME + " TEXT,"+
                DISH_PRICE + " REAL)";
        db.execSQL(CREATE_DISHES_TABLE);

        // Crear tabla COMANDOS
        String CREATE_COMMAND_TABLE = "CREATE TABLE " + TABLE_COMMAND + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + COMMAND_VALUE + " TEXT, " + COMMAND_PRICE + " REAL)";
        db.execSQL(CREATE_COMMAND_TABLE);

        //Crear tabla USUARIOS
        String CREAT_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + USER_USERNAME + "TEXT, " + USER_PASSWORD + " TEXT)";
        db.execSQL(CREAT_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISHES);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COMMAND);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER);
        onCreate(db);
    }


    public void addDish(Dish dish){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DISH_TYPE, dish.getDishType());
        values.put(DISH_NAME, dish.getName());
        values.put(DISH_PRICE, dish.getPrice());

        db.insert(TABLE_DISHES, null, values);
        db.close();
    }

    public void addCommand(Command command){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COMMAND_VALUE, command.getDishListString());
        values.put(COMMAND_PRICE, command.getTotalPrice());

        db.insert(TABLE_COMMAND, null, values);
        db.close();
    }

    public List<Dish> getDishes(){
        List<Dish> dishesList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+ TABLE_DISHES;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Dish dish = new Dish();
                dish.setId(Integer.parseInt(cursor.getString(0)));
                dish.setDishType(Integer.parseInt(cursor.getString(1)));
                dish.setName(cursor.getString(2));
                dish.setPrice(Float.parseFloat(cursor.getString(3)));

                dishesList.add(dish);
            }while(cursor.moveToNext());
        }

        return dishesList;
    }

    public List<Command> getCommands(){
        List<Command> commandsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+ TABLE_COMMAND;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Command command = new Command();
                command.setId(Integer.parseInt(cursor.getString(0)));

                String[] dishesSplit = cursor.getString(1).split("/");
                ArrayList<Dish> dishesList = new ArrayList<>();
                for(String dishString:dishesSplit){
                    String[] dishSplit = dishString.split(",");
                    Dish lag = new Dish(Integer.parseInt(dishSplit[0]), dishSplit[1], Float.parseFloat(dishSplit[2]));
                    dishesList.add(lag);
                }
                command.setDish(dishesList);

                commandsList.add(command);
            }while(cursor.moveToNext());
        }

        return commandsList;
    }


    public void dropTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQueryDishes = "DELETE FROM " + TABLE_DISHES;
        String deleteQueryCommands = "DELETE FROM "+TABLE_COMMAND;
        String deleteQueryUsers = "DELETE FROM "+TABLE_USER;

        db.execSQL(deleteQueryCommands);
        db.execSQL(deleteQueryDishes);
        db.execSQL(deleteQueryUsers);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, user.getUserName());
        values.put(USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }


    public boolean login(User user){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_USER + "WHERE username = " + user.getUserName() + "AND password = " + user.getPassword();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.getCount() != 1)
            return true;
        return false;
    }

}
