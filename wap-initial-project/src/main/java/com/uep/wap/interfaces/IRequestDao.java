package com.uep.wap.interfaces;

import com.uep.wap.model.Request;

import java.util.List;

public interface IRequestDao extends GenericDao<Request, Integer> {
    List<Request> getSortRequestsByTitle();
    List<Request> getSortRequestsByBookCount();
    List<Request> getAll();
    void update(Request request);
}
