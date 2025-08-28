package Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")   // bảng trong database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
        private int id;

        @Column(nullable = false, unique = true, length = 100)
        private String email;

        @Column(nullable = false, unique = true, length = 50)
        private String username;  // ✅ thống nhất tên biến "username"

        @Column(nullable = false, length = 100, name = "fullname")
        private String fullName;

        @Column(nullable = false, length = 255, name = "password")
        private String password;  // ✅ thống nhất tên biến "password"

        @Column(length = 255)
        private String avatar;

        @Column(name = "role_id")
        private int roleId;       // ✅ viết camelCase

        @Column(length = 20)
        private String phone;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_date", updatable = false)
        private Date createdDate;
}
