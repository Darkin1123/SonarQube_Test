
package Objects;

import java.io.Serializable;

public class Person implements Serializable{
    protected String id,name,gender,address,phone;
    protected int age;
    public Person(){}
    public Person(String id, String name,int age, String gender, String address, String phone ){
        this.id=id;
        setName(name);
        setAge(age);
        this.gender=gender;
        setAddress(address);
        setPhone(phone);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <=0) {
            throw new IllegalArgumentException("Age must be a positive number!");
        }
        this.age = age;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
            }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
         throw new IllegalArgumentException("Name cannot be null");
            }
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
            }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
            }
        this.phone = phone;
    }
}
    

