package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * JPA Entity mapped to the `staff` table.
 */
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    private int    age;
    private String email;

    public Staff() { }

    // -------- Getters --------
    public Long   getId()    { return id;    }
    public String getName()  { return name;  }
    public int    getAge()   { return age;   }
    public String getEmail() { return email; }

    // -------- Setters --------
    public void setId(Long id)        { this.id    = id;    }
    public void setName(String name)  { this.name  = name;  }
    public void setAge(int age)       { this.age   = age;   }
    public void setEmail(String email){ this.email = email;  }

    @Override
    public String toString() {
        return "Staff[id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
    }
}
