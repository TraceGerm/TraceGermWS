package com.tracegerm.tracegermws.service;

import com.tracegerm.tracegermws.model.User;

public interface UserService {

    User save(User user);

    User update(User user);
}
