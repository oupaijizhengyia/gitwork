package deepClone;

import java.io.Serializable;

/**
 * FileName: Address
 * Author: yeyang
 * Date: 2018/6/5 10:36
 */

public class Address implements Serializable{
    private String department;
    private String postNum;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostNum() {
        return postNum;
    }

    public void setPostNum(String postNum) {
        this.postNum = postNum;
    }

    @Override
    public String toString() {
        return "Address{" +
                "department='" + department + '\'' +
                ", postNum='" + postNum + '\'' +
                '}';
    }

}
