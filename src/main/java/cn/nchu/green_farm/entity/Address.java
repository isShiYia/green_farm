package cn.nchu.green_farm.entity;

/**
 * 收货地址实体来
 * @author Choococo
 */
public class Address extends BaseEntity {

    private static final long serialVersionUID = -2754804173895683653L;

    private Integer id;
    /**'数据归属的用户的id'*/
    private Integer uid;
    /**收货人*/
    private String name;
    /**省，例如：110000*/
    private String province;
    /**市*/
    private String city;
    /**区*/
    private String area;
    /**省市区的中文名称，例如：河北省石家庄市和平区*/
    private String district;
    /**邮编*/
    private String zip;
    /**详细地址*/
    private String address;
    /**手机*/
    private String phone;
    /**固定电话*/
    private String tel;
    /**标签*/
    private String tag;
    /**是否默认，0-非默认，1-默认*/
    private Integer isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", district='" + district + '\'' +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
