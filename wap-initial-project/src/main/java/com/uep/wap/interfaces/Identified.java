package com.uep.wap.interfaces;

import java.io.Serializable;
public interface Identified <PK extends Serializable> {
    PK getId();
    void setId(int id);
}
