package com.example.student.drugproducts;

import java.util.Date;

/**
 * Created by Yuanyuan Zhang on 4/11/15.
 */
public class Drug {

    private int code_;
    private String categorization_;
    private String class_;
    private String drug_id_;
    private String brand_;
    private String pediatric_;
    private String ais_;
    private String comp_name_;
    private String suite_;
    private String street_;
    private String city_;
    private String province_;
    private String country_;
    private String postal_;

    public Drug(){

    }

    public Drug(int code, String categorization, String classes, String drug_id, String brand, String pediatric, String ais, String comp_name, String suite, String street, String city, String province, String country, String postal){

        this.code_ = code;
        this.categorization_ = categorization;
        this.class_ = classes;
        this.drug_id_ = drug_id;
        this.brand_ = brand;
        this.pediatric_ = pediatric;
        this.ais_ = ais;
        this.comp_name_ = comp_name;
        this.suite_ = suite;
        this.street_ = street;
        this.city_ = city;
        this.province_ = province;
        this.country_ = country;
        this.postal_ = postal;

    }

    public void setCode_(int code){this.code_ = code;}
    public int getCode_(){return this.code_;}

    public void setCategorization_(String categorization){this.categorization_ = categorization;}
    public String getCategorization_(){return this.categorization_;}

    public void setClass_(String classes){this.class_ = classes;}
    public String getClass_(){return this.class_;}

    public void setDrug_id_(String drug_id){this.drug_id_ = drug_id;}
    public String getDrug_id_(){return this.drug_id_;}

    public void setBrand_(String brand){this.brand_ = brand;}
    public String getBrand_(){return this.brand_;}

    public void setPediatric_(String pediatric){this.pediatric_ = pediatric;}
    public String getPediatric_(){return this.pediatric_;}

    public void setAis_(String ais){this.ais_ = ais;}
    public String getAis_(){return this.ais_;}

    public void setComp_name_(String comp_name){this.comp_name_ = comp_name;}
    public String getComp_name_(){return this.comp_name_;}

    public void setSuite_(String suite){this.suite_ = suite;}
    public String getSuite_(){return this.suite_;}

    public void setStreet_(String street){this.street_ = street;}
    public String getStreet_(){return this.street_;}

    public void setCity_(String city){this.city_ = city;}
    public String getCity_(){return this.city_;}

    public void setProvince_(String province){this.province_ = province;}
    public String getProvince_(){return this.province_;}

    public void setCountry_(String country){this.country_ = country;}
    public String getCountry_(){return this.country_;}

    public void setPostal_(String postal){this.postal_ = postal;}
    public String getPostal_(){return this.postal_;}

}
