package com.studentcrud;

/**
 * POJO representing a row in the `students` table.
 */
public class Student {

    private int    rollNo;
    private String name;
    private String email;
    private int    age;

    // -------- Getters --------
    public int    getRollNo() { return rollNo; }
    public String getName()   { return name;   }
    public String getEmail()  { return email;  }
    public int    getAge()    { return age;    }

    // -------- Setters --------
    public void setRollNo(int rollNo)    { this.rollNo = rollNo; }
    public void setName(String name)     { this.name   = name;   }
    public void setEmail(String email)   { this.email  = email;  }
    public void setAge(int age)          { this.age    = age;    }

    @Override
    public String toString() {
        return "Student{rollNo=" + rollNo
             + ", name='" + name + '\''
             + ", email='" + email + '\''
             + ", age=" + age + '}';
    }
}
