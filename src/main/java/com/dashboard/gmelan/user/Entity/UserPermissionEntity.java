package com.dashboard.gmelan.user.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permission", schema = "dashboard")
public class UserPermissionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "permission")
    private String permission;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPermissionEntity that = (UserPermissionEntity) o;

        if (id != that.id) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
