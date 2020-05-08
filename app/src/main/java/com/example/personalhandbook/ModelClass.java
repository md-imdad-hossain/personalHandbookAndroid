package com.example.personalhandbook;

public class ModelClass {

   // private long;
    private String item_Domain_Name;
    private String item_Save_Email;
    private String item_Save_Password;
    private String item_vcard_name;
    private String item_vcard_organization;
    private String item_vcard_address;
    private String item_cdCard_number;
    private String item_cdcard_bank;
    private String item_cdcard_name;
    private String item_cdcard_pin;

    public ModelClass() {
    }


    public ModelClass(String item_Domain_Name, String item_Save_Email, String item_Save_Password, String item_vcard_name,
                      String item_vcard_organization, String item_vcard_address,String item_cdCard_number, String item_cdcard_bank,
                      String item_cdcard_name, String item_cdcard_pin) {

        this.item_Domain_Name = item_Domain_Name;
        this.item_Save_Email = item_Save_Email;
        this.item_Save_Password = item_Save_Password;
        this.item_vcard_name = item_vcard_name;
        this.item_vcard_organization = item_vcard_organization;
        this.item_vcard_address= item_vcard_address;
        this.item_cdCard_number = item_cdCard_number;
        this.item_cdcard_bank = item_cdcard_bank;
        this.item_cdcard_name = item_cdcard_name;
        this.item_cdcard_pin= item_cdcard_pin;
    }

    public String getItem_cdCard_number() {
        return item_cdCard_number;
    }

    public void setItem_cdCard_number(String item_cdCard_number) {
        this.item_cdCard_number = item_cdCard_number;
    }

    public String getItem_cdcard_bank() {
        return item_cdcard_bank;
    }

    public void setItem_cdcard_bank(String item_cdcard_bank) {
        this.item_cdcard_bank = item_cdcard_bank;
    }

    public String getItem_cdcard_name() {
        return item_cdcard_name;
    }

    public void setItem_cdcard_name(String item_cdcard_name) {
        this.item_cdcard_name = item_cdcard_name;
    }

    public String getItem_cdcard_pin() {
        return item_cdcard_pin;
    }

    public void setItem_cdcard_pin(String item_cdcard_pin) {
        this.item_cdcard_pin = item_cdcard_pin;
    }

    public String getItem_vcard_name() {
        return item_vcard_name;
    }

    public void setItem_vcard_name(String item_vcard_name) {
        this.item_vcard_name = item_vcard_name;
    }

    public String getItem_vcard_organization() {
        return item_vcard_organization;
    }

    public void setItem_vcard_organization(String item_vcard_organization) {
        this.item_vcard_organization = item_vcard_organization;
    }

    public String getItem_vcard_address() {
        return item_vcard_address;
    }

    public void setItem_vcard_address(String item_vcard_address) {
        this.item_vcard_address = item_vcard_address;
    }

    public String getItem_Domain_Name() {
        return item_Domain_Name;
    }

    public void setItem_Domain_Name(String item_Domain_Name) {
        this.item_Domain_Name = item_Domain_Name;
    }

    public String getItem_Save_Email() {
        return item_Save_Email;
    }

    public void setItem_Save_Email(String item_Save_Email) {
        this.item_Save_Email = item_Save_Email;
    }

    public String getItem_Save_Password() {
        return item_Save_Password;
    }

    public void setItem_Save_Password(String item_Save_Password) {
        this.item_Save_Password = item_Save_Password;
    }

    /*  public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getItemWebsite() {
        return itemWebsite;
    }

    public void setItemWebsite(String itemWebsite) {
        this.itemWebsite = itemWebsite;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemPhone() {
        return itemPhone;
    }

    public void setItemPhone(String itemPhone) {
        this.itemPhone = itemPhone;
    }*/
}
