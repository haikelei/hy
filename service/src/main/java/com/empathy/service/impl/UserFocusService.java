package com.empathy.service.impl;

import com.empathy.common.RspResult;
import com.empathy.dao.BaseMemberDao;
import com.empathy.dao.FileDao;
import com.empathy.dao.UserFocusDao;
import com.empathy.domain.bidding.File;
import com.empathy.domain.file.bo.FileCarBo;
import com.empathy.domain.user.BaseMember;
import com.empathy.domain.user.UserFocus;
import com.empathy.domain.user.bo.FocusAddBo;
import com.empathy.domain.user.bo.FocusCancelBo;
import com.empathy.domain.user.bo.FocusFindBo;
import com.empathy.domain.user.vo.UserFocusVo;
import com.empathy.service.AbstractBaseService;
import com.empathy.service.IUserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MI on 2018/4/18.
 */
@Service
public class UserFocusService extends AbstractBaseService implements IUserFocusService {

    @Autowired
    private UserFocusDao userFocusDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private BaseMemberDao baseMemberDao;

    @Override
    public RspResult findFocus(FocusFindBo bo) {
        try {

            List<UserFocusVo> list = userFocusDao.list(bo);
            Integer count = userFocusDao.count(bo);
            for (int i =0;i<list.size();i++){
                BaseMember baseMember = baseMemberDao.findById(list.get(i).getFocusUserId());
                if (baseMember != null) {
                    list.get(i).setStatus(baseMember.getProveStatus());
                }

                FileCarBo fileCarBo = new FileCarBo();
                fileCarBo.setType("user");
                fileCarBo.setPurposeId(list.get(i).getFocusUserId());
                File file = fileDao.findFileByPurposeIdAndType(fileCarBo);
                if(file!=null){
                    list.get(i).setUrl(file.getLocation());
                }
            }

            return success(count != null ? count : 0,list);
        }catch (Exception e){
            e.printStackTrace();
            return errorNo();
        }
    }

    @Override
    public RspResult cancelFocus(FocusCancelBo bo) {
        UserFocus userFocus = userFocusDao.findByIds(bo.getId(),bo.getUserId());
        if (userFocus == null) {
            return new RspResult("id绑定对象为空", 1);
        }
        userFocusDao.delById(bo.getId());
        return success();
    }

    @Override
    public RspResult addFocus(FocusAddBo bo) {
        UserFocus userFocus = new UserFocus();
        userFocus.setFocusUserId(bo.getFocusUserId());
        userFocus.setUserId(bo.getUserId());
        userFocusDao.save(userFocus);
        return success(userFocus);
    }

    @Override
    public RspResult save(UserFocus entity) {
        return null;
    }

    @Override
    public UserFocus findById(Long aLong) {
        return null;
    }

    @Override
    public RspResult update(UserFocus entity) {
        return null;
    }

    @Override
    public RspResult delById(Long aLong) {
        return null;
    }
}
