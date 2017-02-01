package com.example.linxi.qzone.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linxi on 2017/1/28.
 */

public class DBHelper {
    private SQLiteDatabase db;
    private DBManager dbManager;
    private int version=1;

    private DBHelper(){

    }
    /**
     * 实例化DBHelper
     */
    public DBHelper(Context oContext){
        dbManager=new DBManager(oContext,version);
        db=dbManager.getWritableDatabase();
    }
    /**
     * 插入数据到指定表
     * 以Map格式插入
     */
    public boolean insertModel(String tableName, Map<String,String>parm){
        boolean result=false;
        if (parm!=null&&parm.size()>0){
            ContentValues parmValues=new ContentValues();
            for (String rowName:parm.keySet()){
                parmValues.put(rowName,parm.get(rowName));
            }
            result=db.insert(tableName,null,parmValues)>0;
        }
        return result;
    }

    /**
     * 删除指定表的数据
     */
    public boolean deleteModel(String tableName,String whereString,String[]whereParm){
        boolean result=false;
        result=db.delete(tableName,whereString,whereParm)>0;
        return result;
    }
    /**
     * 更新指定表的数据
     */
    public boolean updateModel(String tableName,Map<String,String>parm,String whereString,String[]whereParm){
        boolean result=false;
        if (parm!=null&&parm.size()>0){
            ContentValues parmValues=new ContentValues();
            for (String rowName:parm.keySet()){
                parmValues.put(rowName,parm.get(rowName));
            }
            result=db.update(tableName,parmValues,whereString,whereParm)>0;
        }
        return result;
    }
    /**
     * 根据sql语句查询指定的对象
     * @param sql
     *              要执行的sql语句 如：select * from userinfo where id=?
     * @param parm
     *              执行sql语句的参数，没有值为null，有则为String[]{'1'}
     * @return
     *              键值对
     */
    public Map<String,String> getModel(String sql,String[]parm){
        Map<String,String>oMap=new HashMap<>();
        Cursor cursor=db.rawQuery(sql,parm);
        if (cursor.moveToNext()){
            oMap=getDateItem(cursor);
        }
        return oMap;
    }
        //多态，重载？
    public Map<String,String>getModel(String sql){
       return getModel(sql,null);
    }

    /**
     * 根据sql语句查询数据库指定的对象集合。
     * @param sql
     * @param parm
     * @return
     */
    public List<Map<String,String>>getModels(String sql,String[]parm){
        List<Map<String,String>>dataList=null;
        Cursor cursor=db.rawQuery(sql,parm);
        if (cursor!=null&&cursor.getCount()>0){
            dataList=new ArrayList<Map<String,String>>();
            while (cursor.moveToNext()){
                dataList.add(getDateItem(cursor));
            }
        }
        return dataList;
    }
    public List<Map<String,String>>getModels(String sql){
        return getModels(sql,null);
    }

    /**
     * 根据传入的Cursor对象，封装成HashMap，key为表列名，value为对应的值
     * @param cursor
     * @return
     */
    private Map<String, String> getDateItem(Cursor cursor) {
        Map<String,String>oMap=null;
        if (cursor!=null&&cursor.getCount()>0){
            //为什么用HashMap？
            oMap=new HashMap<String,String>();
            for (String rowsName:cursor.getColumnNames()){
                //for(int i=0;i<cursor.getClumNames().size();i++){
                //  rowsName=cursor.getClumNames().get(i);
                // }
                oMap.put(rowsName,cursor.getString(cursor.getColumnIndex(rowsName)));
            }
        }
        return oMap;
    }
    public void closeDB(){
        db.close();
    }
}
