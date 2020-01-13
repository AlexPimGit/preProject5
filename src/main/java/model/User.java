package model;

import javax.persistence.*;

@Entity//объект класса можно «переложить» в таблицу
@Table(name = "users")//связывает класс и таблицу необ

public class User {
    @Id//поле является первичным ключом в таблице
    @Column(name = "id")//: связывает поле и колонку в таблице необ
    //значение первичного ключа генерируется автоматически.ТИП ГЕНЕРАЦИИ. IDENTITY (увеличение)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "role")
    private String role;
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(Long id, String name, String nickname, String role, String password) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.password = password;
    }

    public User(String name, String nickname, String role, String password) {
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
