package com.udit.bankexam.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamHistoryBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.bean.FavoriteRecord;

import com.udit.bankexam.db.ExamBeanDao;
import com.udit.bankexam.db.ExamHistoryBeanDao;
import com.udit.bankexam.db.ExamNoteBeanDao;
import com.udit.bankexam.db.ExamOptionBeanDao;
import com.udit.bankexam.db.FavoriteRecordDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig examBeanDaoConfig;
    private final DaoConfig examHistoryBeanDaoConfig;
    private final DaoConfig examNoteBeanDaoConfig;
    private final DaoConfig examOptionBeanDaoConfig;
    private final DaoConfig favoriteRecordDaoConfig;

    private final ExamBeanDao examBeanDao;
    private final ExamHistoryBeanDao examHistoryBeanDao;
    private final ExamNoteBeanDao examNoteBeanDao;
    private final ExamOptionBeanDao examOptionBeanDao;
    private final FavoriteRecordDao favoriteRecordDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        examBeanDaoConfig = daoConfigMap.get(ExamBeanDao.class).clone();
        examBeanDaoConfig.initIdentityScope(type);

        examHistoryBeanDaoConfig = daoConfigMap.get(ExamHistoryBeanDao.class).clone();
        examHistoryBeanDaoConfig.initIdentityScope(type);

        examNoteBeanDaoConfig = daoConfigMap.get(ExamNoteBeanDao.class).clone();
        examNoteBeanDaoConfig.initIdentityScope(type);

        examOptionBeanDaoConfig = daoConfigMap.get(ExamOptionBeanDao.class).clone();
        examOptionBeanDaoConfig.initIdentityScope(type);

        favoriteRecordDaoConfig = daoConfigMap.get(FavoriteRecordDao.class).clone();
        favoriteRecordDaoConfig.initIdentityScope(type);

        examBeanDao = new ExamBeanDao(examBeanDaoConfig, this);
        examHistoryBeanDao = new ExamHistoryBeanDao(examHistoryBeanDaoConfig, this);
        examNoteBeanDao = new ExamNoteBeanDao(examNoteBeanDaoConfig, this);
        examOptionBeanDao = new ExamOptionBeanDao(examOptionBeanDaoConfig, this);
        favoriteRecordDao = new FavoriteRecordDao(favoriteRecordDaoConfig, this);

        registerDao(ExamBean.class, examBeanDao);
        registerDao(ExamHistoryBean.class, examHistoryBeanDao);
        registerDao(ExamNoteBean.class, examNoteBeanDao);
        registerDao(ExamOptionBean.class, examOptionBeanDao);
        registerDao(FavoriteRecord.class, favoriteRecordDao);
    }
    
    public void clear() {
        examBeanDaoConfig.clearIdentityScope();
        examHistoryBeanDaoConfig.clearIdentityScope();
        examNoteBeanDaoConfig.clearIdentityScope();
        examOptionBeanDaoConfig.clearIdentityScope();
        favoriteRecordDaoConfig.clearIdentityScope();
    }

    public ExamBeanDao getExamBeanDao() {
        return examBeanDao;
    }

    public ExamHistoryBeanDao getExamHistoryBeanDao() {
        return examHistoryBeanDao;
    }

    public ExamNoteBeanDao getExamNoteBeanDao() {
        return examNoteBeanDao;
    }

    public ExamOptionBeanDao getExamOptionBeanDao() {
        return examOptionBeanDao;
    }

    public FavoriteRecordDao getFavoriteRecordDao() {
        return favoriteRecordDao;
    }

}
