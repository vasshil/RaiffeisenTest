package com.app.raiffeisen.spring;

import com.app.raiffeisen.database.DataBaseController;
import com.app.raiffeisen.socks.SocksData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RestController
public class Controller {

    @ResponseBody
    @ResponseStatus
    @RequestMapping("/api/socks/income")
    public void income(@RequestBody SocksData socksData) {
        DataBaseController.getInstance().changeSocks(socksData);
    }

    @ResponseBody
    @ResponseStatus
    @RequestMapping("/api/socks/outcome")
    public void outcome(@RequestBody SocksData socksData) {
        DataBaseController.getInstance().changeSocks(socksData);
    }

    @ResponseStatus
    @GetMapping("/api/socks")
    public long socks(HttpServletRequest request) {
        return DataBaseController.getInstance().getSocks(
                request.getParameter("color"),
                request.getParameter("operation"),
                request.getParameter("cottonPart")
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void onIllegalArgumentException(IllegalArgumentException exception) {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void onSQLException(SQLException exception) {}

}
