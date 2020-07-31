package com.kyy.recuperationcourt.common.base.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kyy.recuperationcourt.common.util.SystemUtils;

/**
 * 数据库操作基础
 * Created by Eric.
 */
public class BaseDao {

    private static SQLiteDatabase sqLiteDatabase;// 数据库对象

    /**
     * 获取数据库对象(使用单列模式获取数据库对象)
     *
     * @return 数据库对象SQLiteDatabase
     */
    public static SQLiteDatabase getDatabase() {
        if (sqLiteDatabase == null) {
            // 数据库路径
            String databasePath = SystemUtils.getDatabasePath() + "/" + SystemUtils.DATABASE_FILENAME;
            // 获取数据库对象
            sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(databasePath, null);
        }
        return sqLiteDatabase;
    }

    /**
     * 执行增、删、改
     *
     * @param sql      执行语句
     * @param bindArgs 参数
     */
    public void execSql(String sql, Object[] bindArgs) {
        getDatabase().execSQL(sql, bindArgs);
    }

    /**
     * 获取统计数值
     *
     * @param sql           执行语句
     * @param selectionArgs 参数
     * @return 统计数值
     */
    public int getStatistic(String sql, String[] selectionArgs) {
        int count = 0;
        Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    count = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return count;
    }

    /**
     * 执行查询
     *
     * @param sql
     * @param selectionArgs
     * @return
     */
    public Cursor execQuery(String sql, String[] selectionArgs) {
        return getDatabase().rawQuery(sql, selectionArgs);
    }

}
