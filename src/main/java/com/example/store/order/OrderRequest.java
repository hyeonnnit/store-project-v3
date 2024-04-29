package com.example.store.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class UpdateDTO{
        private Integer orderNum;
    }

}
