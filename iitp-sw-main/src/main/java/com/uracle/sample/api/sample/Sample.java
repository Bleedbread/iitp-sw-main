package com.uracle.sample.api.sample;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Sample {

    private Integer seq;
    private String id;
    private String password;
    private String username;
    private String company;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}
