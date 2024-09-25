package org.example.unibelltestapp.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.unibelltestapp.util.EmailValidationGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * @author batal
 * @Date 25.09.2024
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "PHONE|EMAIL", message = "Type must be either PHONE or EMAIL")
    private String type;

    @NotBlank(message = "Value is required")
    @Size(max = 255, message = "Value must not exceed 255 characters")
    @Email(message = "Invalid email format", groups = EmailValidationGroup.class)
    private String value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
