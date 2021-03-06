package com.udit.bankexam.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.udit.bankexam.bean.ExamHistoryBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EXAM_HISTORY_BEAN".
*/
public class ExamHistoryBeanDao extends AbstractDao<ExamHistoryBean, String> {

    public static final String TABLENAME = "EXAM_HISTORY_BEAN";

    /**
     * Properties of entity ExamHistoryBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, String.class, "_id", true, "_ID");
        public final static Property Eid_oid = new Property(1, String.class, "eid_oid", false, "eid_oid");
        public final static Property Flag_zhineng = new Property(2, boolean.class, "flag_zhineng", false, "flag_zhineng");
        public final static Property Flag_main = new Property(3, boolean.class, "flag_main", false, "flag_main");
        public final static Property Flag_oid = new Property(4, boolean.class, "flag_oid", false, "flag_oid");
        public final static Property Name = new Property(5, String.class, "name", false, "name");
        public final static Property Flag_cl = new Property(6, boolean.class, "flag_cl", false, "flag_cl");
        public final static Property Selected_exam = new Property(7, int.class, "selected_exam", false, "selected_exam");
        public final static Property User_id = new Property(8, String.class, "user_id", false, "user_id");
    }


    public ExamHistoryBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ExamHistoryBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EXAM_HISTORY_BEAN\" (" + //
                "\"_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: _id
                "\"eid_oid\" TEXT," + // 1: eid_oid
                "\"flag_zhineng\" INTEGER NOT NULL ," + // 2: flag_zhineng
                "\"flag_main\" INTEGER NOT NULL ," + // 3: flag_main
                "\"flag_oid\" INTEGER NOT NULL ," + // 4: flag_oid
                "\"name\" TEXT," + // 5: name
                "\"flag_cl\" INTEGER NOT NULL ," + // 6: flag_cl
                "\"selected_exam\" INTEGER NOT NULL ," + // 7: selected_exam
                "\"user_id\" TEXT);"); // 8: user_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EXAM_HISTORY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ExamHistoryBean entity) {
        stmt.clearBindings();
 
        String _id = entity.get_id();
        if (_id != null) {
            stmt.bindString(1, _id);
        }
 
        String eid_oid = entity.getEid_oid();
        if (eid_oid != null) {
            stmt.bindString(2, eid_oid);
        }
        stmt.bindLong(3, entity.getFlag_zhineng() ? 1L: 0L);
        stmt.bindLong(4, entity.getFlag_main() ? 1L: 0L);
        stmt.bindLong(5, entity.getFlag_oid() ? 1L: 0L);
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
        stmt.bindLong(7, entity.getFlag_cl() ? 1L: 0L);
        stmt.bindLong(8, entity.getSelected_exam());
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(9, user_id);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ExamHistoryBean entity) {
        stmt.clearBindings();
 
        String _id = entity.get_id();
        if (_id != null) {
            stmt.bindString(1, _id);
        }
 
        String eid_oid = entity.getEid_oid();
        if (eid_oid != null) {
            stmt.bindString(2, eid_oid);
        }
        stmt.bindLong(3, entity.getFlag_zhineng() ? 1L: 0L);
        stmt.bindLong(4, entity.getFlag_main() ? 1L: 0L);
        stmt.bindLong(5, entity.getFlag_oid() ? 1L: 0L);
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(6, name);
        }
        stmt.bindLong(7, entity.getFlag_cl() ? 1L: 0L);
        stmt.bindLong(8, entity.getSelected_exam());
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(9, user_id);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ExamHistoryBean readEntity(Cursor cursor, int offset) {
        ExamHistoryBean entity = new ExamHistoryBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // eid_oid
            cursor.getShort(offset + 2) != 0, // flag_zhineng
            cursor.getShort(offset + 3) != 0, // flag_main
            cursor.getShort(offset + 4) != 0, // flag_oid
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // name
            cursor.getShort(offset + 6) != 0, // flag_cl
            cursor.getInt(offset + 7), // selected_exam
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // user_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ExamHistoryBean entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setEid_oid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFlag_zhineng(cursor.getShort(offset + 2) != 0);
        entity.setFlag_main(cursor.getShort(offset + 3) != 0);
        entity.setFlag_oid(cursor.getShort(offset + 4) != 0);
        entity.setName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFlag_cl(cursor.getShort(offset + 6) != 0);
        entity.setSelected_exam(cursor.getInt(offset + 7));
        entity.setUser_id(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ExamHistoryBean entity, long rowId) {
        return entity.get_id();
    }
    
    @Override
    public String getKey(ExamHistoryBean entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ExamHistoryBean entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
