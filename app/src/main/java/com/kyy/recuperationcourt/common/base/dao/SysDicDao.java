package com.kyy.recuperationcourt.common.base.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.kyy.recuperationcourt.common.model.entity.consult.NewsType;
import com.kyy.recuperationcourt.common.util.DBUtil;

import java.util.List;

/**
 * 数据字典dao
 * Created by DefterosChen on 2020-01-04.
 */
public class SysDicDao extends BaseDao {


    /**
     * 批量增加新闻类别
     *
     * @param dicList
     * @throws Exception
     */
    public void bathAddSysDicType(List<NewsType> dicList) throws Exception {

        String sql = "insert into TYPE_NEWS " +
                " (id, pid, level, name, description, icon, type, alias, systemId, orders, createTime) " +
                " values (?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            SQLiteStatement stat = getDatabase().compileStatement(sql); //预编译Sql语句避免重复解析Sql语句
            getDatabase().beginTransaction();//开始事务
            for (NewsType dic : dicList) {
                String[] bindArgs = new String[]{dic.getId()
                        , dic.getPid() == null ? "" : dic.getPid()
                        , dic.getLevel() + ""
                        , dic.getName()
                        , dic.getDescription()
                        , dic.getIcon() == null ? "" : dic.getIcon()
                        , dic.getType() + ""
                        , dic.getAlias()
                        , dic.getSystemId() + ""
                        , dic.getOrders() + ""
                        , dic.getCreateTime() == null ? "" : dic.getCreateTime()
                };
                stat.bindAllArgsAsStrings(bindArgs);
                long result = stat.executeInsert();
                if (result < 0) {
                    return;
                }
            }
            getDatabase().setTransactionSuccessful();  //控制回滚，如果不设置此项自动回滚
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" 新闻类别 导入数据库出错！！");
        } finally {
            getDatabase().endTransaction();  //事务提交
        }
    }


    /**
     * 得到新闻类别的数据字典项
     *
     * @return
     */
    public List<NewsType> queryNewsDataList() {
        String sql = "select *  from type_news ";

        String[] selectionArgs = new String[]{};
        Cursor cursor = execQuery(sql, selectionArgs);
        List<NewsType> dicList = (List<NewsType>) DBUtil.cursorToList(cursor, NewsType.class);
        return dicList;
    }

}
