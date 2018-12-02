package com.hacks.yale.yhacks_2018.firebase;

public class Rule {

    public int type; //one item = 1, list = 2
    public String NCD;
    public String code;
    public String predicate;
    public String field;
    public Object value;

    public Rule(int type, String NCD, String code, String predicate, String field, Object value) {
        this.type = type;
        this.NCD = NCD;
        this.code = code;
        this.predicate = predicate;
        this.field = field;
        this.value = value;
    }
}
