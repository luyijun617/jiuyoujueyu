package cn.luyijun.fitness.api.auth;

public enum LoginType {

    PHONE(1),

    USERNAME(0);

    private Integer type;

    LoginType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }


}
