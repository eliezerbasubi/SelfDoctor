package com.example.eliezer.selfdoctor;

public class DiseaseModel {
    public String cure,description,name,image,prevention,syptoms;

    public DiseaseModel() {
    }

    public DiseaseModel(String cure, String description, String name, String image, String prevention, String syptoms) {
        this.cure = cure;
        this.description = description;
        this.name = name;
        this.image = image;
        this.prevention = prevention;
        this.syptoms = syptoms;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrevention() {
        return prevention;
    }

    public void setPrevention(String prevention) {
        this.prevention = prevention;
    }

    public String getSyptoms() {
        return syptoms;
    }

    public void setSyptoms(String syptoms) {
        this.syptoms = syptoms;
    }
}
