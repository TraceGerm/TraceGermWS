package com.tracegerm.tracegermws.mapper;

import com.tracegerm.tracegermws.dto.AlertDTO;
import com.tracegerm.tracegermws.model.alert.Alert;

/**
 * Created by askos on 10/4/2015.
 */
public class AlertDTOtoAlertMapper implements IMapper<AlertDTO, Alert> {

    @Override
    public Alert map(AlertDTO source, Alert target) {
        target.setId(source.getId());
        target.setUser(source.getUser());
        target.setDetailsList(source.getDetailsList());
        target.setPlace(source.getPlace());
        target.setTimestamp(source.getTimestamp());
        return target;
    }
}
