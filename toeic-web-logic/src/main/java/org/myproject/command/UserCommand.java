package org.myproject.command;

import org.myproject.core.dto.UserDTO;
import org.myproject.core.web.command.AbsCommand;

public class UserCommand extends AbsCommand<UserDTO> {
    private String confirmPassword;

    public UserCommand() {
        this.pojo = new UserDTO();
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
