package com.machine.test.hbase.controller;

import com.machine.test.hbase.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("hbase")
public class HBaseController {

    @Autowired
    private HBaseService hBaseService;


    @GetMapping("/createTable")
    public String createTable() throws SQLException {
        hBaseService.createTable();
        return "Table";
    }

}