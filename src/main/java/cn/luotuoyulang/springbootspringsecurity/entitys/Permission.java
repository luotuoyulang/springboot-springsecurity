package cn.luotuoyulang.springbootspringsecurity.entitys;

import lombok.Data;

@Data
public class Permission {
    private Integer id;

    private String permname;

    private String permtag;

    private String url;

}