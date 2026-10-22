package za.ac.cput.domain;
import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    public Admin() {
    }


    public Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.user = builder.user;
    }

    public Long getAdminId() {
        return adminId;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                '}';
    }

    public static class Builder {
        private Long adminId;
        private User user;

        public Builder setadminId(Long adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Admin admin) {
            this.adminId = admin.getAdminId();
            this.user = admin.getUser();
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
