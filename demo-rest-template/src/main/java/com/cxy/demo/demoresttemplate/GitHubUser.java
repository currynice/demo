package com.cxy.demo.demoresttemplate;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * According to https://api.github.com/users/currynice
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
public class GitHubUser {
    private String name;
    private String blog;
}
