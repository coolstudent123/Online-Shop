package com.uep.wap.mappers;

import com.uep.wap.dto.RequestDto;
import com.uep.wap.model.Request;

import java.util.List;


public interface RequestMapper {

    RequestDto requestToRequestDto(Request request);


    Request requestDtoToRequest(RequestDto request);


    List<RequestDto> requestListToRequestDtoList(List<Request> requests);
}
