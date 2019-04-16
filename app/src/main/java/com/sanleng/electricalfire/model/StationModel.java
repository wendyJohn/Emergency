package com.sanleng.electricalfire.model;

import com.sanleng.electricalfire.ui.bean.ArchitectureBean;

import java.util.List;

public interface StationModel {

    void StationSuccess(List<ArchitectureBean> list, int size);

    void StationFailed();
}
